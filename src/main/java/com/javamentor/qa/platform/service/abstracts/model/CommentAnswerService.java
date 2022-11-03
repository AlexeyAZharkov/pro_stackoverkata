package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.repository.ReadWriteService;

import java.util.Optional;

public interface CommentAnswerService extends ReadWriteService<CommentAnswer, Long> {

    Optional<CommentAnswer> addCommentToAnswer(Long answerId, User user, String comment);

    void addCommentAnswerToDb(Long answerId, User user, String commentText);
}
