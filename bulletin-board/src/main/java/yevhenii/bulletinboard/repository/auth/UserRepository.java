package yevhenii.bulletinboard.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yevhenii.bulletinboard.model.entities.auth.User;

import java.util.Optional;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
