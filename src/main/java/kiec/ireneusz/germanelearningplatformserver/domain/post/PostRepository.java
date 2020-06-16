package kiec.ireneusz.germanelearningplatformserver.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByDeletedAtIsNull();

    Optional<Post> findByIdAndDeletedAtIsNull(Long postId);
}
