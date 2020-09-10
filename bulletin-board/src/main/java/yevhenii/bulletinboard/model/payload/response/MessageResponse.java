package yevhenii.bulletinboard.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
@AllArgsConstructor
public class MessageResponse {
    private String message;
}