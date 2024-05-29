package iocode.web.app.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTests {

  @Autowired
  private UserRepository userRepository;

  @Test
  void saveUserTests(){
    User user = User.builder()
            .fname("Iocode fname")
            .lname("Iocode lname")
            .email("Iocode@gmail.com")
            .password("Iocode password")
            .age(1)
            .build();
    User savedUser = userRepository.save(user);
    assertEquals(user, savedUser);
  }
}
