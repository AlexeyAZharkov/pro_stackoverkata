package com.javamentor.qa.platform.repository;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReputationRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findAnswerByIdAndUser_id(Long id, Long user_id);

//    @Modifying
//    @Query("update Answer a set a.htmlBody = :sssw, a.updateDateTime = :localDateTime")
//    void deleteAllBy
//    void updateAnswerById(@Param(":sssw") String htmlBody);

}
