package yevhenii.bulletinboard.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yevhenii.bulletinboard.model.entities.Role;
import yevhenii.bulletinboard.model.entities.RoleName;

import java.util.Optional;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
