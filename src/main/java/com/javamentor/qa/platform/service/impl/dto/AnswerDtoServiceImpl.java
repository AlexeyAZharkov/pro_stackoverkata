package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDao;
import com.javamentor.qa.platform.models.dto.question.answer.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.webapp.converters.AnswerConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerDtoServiceImpl implements AnswerDtoService {
    private final AnswerDao answerDao;
    private final AnswerConverter answerConverter;

    public AnswerDtoServiceImpl(AnswerDao answerDao, AnswerConverter answerConverter) {
        this.answerDao = answerDao;
        this.answerConverter = answerConverter;
    }

    @Override
    @Transactional
    public void updateAnswerByIdAndDto(AnswerDto answerDto, Long answerId) {

        answerDao.updateAnswerById(answerConverter.answerDtoToAnswer(answerDto), answerId);
    }
}