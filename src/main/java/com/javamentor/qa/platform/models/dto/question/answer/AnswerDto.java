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
@Schema(description = "сущность для хранения ответа на вопрос")
public class AnswerDto {
    @Parameter(description = "id ответа")
    private Long id;
    @Parameter(description = "id пользователя, который опубликовал ответ;")
    private Long userId;
    @Parameter(description = "id вопроса, к которому относиться ответ")
    private Long questionId;
    @Parameter(description = "тело ответа")
    private String body;
    @Parameter(description = "дата публикации ответа")
    private LocalDateTime persistDate;
    @Parameter(description = "отметка, что именно этот ответ помог решить вопрос, к которому оно относится")
    private Boolean isHelpful;
    @Parameter(description = "дата, решения вопроса")
    private LocalDateTime dateAccept;
    @Parameter(description = "счетчик репутации ответа")
    private Long countValuable;
    @Parameter(description = "ссылка на аватар автора ответа")
    private String image;
    @Parameter(description = "nickName автора ответа")
    private String nickName;

}
