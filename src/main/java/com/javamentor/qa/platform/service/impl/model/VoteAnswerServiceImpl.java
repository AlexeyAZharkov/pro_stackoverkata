package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.VoteType;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteAnswerServiceImpl extends ReadWriteServiceImpl<VoteAnswer, Long> implements VoteAnswerService {

    private final VoteAnswerDao voteAnswerDao;
    private final ReputationService reputationService;

    public VoteAnswerServiceImpl(ReadWriteDao<VoteAnswer, Long> readWriteDao, VoteAnswerDao voteAnswerDao, ReputationService reputationService) {
        super(readWriteDao);
        this.voteAnswerDao = voteAnswerDao;
        this.reputationService = reputationService;
    }

    @Override
    @Transactional
    public Optional<VoteAnswer> getByUserIdAndAnswerId(Long answerId, Long userId) {
        return voteAnswerDao.getByUserIdAndAnswerId(answerId, userId);
    }

    @Override
    @Transactional
    public Long voteDown(Answer answer, User user, int repCount, VoteType voteType) {

        Optional<VoteAnswer> optionalVoteAnswer = getByUserIdAndAnswerId(answer.getId(), user.getId());
        VoteAnswer voteAnswer;
        if (optionalVoteAnswer.isPresent()){
            voteAnswer = optionalVoteAnswer.get();
        } else {
            voteAnswer = new VoteAnswer();
            voteAnswer.setUser(user);
            voteAnswer.setAnswer(answer);
        }
        voteAnswer.setVoteType(voteType);
        update(voteAnswer);

        Optional<Reputation> optionalReputation = reputationService.getByAnswerIdAndSenderId(answer.getId(), user.getId());
        Reputation reputation;
        if (optionalReputation.isPresent()){
            reputation = optionalReputation.get();
        } else {
            reputation = new Reputation();
            reputation.setAuthor(answer.getUser());
            reputation.setSender(user);
            reputation.setAnswer(answer);
            reputation.setType(ReputationType.Answer);
        }
        reputation.setCount(repCount);
        reputationService.update(reputation);

        return voteAnswerDao.countVote(answer.getId());
    }
}
