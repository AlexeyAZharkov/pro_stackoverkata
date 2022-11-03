package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.question.answer.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Optional;

public interface CommentAnswerDtoDao {

    Optional<CommentAnswerDto> getCommentAnswerDtoByAnswerIdAndUserId(Long answerId, User user);
}
