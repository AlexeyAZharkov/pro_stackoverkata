package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.webapp.converters.UserConverter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/registration")
public class RegistrationController {
    private final UserService userService;
    public UserConverter userConverter;

    public JavaMailSender javaMailSender;

    @Value("${registration.expirationTimeInMinutes}")
    private int EXPIRATION_TIME_IN_MINUTES;
    @Value("${registration.fromAddress}")
    private String fromAddress;
    @Value("${registration.senderName}")
    private String senderName;
    @Value("${registration.host}")
    private String host;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Метод отправляет пользователю сообщение, подтверждающий почту")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Сообщение успешно отправлено"),
            @ApiResponse(code = 400, message = "Сообщение не отправлено")})
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody UserRegistrationDto userRegistrationDto) throws IOException {
        User user = userConverter.userRegistrationDtoToUser(userRegistrationDto);
        user.setIsEnabled(false);
        userService.persist(user);
        int code = userRegistrationDto.getEmail().hashCode();
        String toEmail = userRegistrationDto.getEmail();
        String content = new String(Files.readAllBytes(Paths.get("src/main//resources/templates/message.html")));
        content.replace("[[name]]", user.getUsername())
                .replace("[[email]]", user.getEmail())
                .replace("[[code]]", code + "")
                .replace("[[host]]", host);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress + " , " + senderName);
        message.setTo(toEmail);
        message.setText(content);
        javaMailSender.send(message);
        return new ResponseEntity<>("Сообщение успешно отправлено!", HttpStatus.OK);
    }

    @ApiOperation("Регистрация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Пользователь успешно прошел регистрацию"),
            @ApiResponse(code = 400, message = "Пользователь не прошел регистрацию")})
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("code") int code, @RequestParam("email") String email) {
        Optional<User> optionalUser = userService.getByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LocalDateTime expirationTime = user.getPersistDateTime().plusMinutes(EXPIRATION_TIME_IN_MINUTES);
            if (code == user.getEmail().hashCode() && LocalDateTime.now().isBefore(expirationTime)) {
                user.setIsEnabled(true);
                userService.persist(user);
            }
            return new ResponseEntity<>("Вы успешно зарегистрировались!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ссылка недействительна", HttpStatus.FORBIDDEN);
    }

}