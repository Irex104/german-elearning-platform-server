package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginApi {

    private String username;
    private String password;

}
