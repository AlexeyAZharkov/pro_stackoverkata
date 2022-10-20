package com.javamentor.qa.platform.initdb;

import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDataInitServiceTest {

    @Test
    void createEntity() {
        createUsers();
    }

    @Test
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    List<User> users = new ArrayList<>();

    private void createUsers() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.comm");
        user.setPassword(passwordEncoder().encode("qwe"));//qwe
        user.setFullName("Vladikin");
        user.setAbout("I'm mister genius");
        user.setCity("Moscow");
        user.setImageLink("vladikin.jpg");
        user.setLinkGitHub("github.com");
        user.setLinkSite("www.1007.com");
        user.setLinkVk("vk.com");
        user.setLastUpdateDateTime(null);
        user.setPersistDateTime(null);
        user.setIsEnabled(true);
        user.setIsDeleted(true);
        user.setNickname("Vladik");
        user.setRole(new Role(1L, "USER"));
        users.add(user);
    }
}