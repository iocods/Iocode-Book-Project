package iocode.web.app.service;

import iocode.web.app.dto.UserAuth;
import iocode.web.app.dto.UserReg;
import iocode.web.app.entity.User;
import iocode.web.app.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final AuthenticationManager authManager;

  public String authenticate(UserAuth authDetails) {
    User user = (User) userDetailsService.loadUserByUsername(authDetails.getEmail());
    if (user == null){
      throw new UsernameNotFoundException("User with the provided username is not found!");
    }
    authManager.authenticate(new UsernamePasswordAuthenticationToken(authDetails.getEmail(), authDetails.getPassword()));
    return jwtService.generateToken(Map.of("sub", authDetails.getEmail()));
  }
  public User register(UserReg regDetails) {
    User user = User.builder()
            .email(regDetails.getEmail())
            .fname(regDetails.getFname())
            .lname(regDetails.getLname())
            .password(passwordEncoder.encode(regDetails.getPassword()))
            .age(regDetails.getAge())
            .build();
    return userRepo.save(user);
  }
}
