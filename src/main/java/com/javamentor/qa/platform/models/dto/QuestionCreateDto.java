package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "создание вопроса")
public class QuestionCreateDto {
    @NotNull
    @Parameter(description = "заголовок вопроса при создании")
    private String title;
    @NotNull
    @Parameter(description = "описание вопроса при создании")
    private String description;
    @NotNull
    @Parameter(description = "теги, которыми обозначен вопрос при создании")
    private List<TagDto> tags;

}
