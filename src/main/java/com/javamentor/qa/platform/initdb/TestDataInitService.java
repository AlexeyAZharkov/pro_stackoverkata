package com.javamentor.qa.platform.initdb;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataInitService {
    private final UserService userService;
    private final RoleService roleService;
    private final TagService tagService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ReputationService reputationService;

    public TestDataInitService(UserService userService, RoleService roleService, TagService tagService,
                               QuestionService questionService, AnswerService answerService,
                               ReputationService reputationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.tagService = tagService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.reputationService = reputationService;
    }

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
        Role role = new Role();
        role.setName("ROLE_USER");
        roleService.persist(role);
        User user = new User();
        user.setEmail("test@mail.comm");
        user.setPassword(passwordEncoder().encode("qwe"));
        user.setFullName("Test");
        user.setLastUpdateDateTime(LocalDateTime.of(2022, 10, 5, 6, 22));
        user.setPersistDateTime(LocalDateTime.now());
        user.setNickname("Testik");
        user.setRole(role);
        user.setIsEnabled(true);
        user.setIsDeleted(false);
        user.setCity("Moscow");
        user.setLinkSite("www.1007.com");
        user.setLinkGitHub("github.com");
        user.setLinkVk("test.vk.com");
        user.setAbout("I'm mister genius");
        user.setImageLink("test.jpg");
        users.add(user);
        userService.persist(user);
    }

    List<Tag> tags = new ArrayList<>();

    private void createTags() {
        Tag tag = new Tag();
        tag.setName("Java");
        tag.setDescription("Java is simple");
        tag.setPersistDateTime(LocalDateTime.now());
        tag.setQuestions(questions);
        tags.add(tag);
        tagService.persist(tag);
    }

    List<Question> questions = new ArrayList<>();

    private void createQuestions() {
        Question question = new Question();
        question.setTitle("Did you know?");
        question.setDescription("Interesting facts");
        question.setPersistDateTime(LocalDateTime.now());
        question.setLastUpdateDateTime(LocalDateTime.of(2022, 10, 30, 12, 33));
        question.setIsDeleted(false);
        question.setUser(users.get(0));
        question.setTags(tags);
        question.setCommentQuestions(null);
        question.setAnswers(answers);
        question.setUserFavoriteQuestions(null);
        questions.add(question);
        questionService.persistAll(questions);
    }

    List<Answer> answers = new ArrayList<>();

    private void createAnswers() {
        Answer answer = new Answer();
        answer.setPersistDateTime(LocalDateTime.of(2022, 9, 2, 5, 34));
        answer.setUpdateDateTime(LocalDateTime.now());
        answer.setQuestion(questions.get(0));
        answer.setUser(users.get(0));
        answer.setHtmlBody("html_body_for_answer");
        answer.setIsHelpful(true);
        answer.setIsDeleted(false);
        answer.setIsDeletedByModerator(false);
        answer.setDateAcceptTime(LocalDateTime.of(2022, 9, 2, 9, 10));
        answer.setCommentAnswers(null);
        answer.setVoteAnswers(null);
        answers.add(answer);
        answerService.persistAll(answers);
    }

    List<Reputation> reputations = new ArrayList<>();

    private void createReputations() {
        Reputation reputation = new Reputation();
        reputation.setPersistDate(LocalDateTime.of(2022, 7, 2, 2, 2));
        reputation.setAuthor(users.get(0));
        reputation.setSender(users.get(0));
        reputation.setCount(22);
        reputation.setType(ReputationType.Question);
        reputation.setQuestion(questions.get(0));
        reputation.setAnswer(null);
        reputations.add(reputation);
        reputationService.persistAll(reputations);
    }
}
