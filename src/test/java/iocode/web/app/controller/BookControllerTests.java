package iocode.web.app.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class BookControllerTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void getBooksWithoutUserTest() throws Exception{
    mvc.perform(get("/v1/api/books"))
            .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser
  void getBooksWithUserTest() throws Exception{
    mvc.perform(get("/v1/api/books"))
            .andExpect(status().isOk());
  }
}
