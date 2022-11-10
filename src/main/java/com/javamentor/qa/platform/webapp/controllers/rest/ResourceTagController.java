package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.IgnoredTagDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/tag")
public class ResourceTagController {
    private final TagDtoService tagDtoService;

    public ResourceTagController(TagDtoService tagDtoService) {
        this.tagDtoService = tagDtoService;
    }

    @ApiOperation("Метод вывода всех IgnoredTag пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "IgnoredTag найдены"),
            @ApiResponse(code = 400, message = "IgnoredTag не найдены")})
    @GetMapping(value = "/ignored")
    public ResponseEntity<List<IgnoredTagDto>> getIgnoredTagByAuthUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(tagDtoService.getIgnoredTagByAuthUser(user.getId()));
    }
}