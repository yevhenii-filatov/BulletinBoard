package yevhenii.bulletinboard.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import yevhenii.bulletinboard.model.entities.auth.Role;
import yevhenii.bulletinboard.model.entities.auth.RoleName;
import yevhenii.bulletinboard.model.entities.auth.User;
import yevhenii.bulletinboard.model.payload.request.LoginRequest;
import yevhenii.bulletinboard.model.payload.request.SignUpRequest;
import yevhenii.bulletinboard.model.payload.response.JwtResponse;
import yevhenii.bulletinboard.model.payload.response.MessageResponse;
import yevhenii.bulletinboard.repository.auth.RoleRepository;
import yevhenii.bulletinboard.repository.auth.UserRepository;
import yevhenii.bulletinboard.security.jwt.JwtUtils;
import yevhenii.bulletinboard.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    private static final String ROLE_NOT_FOUND_MESSAGE = "Error: Role not fond";
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        JwtResponse jwtResponse = new JwtResponse(
                jwtToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        );
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(username, email, passwordEncoder.encode(password));
        user.setRoles(prepareRoles(signUpRequest));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Registration successful"));
    }

    private Set<Role> prepareRoles(SignUpRequest signUpRequest) {
        Set<String> rolesRaw = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (Objects.isNull(rolesRaw)) {
            roles.add(findRoleInDatabase(RoleName.ROLE_USER));
        } else {
            rolesRaw.forEach(roleRaw -> findAndAddOneRole(roleRaw, roles));
        }
        return roles;
    }

    private void findAndAddOneRole(String roleRaw, Set<Role> roles) {
        switch (roleRaw) {
            case "admin":
                roles.add(findRoleInDatabase(RoleName.ROLE_ADMIN));
                break;
            case "moderator":
                roles.add(findRoleInDatabase(RoleName.ROLE_MODERATOR));
                break;
            default:
                roles.add(findRoleInDatabase(RoleName.ROLE_USER));
        }
    }

    private Role findRoleInDatabase(RoleName roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE));
    }
}
