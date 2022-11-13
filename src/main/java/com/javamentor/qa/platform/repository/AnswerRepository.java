package com.javamentor.qa.platform.repository;

import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findAnswerByIdAndUser_id(Long id, Long user_id);

//    @Modifying
//    @Query("update Answer a set a.htmlBody = :sssw, a.updateDateTime = :localDateTime")
//    void deleteAllBy
//    void updateAnswerById(@Param(":sssw") String htmlBody);

}
