package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "вопрос")
public class QuestionDto {
    @Parameter(description = "id вопроса")
    private Long id;
    @Parameter(description = "заголовок вопроса")
    private String title;
    @Parameter(description = "id автора вопроса")
    private Long authorId;
    @Parameter(description = "имя автора вопроса")
    private String authorName;
    @Parameter(description = "аватарка автора вопроса")
    private String authorImage;
    @Parameter(description = "описание автора вопроса")
    private String description;
    @Parameter(description = "репутация автора вопроса")
    private Long authorReputation;
    @Parameter(description = "количество ответов на вопрос")
    private int countAnswer;
    @Parameter(description = "количество полезных  ответов на вопрос")
    private int countValuable;
    @Parameter(description = "дата создания вопроса")
    private LocalDateTime persistDateTime;
    @Parameter(description = "дата последней редакции вопроса или добавления ответа")
    private LocalDateTime lastUpdateDateTime;
    @Parameter(description = "теги, которыми обозначен вопрос")
    private List<TagDto> listTagDto;


}
