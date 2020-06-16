package kiec.ireneusz.germanelearningplatformserver.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.germanelearningplatformserver.domain.post.PostFacade;
import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostDTO;
import kiec.ireneusz.germanelearningplatformserver.domain.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Public")
public class PublicController {

    private final UserFacade userFacade;
    private final PostFacade postFacade;

    @Autowired
    public PublicController(UserFacade userFacade, PostFacade postFacade) {
        this.userFacade = userFacade;
        this.postFacade = postFacade;
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok(postFacade.getAllPosts());
    }

}
