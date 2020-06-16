package kiec.ireneusz.germanelearningplatformserver.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    List<Login> findByMailLikeAndDeletedAtIsNull(String mail);

    Optional<Login> findByMailAndDeletedAtIsNull(String mail);
    Optional<Login> findByActivateTokenAndDeletedAtIsNull(String token);
    Optional<Login> findByPasswordTokenAndDeletedAtIsNull(String passwordToken);
    Optional<Login> findByIdAndDeletedAtIsNull(Long loginId);

}
