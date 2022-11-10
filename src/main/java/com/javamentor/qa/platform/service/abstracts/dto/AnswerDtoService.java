package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.question.answer.AnswerDto;

public interface AnswerDtoService {
    void updateAnswerByIdAndDto(AnswerDto answerDto, Long answerId);
}
