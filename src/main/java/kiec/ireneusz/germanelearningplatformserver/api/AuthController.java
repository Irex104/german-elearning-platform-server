package kiec.ireneusz.germanelearningplatformserver.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.germanelearningplatformserver.domain.user.UserFacade;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.LoginApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.MailApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.PasswordResetApi;
import kiec.ireneusz.germanelearningplatformserver.exception.NotFoundException;
import kiec.ireneusz.germanelearningplatformserver.utils.AuthenticationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/authorization", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Auth")
public class AuthController {

    private final TokenEndpoint tokenEndpoint;
    private final UserFacade userFacade;

    @Autowired
    public AuthController(TokenEndpoint tokenEndpoint, UserFacade userFacade) {
        this.tokenEndpoint = tokenEndpoint;
        this.userFacade = userFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<OAuth2AccessToken> login(
            @RequestBody LoginApi api,
            @RequestHeader(defaultValue = "Basic YnJvd3Nlcjo=") String authorization
    ) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", api.getUsername());
        parameters.put("password", api.getPassword());
        parameters.put("grant_type", "password");
        parameters.put("scope", "ui");
        return tokenEndpoint.postAccessToken(new AuthenticationImpl(), parameters);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextLogoutHandler logout = new SecurityContextLogoutHandler();
            logout.setClearAuthentication(true);
            logout.logout(request, response, auth);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/passwordReminder")
    public ResponseEntity<String> remindPassword(
            @RequestBody MailApi api
    ) throws NotFoundException {
        return ResponseEntity.ok(userFacade.remindPassword(api));
    }

    @PutMapping("/newPassword/{passwordToken}")
    public ResponseEntity<String> newPassword(
            @PathVariable String passwordToken,
            @RequestBody PasswordResetApi passwordResetApi
    ) throws NotFoundException {
        return ResponseEntity.ok(userFacade.newPassword(passwordResetApi, passwordToken));
    }

}
