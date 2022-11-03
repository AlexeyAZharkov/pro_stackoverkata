package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class QuestionDtoDaoImpl implements QuestionDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<QuestionDto> getById(Long questionId, Long authorizedUserId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("""
                        select new com.javamentor.qa.platform.models.dto.QuestionDto(
                        q.id,
                        q.title,
                        q.user.id,
                        q.user.nickname,
                        q.user.imageLink,
                        q.description,
                        (select coalesce(sum(qv.id), 0) from QuestionViewed qv where qv.question.id = q.id),
                        (select coalesce(sum(r.count), 0) from Reputation r where r.question.id=q.id),
                        (select coalesce(sum(a.id), 0) from Answer a where a.question.id = q.id),
                        (select coalesce(sum(voteq.id),0) from VoteQuestion voteq where voteq.question.id = q.id),
                        q.persistDateTime,
                        q.lastUpdateDateTime,
                        (select count (voteq.vote) from VoteQuestion voteq where voteq.question.id = q.id),
                        (select count (voteq.vote) from VoteQuestion voteq where voteq.question.id = q.id
                        and voteq.user.id = q.user.id),
                        q.tags)
                        from Question q where q.id = :questionId and q.user.id = :authorizedUserId""", QuestionDto.class)
                .setParameter("questionId", questionId).setParameter("authorizedUserId", authorizedUserId));

    }
}
