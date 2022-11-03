package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentAnswerDtoDao;
import com.javamentor.qa.platform.models.dto.question.answer.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerDtoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentAnswerDtoServiceImpl implements CommentAnswerDtoService {

    private final CommentAnswerDtoDao commentAnswerDtoDao;

    public CommentAnswerDtoServiceImpl(CommentAnswerDtoDao commentAnswerDtoDao) {
        this.commentAnswerDtoDao = commentAnswerDtoDao;
    }

    public Optional<CommentAnswerDto> getCommentAnswerDtoByAnswerIdAndUserId(Long answerId, User user) {
        return  commentAnswerDtoDao.getCommentAnswerDtoByAnswerIdAndUserId(answerId, user);
    }

}
