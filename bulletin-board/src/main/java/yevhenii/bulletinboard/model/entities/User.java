package yevhenii.bulletinboard.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    Long id;

    @NotBlank
    @Size(max = 20)
    @Getter
    @Setter
    @Column(name = "username")
    String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Getter
    @Setter
    @Column(name = "email")
    String email;

    @NotBlank
    @Size(max = 50)
    @Email
    @Getter
    @Setter
    @Column(name = "phone")
    String phone;

    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    @Getter
    @Setter
    @Column(name = "password")
    String password;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    transient Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    transient List<Advertisement> advertisements;
}
