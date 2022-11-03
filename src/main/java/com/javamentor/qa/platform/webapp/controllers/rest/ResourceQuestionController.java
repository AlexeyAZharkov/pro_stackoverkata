package com.javamentor.qa.platform.webapp.controllers.rest;


import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.javamentor.qa.platform.models.dto.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import com.javamentor.qa.platform.webapp.converters.QuestionConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/question")
public class ResourceQuestionController {

    private final QuestionConverter questionConverter;
    private final QuestionService questionService;
    private final VoteQuestionService voteQuestionService;
    private final QuestionDtoService questionDtoService;

    public ResourceQuestionController(QuestionConverter questionConverter, VoteQuestionService voteQuestionService, QuestionService questionService, QuestionDtoService questionDtoService) {
        this.questionConverter = questionConverter;
        this.questionService = questionService;
        this.voteQuestionService = voteQuestionService;
        this.questionDtoService = questionDtoService;
    }

    @ApiOperation("Положительное голосование за вопрос")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Возвращает сумму голосов 'за' и 'против'"),
            @ApiResponse(code = 404, message = "Вопрос с таким id не найден"),
            @ApiResponse(code = 400, message = "Пользователь голосует за свой вопрос")
    })
    @PostMapping(value = "/{questionId}/upVote")
    public ResponseEntity questionUpVote(@PathVariable("questionId") Long questionId, @AuthenticationPrincipal User userAuth) {

        Optional<Question> question = questionService.getById(questionId);

        if (question.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        voteQuestionService.upVote(userAuth, question.get());

        return new ResponseEntity(voteQuestionService.getSumVoteForQuestion(questionId), HttpStatus.OK);
    }

    @ApiOperation("Метод сохранения вопроса")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Вопрос сохранен"),
            @ApiResponse(code = 400, message = "Вопрос не сохранен")})
    @PostMapping
    public ResponseEntity<QuestionDto> addNewQuestion(@AuthenticationPrincipal User user, @RequestBody QuestionCreateDto questionCreateDto) {
        Question question = questionConverter.questionCreateDtoToQuestion(questionCreateDto);
        question.setUser(user);
        questionService.persist(question);
        QuestionDto questionDto = questionConverter.questionToQuestionDto(question);
        return ResponseEntity.ok(questionDto);
    }

    @ApiOperation("Метод возвращает вопрос по его id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Вопрос с таким id успешно найден"), @ApiResponse(code = 400, message = "Вопрос с таким id не найден")})
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionDtoById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return questionDtoService.getById(id, user.getId()).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
