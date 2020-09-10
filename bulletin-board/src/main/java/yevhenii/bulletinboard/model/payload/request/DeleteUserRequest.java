package yevhenii.bulletinboard.model.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteUserRequest {
    @NotBlank
    String email;
}
