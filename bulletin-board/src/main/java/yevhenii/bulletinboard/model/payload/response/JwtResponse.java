package yevhenii.bulletinboard.model.payload.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    String token;
    String type = "Bearer";
    Long id;
    String username;
    String email;
    Set<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, Set<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
