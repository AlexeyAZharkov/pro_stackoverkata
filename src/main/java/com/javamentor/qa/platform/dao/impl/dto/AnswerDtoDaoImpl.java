package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.question.answer.AnswerDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class AnswerDtoDaoImpl implements AnswerDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<AnswerDto> getAnswerDtoByAnswerIdAndUserId(Long answerId, Long userId) {

        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("""
                        select new com.javamentor.qa.platform.models.dto.question.answer.AnswerDto(
                        a.id,
                        u.id,
                        a.question.id,
                        a.htmlBody,
                        a.updateDateTime,
                        a.isHelpful,
                        a.dateAcceptTime,
                        (SELECT COALESCE(SUM(r.count), 0L) from Reputation as r where a.id = r.answer.id)),
                        u.imageLink,
                        u.nickname
                        from Answer as a, User as u
                        where a.id = :answerId
                        and u.id = :userId
                        """, AnswerDto.class)
                .setParameter("userId", userId)
                .setParameter("answerId", answerId));

    }

}