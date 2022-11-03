package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDao;
import com.javamentor.qa.platform.dao.abstracts.model.CommentAnswerDao;
import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.exception.AnswerException;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentAnswerServiceImpl extends ReadWriteServiceImpl<CommentAnswer, Long> implements CommentAnswerService {

    private final AnswerDao answerDao;
    private final CommentAnswerDao commentAnswerDao;

    @Lazy
    @Autowired
    public CommentAnswerServiceImpl(ReadWriteDao<CommentAnswer, Long> readWriteDao, AnswerDao answerDao, CommentAnswerDao commentAnswerDao) {
        super(readWriteDao);
        this.answerDao = answerDao;
        this.commentAnswerDao = commentAnswerDao;
    }

    public Optional<CommentAnswer> addCommentToAnswer(Long answerId, User user, String commentText) {
        Optional<Answer> answer = answerDao.getById(answerId);
        if (answer.isEmpty()) {
            throw new AnswerException("Ответа не существует");
        }
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setText(commentText);
        comment.setCommentType(CommentType.ANSWER);

        CommentAnswer commentAnswer = new CommentAnswer();
        commentAnswer.setComment(comment);
        commentAnswer.setAnswer(answer.orElse(null));
        return Optional.of(commentAnswer);
    }

    public void addCommentAnswerToDb(Long answerId, User user, String commentText) {
        commentAnswerDao.addCommentAnswerToDb(answerId, user, commentText);
    }

}