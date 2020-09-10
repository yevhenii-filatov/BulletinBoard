package yevhenii.bulletinboard.model.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAdvertisementRequest {
    @NotBlank
    String title;

    @NotNull
    Double price;

    @NotBlank
    String description;

    String photoUrl;
}
