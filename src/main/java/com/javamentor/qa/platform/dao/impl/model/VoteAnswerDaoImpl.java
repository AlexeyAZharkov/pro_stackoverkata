package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class VoteAnswerDaoImpl extends ReadWriteDaoImpl<VoteAnswer, Long> implements VoteAnswerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VoteAnswer> getByUserIdAndAnswerId(Long answerId, Long userId) {
        TypedQuery<VoteAnswer> query = entityManager.createQuery("""
                select v
                from VoteAnswer v
                where v.user.id=:userId and v.answer.id=:answerId""", VoteAnswer.class);
        query.setParameter("userId", userId);
        query.setParameter("answerId", answerId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }
    @Override
    public Long countVote(Long answerId) {
        TypedQuery<Long> query = entityManager.createQuery("""
                select sum(case when v.voteType = 'DOWN' then -1 else 1 end)
                from VoteAnswer v
                where v.answer.id=:answerId""", Long.class);
        query.setParameter("answerId", answerId);
        return SingleResultUtil.getSingleResultOrNull(query).get();
    }
}
