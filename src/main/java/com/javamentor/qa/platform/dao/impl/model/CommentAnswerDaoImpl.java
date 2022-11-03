package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentAnswerDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentAnswerDaoImpl extends ReadWriteDaoImpl<CommentAnswer, Long> implements CommentAnswerDao {
    @PersistenceContext
    private EntityManager entityManager;

    private final CommentAnswerService commentAnswerService;

    public CommentAnswerDaoImpl(CommentAnswerService commentAnswerService) {
        this.commentAnswerService = commentAnswerService;
    }

    public void addCommentAnswerToDb(Long answerId, User user, String commentText) {
        entityManager.persist(commentAnswerService.addCommentToAnswer(answerId, user, commentText));
    }
}