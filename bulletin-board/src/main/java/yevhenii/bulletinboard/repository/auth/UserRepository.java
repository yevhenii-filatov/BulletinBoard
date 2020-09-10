package yevhenii.bulletinboard.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yevhenii.bulletinboard.model.entities.User;

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
    int deleteByEmail(String email);

    @Modifying
    @Query("update User user set user.email = :email where user.username = :username")
    void changeEmail(@Param("username") String username, @Param("email") String email);
}