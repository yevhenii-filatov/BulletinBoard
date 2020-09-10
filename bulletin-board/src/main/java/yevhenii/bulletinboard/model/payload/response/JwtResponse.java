package yevhenii.bulletinboard.model.payload.response;

import lombok.Data;

import java.util.Set;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, Set<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
