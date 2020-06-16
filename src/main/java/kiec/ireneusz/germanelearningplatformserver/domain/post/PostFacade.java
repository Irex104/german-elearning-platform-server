package kiec.ireneusz.germanelearningplatformserver.domain.post;

import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostApi;
import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostDTO;
import kiec.ireneusz.germanelearningplatformserver.exception.PostNotFoundExceprion;
import kiec.ireneusz.germanelearningplatformserver.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostFacade {

    private final PostService postService;

    @Autowired
    public PostFacade(PostService postService) {
        this.postService = postService;
    }

    public PostDTO createPost(PostApi api) {
        return new PostDTO(postService.createPost(api));
    }

    public List<PostDTO> getAllPosts() {
        return postService.getAll().stream().map(Mapper::toPostDTO)
                .collect(Collectors.toList());
    }

    public PostDTO updatePost(Long postId, PostApi api) throws PostNotFoundExceprion {
        return new PostDTO(postService.updateOne(postId, api));
    }

    public void deletePost(Long postId) throws PostNotFoundExceprion {
        postService.deleteOne(postId);
    }
}
