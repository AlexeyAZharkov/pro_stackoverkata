package com.javamentor.qa.platform.initdb;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataInitService {

    @Transactional
    public void createEntity() {
        createUsers();
        createTags();
        createQuestions();
        createAnswers();
        createReputations();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    List<User> users = new ArrayList<>();


    private void createUsers() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.comm");
        user.setPassword(passwordEncoder().encode("qwe"));
        user.setFullName("Vladikin");
        user.setAbout("I'm mister genius");
        user.setCity("Moscow");
        user.setImageLink("vladikin.jpg");
        user.setLinkGitHub("github.com");
        user.setLinkSite("www.1007.com");
        user.setLinkVk("vk.com");
        user.setLastUpdateDateTime(LocalDateTime.now());
        user.setPersistDateTime(LocalDateTime.of(2022, 10, 5, 6, 22));
        user.setIsEnabled(true);
        user.setIsDeleted(false);
        user.setNickname("Vladik");
        user.setRole(new Role(1L, "USER"));
        users.add(user);
    }

    List<Tag> tags = new ArrayList<>();

    private void createTags() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Java");
        tag.setDescription("Java is simple");
        tag.setPersistDateTime(LocalDateTime.now());
        tags.add(tag);
    }

    List<Question> questions = new ArrayList<>();

    private void createQuestions() {
        Question question = new Question();
        question.setId(1L);
        question.setTitle("Did you know?");
        question.setDescription("Interesting facts");
        question.setPersistDateTime(LocalDateTime.now());
        question.setLastUpdateDateTime(LocalDateTime.of(2022, 10, 30, 12, 33));
        question.setIsDeleted(false);
        question.setUser(users.get(1));
        questions.add(question);

    }

    List<Answer> answers = new ArrayList<>();

    private void createAnswers() {
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setPersistDateTime(LocalDateTime.of(2022, 9, 2, 5, 34));
        answer.setUpdateDateTime(LocalDateTime.now());
        answer.setQuestion(questions.get(1));
        answer.setUser(users.get(1));
        answer.setHtmlBody("html_body_for_answer");
        answer.setIsHelpful(true);
        answer.setIsDeleted(false);
        answer.setIsDeletedByModerator(false);
        answer.setDateAcceptTime(LocalDateTime.of(2022, 9, 2, 9, 10));
        answers.add(answer);
    }

    List<Reputation> reputations = new ArrayList<>();

    private void createReputations() {
        Reputation reputation = new Reputation();
        reputation.setId(1L);
        reputation.setPersistDate(LocalDateTime.of(2022, 7, 2, 2, 2));
        reputation.setAuthor(users.get(1));
        reputation.setSender(users.get(1));
        reputation.setCount(22);
        reputation.setType(null);
        reputation.setQuestion(questions.get(1));
        reputation.setAnswer(answers.get(1));
        reputations.add(reputation);

    }
}
