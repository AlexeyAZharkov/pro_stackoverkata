package com.javamentor.qa.platform.webapp.converters;

import com.javamentor.qa.platform.models.dto.question.answer.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AnswerConverter {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "question.id", source = "questionId")
    @Mapping(target = "htmlBody", source = "body")
    @Mapping(target = "persistDateTime", source = "persistDate")
    @Mapping(target = "dateAcceptTime", source = "dateAccept")
    public abstract Answer answerDtoToAnswer(AnswerDto answerDto);

    @InheritInverseConfiguration
    public abstract AnswerDto answerToAnswerDto(Answer answer);
}