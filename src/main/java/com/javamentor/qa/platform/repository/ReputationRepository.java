package com.javamentor.qa.platform.repository;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReputationRepository extends JpaRepository<Reputation, Long> {
    Optional<Reputation> findReputationByAuthorAndQuestionAndType(User user, Question question, ReputationType reputationType);
    Optional<Reputation> findReputationBySenderIdAndAnswerId(Long sender_id, Long answer_id);
}
