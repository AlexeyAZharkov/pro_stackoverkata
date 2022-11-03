package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class VoteQuestionDaoImpl extends ReadWriteDaoImpl<VoteQuestion, Long> implements VoteQuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<VoteQuestion> getVoteQuestion(User user, Question question) {
        return Optional.ofNullable(entityManager
                .createQuery("select vq from VoteQuestion vq where vq.user = :user and vq.question = :question", VoteQuestion.class)
                .setParameter("user", user)
                .setParameter("question", question)
                .getSingleResult());
    }

    @Override
    public Long getSumVoteForQuestion(Question question) {
        return (long) entityManager
                .createQuery("select sum(sumvote), " +
                        "case when vq.vote = 'UP' then 1 " +
                        "when vq.vote = 'DOWN' then -1 " +
                        "else 0 " +
                        "end as sumvote " +
                        "from VoteQuestion vq where vq.question = :question")
                .setParameter("question", question)
                .getFirstResult();
    }
}
