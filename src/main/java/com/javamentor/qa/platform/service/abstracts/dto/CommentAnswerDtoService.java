package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.question.answer.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Optional;

public interface CommentAnswerDtoService {

    Optional<CommentAnswerDto> getCommentAnswerDtoByAnswerIdAndUserId(Long answerId, User user);
}
