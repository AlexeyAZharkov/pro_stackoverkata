package com.javamentor.qa.platform.models.dto.question.answer;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Schema(description = "сущность для хранения информации о комментарии к ответу")
public class CommentAnswerDto {
    @Parameter(description = "id сущности")
    private Long id;
    @Parameter(description = "id вопроса, к которому принадлежит комментарий")
    private Long answerId;
    @Parameter(description = "последнее время редактирования комментария")
    private LocalDateTime lastRedactionDate;
    @Parameter(description = "дата создания комментария")
    private LocalDateTime persistDate;
    @Parameter(description = "текст комментария")
    private String text;
    @Parameter(description = "id пользователя, написавшего комментарий")
    private Long userId;
    @Parameter(description = "ссылка на аватар автора комментария")
    private String imageLink;
    @Parameter(description = "счетчик репутации комментария")
    private Long reputation;
}
