package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;

public interface CommentAnswerDao extends ReadWriteDao<CommentAnswer, Long> {

    void addCommentAnswerToDb(Long answerId, User user, String commentText);
}
