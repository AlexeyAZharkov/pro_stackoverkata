package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import javax.persistence.TypedQuery;

@Repository
public class ReputationDaoImpl extends ReadWriteDaoImpl<Reputation, Long> implements ReputationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Reputation> getReputation(User user, Question question, ReputationType reputationType) {
        return Optional.ofNullable(entityManager
                .createQuery("select r from Reputation r where r.author = :user and r.question = :question and r.type = :rType", Reputation.class)
                .setParameter("user", user)
                .setParameter("question", question)
                .setParameter("rType", reputationType)
                .getSingleResult());
    }

    @Override
    public Optional<Reputation> getByAnswerIdAndSenderId(Long answerId, Long userId) {
        TypedQuery<Reputation> query = entityManager.createQuery("""
                select r from Reputation r
                where r.sender.id=:userId and r.answer.id=:answerId""", Reputation.class);
        query.setParameter("userId", userId);
        query.setParameter("answerId", answerId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }
}
