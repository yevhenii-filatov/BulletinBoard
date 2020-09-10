package yevhenii.bulletinboard.model.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> roles;
}
