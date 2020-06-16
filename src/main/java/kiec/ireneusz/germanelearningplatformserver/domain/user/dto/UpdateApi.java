package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApi {

    private String mail;
    private String password;
    private String firstName;
    private String lastName;
    private String description;
    private String reference;
    private String image;

}
