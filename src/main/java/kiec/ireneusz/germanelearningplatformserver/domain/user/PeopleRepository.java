package kiec.ireneusz.germanelearningplatformserver.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {

    Page<Person> findAllByDeletedAtIsNull(Pageable pageable);
    Page<Person> findByMailLikeAndDeletedAtIsNull(String mail, Pageable pageable);

    Optional<Person> findByIdAndDeletedAtIsNull(Long id);
}
