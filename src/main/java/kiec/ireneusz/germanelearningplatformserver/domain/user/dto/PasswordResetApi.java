package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetApi {

    @NotBlank
    private String newPassword;
    @NotBlank
    private String repeatPassword;

}
