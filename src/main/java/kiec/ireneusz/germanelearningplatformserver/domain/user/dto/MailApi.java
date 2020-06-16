package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MailApi {

    private String recipient;

    @JsonIgnore
    private String subject = "Are you forgot your password ?";
    @JsonIgnore
    private String message = "click on link: " +
            "http://localhost:9090/public/newPassword/";

}
