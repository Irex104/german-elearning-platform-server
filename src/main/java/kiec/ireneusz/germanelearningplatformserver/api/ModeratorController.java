package kiec.ireneusz.germanelearningplatformserver.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.germanelearningplatformserver.domain.post.PostFacade;
import kiec.ireneusz.germanelearningplatformserver.domain.user.UserFacade;
import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostApi;
import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostDTO;
import kiec.ireneusz.germanelearningplatformserver.exception.PostNotFoundExceprion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/moderator", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Moderator")
public class ModeratorController {

    private final UserFacade userFacade;
    private final PostFacade postFacade;

    @Autowired
    public ModeratorController(UserFacade userFacade, PostFacade postFacade) {
        this.userFacade = userFacade;
        this.postFacade = postFacade;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostDTO> createPost(
            @RequestBody PostApi api
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(postFacade.createPost(api));
    }

    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable Long postId,
            @RequestBody PostApi api
    ) throws PostNotFoundExceprion {
        return ResponseEntity.ok(postFacade.updatePost(postId, api));
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId
    ) throws PostNotFoundExceprion {
        postFacade.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}
