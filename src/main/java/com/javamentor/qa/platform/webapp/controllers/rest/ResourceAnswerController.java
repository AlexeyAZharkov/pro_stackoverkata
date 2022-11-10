package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.question.answer.AnswerDto;
import com.javamentor.qa.platform.models.dto.question.answer.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/question/{questionId}/answer")
public class ResourceAnswerController {

    private final AnswerService answerService;
    private final CommentAnswerDtoService commentAnswerDtoService;
    private final CommentAnswerService commentAnswerService;
    private final AnswerDtoDao answerDtoDao;
    private final AnswerDtoService answerDtoService;

    public ResourceAnswerController(AnswerService answerService, CommentAnswerDtoService commentAnswerDtoService, CommentAnswerService commentAnswerService, AnswerDtoDao answerDtoDao, AnswerDtoService answerDtoService) {
        this.answerService = answerService;
        this.commentAnswerDtoService = commentAnswerDtoService;
        this.commentAnswerService = commentAnswerService;
        this.answerDtoDao = answerDtoDao;
        this.answerDtoService = answerDtoService;
    }

    @ApiOperation("Пометка ответа на удаление")
    @DeleteMapping(value = "/{answerId}")
    public ResponseEntity<?> markAnswerToDelete (@PathVariable("answerId") Long answerId) {
        if (answerService.getById(answerId).isPresent()) {
            answerService.getById(answerId).get().setIsDeleted(Boolean.TRUE);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{answerId}/comment")
    public ResponseEntity<CommentAnswerDto> returnCommentAnswerDto(@AuthenticationPrincipal User user,
                                                                   @PathVariable("answerId") Long answerId,
                                                                   String comment) {
        if (comment.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentAnswerService.addCommentAnswerToDb(answerId, user, comment);
        return new ResponseEntity<>(commentAnswerDtoService.getCommentAnswerDtoByAnswerIdAndUserId(
                answerId, user).orElse(null), HttpStatus.OK);
    }

    @ApiOperation("Изменение ответа на вопрос")
    @PutMapping(value = "/{answerId}")
    public ResponseEntity<AnswerDto> changeAnswer (@PathVariable("answerId") Long answerId,
                                                   @RequestBody AnswerDto answerDto) {

        if (answerDto.getId() != answerId) {
            return (ResponseEntity<AnswerDto>) ResponseEntity.badRequest();
        }

        answerDtoService.updateAnswerByIdAndDto(answerDto, answerId);

        return ResponseEntity.ok(answerDtoDao.getAnswerDtoByAnswerIdAndUserId(answerId, answerDto.getUserId()).get());
    }
}
