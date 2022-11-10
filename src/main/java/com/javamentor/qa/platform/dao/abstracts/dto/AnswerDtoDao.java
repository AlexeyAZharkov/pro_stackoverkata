package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.question.answer.AnswerDto;

import java.util.Optional;

public interface AnswerDtoDao {
    Optional<AnswerDto> getAnswerDtoByAnswerIdAndUserId(Long answerId, Long userId);
}
