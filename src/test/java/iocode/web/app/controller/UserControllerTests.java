package iocode.web.app.controller;

import iocode.web.app.dto.UserReg;
import iocode.web.app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class UserControllerTests {

  @Autowired
  private UserService userService;

  @Autowired
  private MockMvc mvc;

  @BeforeEach
  void saveUserForTest() {
    UserReg reg = new UserReg(
            "Iocode fname",
            "Iocode lname",
            "Iocode@gmail.com",
            "Iocode123",
            1
    );
    userService.register(reg);
  }

  @Test
  void authenticateUserTests() throws Exception {
    String authJson = "{" +
            "\"email\" : \"Iocode@gmail.com\"," +
            "\"password\" : \"Iocode123\"" +
            "}";

    MvcResult result = mvc.perform(post("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .content(authJson)
    ).andExpect(status().isOk()).andReturn();

    String response = result.getResponse().getContentAsString();
    System.out.println("Returned Jwt token \n" + response);
  }
}
