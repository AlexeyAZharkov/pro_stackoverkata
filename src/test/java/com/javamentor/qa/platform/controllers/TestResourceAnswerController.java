package com.javamentor.qa.platform.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.webapp.configs.JmApplication;
import com.javamentor.qa.platform.webapp.controllers.rest.ResourceAnswerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Тесты пока не сделал, не разобрался...
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = JmApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestResourceAnswerController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceAnswerController resourceAnswerController;

    @MockBean
    private AnswerService answerService;

    @Test
    public void testResourceAnswerController() throws Exception {

        given(this.answerService.getById(100L).get().getIsDeleted()).willReturn(Boolean.TRUE);

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/user/question/100/answer/{answerId}", 100) //делаем запрос
                .contentType(MediaType.APPLICATION_JSON)) //тип данных в запросе (необязательно)
                .andExpect(MockMvcResultMatchers.status().isOk()); //хотим получить статус ОК
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //хотим получить json
//                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", Matchers.is("Алеша Попович"))); //проверка по полю

    }
}
