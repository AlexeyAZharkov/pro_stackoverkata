package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ApiOperation("Метод считает количество вопросов к базе данных")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Количество вопросов успешно посчитано"), @ApiResponse(code = 400, message = "Неверный запрос")})
    @GetMapping("/count")
    public ResponseEntity<Long> getCountQuestion() {
        return new ResponseEntity<>(questionService.getCountQuestion(), HttpStatus.OK);
    }
}
