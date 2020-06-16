package kiec.ireneusz.germanelearningplatformserver.domain.post;

import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostApi;
import kiec.ireneusz.germanelearningplatformserver.exception.PostNotFoundExceprion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    Post createPost(PostApi api) {
        Post post = new Post(api);
        return repository.save(post);
    }

    List<Post> getAll() {
        return repository.findAllByDeletedAtIsNull().stream().filter(Post::isVisible)
                .collect(Collectors.toList());
    }

    Post updateOne(Long postId, PostApi api) throws PostNotFoundExceprion {
        Post post = repository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() -> new PostNotFoundExceprion(postId));
        post.update(api);
        return repository.save(post);
    }

    void deleteOne(Long postId) throws PostNotFoundExceprion {
        Post post = repository.findByIdAndDeletedAtIsNull(postId).orElseThrow(() -> new PostNotFoundExceprion(postId));
        post.delete();
        repository.save(post);
    }
}
