package iocode.web.app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReg {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private int age;
}
