package yevhenii.bulletinboard.model.entities.auth;

import lombok.Data;
import yevhenii.bulletinboard.model.RoleName;

import javax.persistence.*;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleName name;
}
