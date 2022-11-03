package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.exception.VoteException;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteQuestionServiceImpl extends ReadWriteServiceImpl<VoteQuestion, Long> implements VoteQuestionService {
    private final VoteQuestionDao voteQuestionDao;
    private final ReputationDao reputationDao;
    private final QuestionService questionService;
    private final ReputationService reputationService;

    public VoteQuestionServiceImpl(ReadWriteDao<VoteQuestion, Long> readWriteDao, VoteQuestionDao voteQuestionDao, ReputationDao reputationDao, QuestionService questionService, ReputationService reputationService) {
        super(readWriteDao);
        this.voteQuestionDao = voteQuestionDao;
        this.reputationDao = reputationDao;
        this.questionService = questionService;
        this.reputationService = reputationService;
    }

    @Transactional
    public void upVote(User user, Question question) {

        Optional<VoteQuestion> voteQuestion = getVoteQuestion(user, question);
        Optional<Reputation> reputation = getReputation(user, question, ReputationType.VoteQuestion);

        if (question.getUser().equals(user)) {
            throw new VoteException("Пользователь не может голосовать за свой вопрос!");
        }

        if (voteQuestion.isPresent()) {
            if (voteQuestion.get().getVote() == VoteType.UP) {
                throw new VoteException("Пользователь проголосовал 'за' ранее!");
            } else {
                voteQuestion.get().setVote(VoteType.UP);
                voteQuestionDao.update(voteQuestion.get());
            }
        } else {
            voteQuestionDao.persist(new VoteQuestion(user, question, VoteType.UP));
        }

        if (reputation.isPresent()) {
            reputation.get().setCount(10);
            reputationDao.update(reputation.get());
        } else {
            Reputation reputationNew = new Reputation();
            reputationNew.setAuthor(user);
            reputationNew.setSender(voteQuestion.get().getUser());
            reputationNew.setCount(10);
            reputationNew.setType(ReputationType.VoteQuestion);
            reputationNew.setQuestion(question);
            reputationDao.persist(reputationNew);
        }
    }

    @Override
    public Long getSumVoteForQuestion(Long questionId) {
        return voteQuestionDao.getSumVoteForQuestion(questionService.getById(questionId).get());
    }

    @Transactional(readOnly = true)
    public Optional<VoteQuestion> getVoteQuestion(User user, Question question) {
        return voteQuestionDao.getVoteQuestion(user, question);
    }

    @Transactional(readOnly = true)
    public Optional<Reputation> getReputation(User user, Question question, ReputationType reputationType) {
        return reputationService.getReputation(user, question, reputationType);
    }

}
