package kiec.ireneusz.germanelearningplatformserver.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.germanelearningplatformserver.domain.user.UserFacade;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UpdateApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UserDTO;
import kiec.ireneusz.germanelearningplatformserver.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "User")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> user(
            @ApiIgnore @AuthenticationPrincipal UserDTO userDTO
    ) {
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<Void> deleteAccount(
            @ApiIgnore @AuthenticationPrincipal UserDTO userDTO
    ) throws NotFoundException {
        userFacade.delete(userDTO.getMail());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("changePassword")
    public ResponseEntity<Void> updateData(
            @ApiIgnore @AuthenticationPrincipal UserDTO userDTO,
            @RequestBody UpdateApi api
    ) throws NotFoundException {
        userFacade.updateData(api, userDTO);
        return ResponseEntity.noContent().build();
    }

}
