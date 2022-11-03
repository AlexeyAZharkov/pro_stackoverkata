package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import java.util.Optional;

public interface ReputationDao extends ReadWriteDao<Reputation, Long> {

    Optional<Reputation> getReputation(User user, Question question, ReputationType reputationType);
    Optional<Reputation> getByAnswerIdAndSenderId(Long answerId, Long userId);
}
