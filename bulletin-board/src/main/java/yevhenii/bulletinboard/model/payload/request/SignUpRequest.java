package yevhenii.bulletinboard.model.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    String username;

    @Email
    @NotBlank
    @Size(max = 50)
    String email;

    @Size(max = 30)
    String phone;

    @NotBlank
    @Size(min = 6, max = 40)
    String password;

    Set<String> roles;
}
