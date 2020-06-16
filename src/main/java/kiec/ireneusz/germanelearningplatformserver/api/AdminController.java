package kiec.ireneusz.germanelearningplatformserver.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.germanelearningplatformserver.domain.user.UserFacade;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.RegisterApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.RoleApi;
import kiec.ireneusz.germanelearningplatformserver.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Admin")
public class AdminController {

    private final UserFacade userFacade;

    @Autowired
    public AdminController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterApi api
    ){
        userFacade.register(api);
        return ResponseEntity.ok().build();
    }

    @PutMapping("giveRole")
    public ResponseEntity<Void> giveRole(
            @RequestBody RoleApi api
    ) throws NotFoundException {
        userFacade.giveRole(api);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteUser/{mail}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable String mail
    ) throws NotFoundException {
        userFacade.delete(mail);
        return ResponseEntity.noContent().build();
    }

}
