package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO, возвращающая тэги")
public class RelatedTagDto {

    @Parameter(description = "id объекта")
    Long id;
    @Schema(description = "название объекта")
    String title;
    @Schema(description = "количество вопросов, содержащих данный тег")
    Long countQuestion;

}
