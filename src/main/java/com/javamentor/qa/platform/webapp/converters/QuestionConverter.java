package com.javamentor.qa.platform.webapp.converters;

import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TagConverter.class)
public abstract class QuestionConverter {
    public abstract Question questionCreateDtoToQuestion(QuestionCreateDto questionCreateDto);
    @Mapping(source = "user.id", target = "authorId")
    @Mapping(source = "user.fullName", target = "authorName")
    @Mapping(source = "user.imageLink", target = "authorImage")
    @Mapping(source = "question.tags", target = "listTagDto")
    public abstract QuestionDto questionToQuestionDto(Question question);
}