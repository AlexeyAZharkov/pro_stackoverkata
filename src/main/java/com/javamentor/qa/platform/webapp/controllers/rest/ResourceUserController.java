package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ResourceUserController {
    private final UserDtoService userDtoService;

    public ResourceUserController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }


    @ApiOperation("Метод возвращает пользователя по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Пользователь с таким id найден"), @ApiResponse(code = 400, message = "Пользователь с таким id не найден")})
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable Long id) {
        return userDtoService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
