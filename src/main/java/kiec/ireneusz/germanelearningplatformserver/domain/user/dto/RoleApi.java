package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import kiec.ireneusz.germanelearningplatformserver.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleApi {

    private String mail;
    private Role role;

}
