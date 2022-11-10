package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.entity.question.VoteType;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/user/question/{questionId}/answer")
public class ResourseAnswerController {

    private final AnswerService answerService;
    private final VoteAnswerService voteAnswerService;

    public ResourseAnswerController(AnswerService answerService, VoteAnswerService voteAnswerService) {
        this.answerService = answerService;
        this.voteAnswerService = voteAnswerService;
    }

    @PostMapping("{id}/downVote")
    @ApiOperation("Голосование за Answer (уменьшение оценки)")
    public ResponseEntity<Long> downVote(@AuthenticationPrincipal User currentUser, @PathVariable("id") Long answerId) {
        Optional<Answer> optionalAnswer = answerService.getAnswerForVote(answerId, currentUser.getId());
        if (optionalAnswer.isEmpty()) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(voteAnswerService.voteDown(optionalAnswer.get(), currentUser, -5, VoteType.DOWN));
    }

    @PostMapping("{id}/upVote")
    @ApiOperation("Голосование за Answer (увеличение оценки)")
    public ResponseEntity<Long> upVote (@AuthenticationPrincipal User currentUser, @PathVariable("id") Long answerId){
        Optional<Answer> optionalAnswer = answerService.getAnswerForVote(answerId, currentUser.getId());
        if (optionalAnswer.isEmpty()){
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(voteAnswerService.voteUp(optionalAnswer.get(), currentUser, +10, VoteType.UP));
    }
}
