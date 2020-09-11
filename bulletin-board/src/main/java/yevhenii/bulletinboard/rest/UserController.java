package yevhenii.bulletinboard.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yevhenii.bulletinboard.model.entities.User;
import yevhenii.bulletinboard.model.payload.request.DeleteUserRequest;
import yevhenii.bulletinboard.model.payload.request.UpdateUserEmailRequest;
import yevhenii.bulletinboard.model.payload.response.MessageResponse;
import yevhenii.bulletinboard.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@Valid @RequestBody DeleteUserRequest deleteUserRequest) {
        String email = deleteUserRequest.getEmail();
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: user with such email does not exist"));
        }
        if (userRepository.deleteByEmail(email) != 0) {
            return ResponseEntity
                    .ok(new MessageResponse("User deleted successfully"));
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    @PostMapping("/update-email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserEmail(@Valid @RequestBody UpdateUserEmailRequest updateUserEmailRequest) {
        String username = updateUserEmailRequest.getUsername();
        String newEmail = updateUserEmailRequest.getNewEmail();
        if (!userRepository.existsByUsername(username)) {
            return new ResponseEntity<>(new MessageResponse("Error: user not found"), HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findByUsername(username).get();
        String currentEmail = user.getEmail();
        if (Objects.equals(currentEmail, newEmail)) {
            return new ResponseEntity<>(new MessageResponse("No changes made - email is the same"), HttpStatus.NOT_MODIFIED);
        }
        userRepository.changeEmail(username, newEmail);
        return ResponseEntity.ok(new MessageResponse("Email was changed successfully"));
    }
}
