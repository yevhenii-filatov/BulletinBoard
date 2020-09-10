package yevhenii.bulletinboard.model.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
