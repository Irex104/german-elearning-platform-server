package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import kiec.ireneusz.germanelearningplatformserver.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterApi {

    private String mail;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private String description;
    private String reference;
    private String image;

}
