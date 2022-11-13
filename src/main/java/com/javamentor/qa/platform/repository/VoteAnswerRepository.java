package com.javamentor.qa.platform.repository;

import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteAnswerRepository extends JpaRepository<VoteAnswer, Long> {
    Optional<VoteAnswer> findVoteAnswerByUserIdAndAnswer_Id(Long userid, Long answer_id);
//    long countVoteAnswerByTy();
}
