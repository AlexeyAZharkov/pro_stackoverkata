package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentAnswerDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.question.answer.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CommentAnswerDtoDaoImpl implements CommentAnswerDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<CommentAnswerDto> getCommentAnswerDtoByAnswerIdAndUserId(Long answerId, User user) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("""
                        select new com.javamentor.qa.platform.models.dto.question.answer.CommentAnswerDto(
                        ca.id,
                        a.id,
                        c.lastUpdateDateTime,
                        c.persistDateTime,
                        c.text,
                        u.id,
                        u.imageLink,
                        (SELECT COALESCE(SUM(r.count), 0L) from Reputation as r where a.question.id = r.question.id))
                        from CommentAnswer as ca, Answer as a, Comment as c, User as u
                        where a.id = :answerId
                        and u.id = :userId
                        and ca.answer.id = a.id
                        and ca.comment.id = c.id
                        and c.user.id = u.id
                        """, CommentAnswerDto.class)
                .setParameter("userId", user.getId())
                .setParameter("answerId", answerId));

    }
}
