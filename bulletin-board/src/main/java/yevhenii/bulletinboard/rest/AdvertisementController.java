package yevhenii.bulletinboard.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yevhenii.bulletinboard.model.entities.Advertisement;
import yevhenii.bulletinboard.model.entities.Role;
import yevhenii.bulletinboard.model.entities.RoleName;
import yevhenii.bulletinboard.model.entities.User;
import yevhenii.bulletinboard.model.payload.request.CreateAdvertisementRequest;
import yevhenii.bulletinboard.model.payload.response.MessageResponse;
import yevhenii.bulletinboard.repository.AdvertisementRepository;
import yevhenii.bulletinboard.repository.UserRepository;
import yevhenii.bulletinboard.security.services.UserDetailsImpl;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/advertisements")
public class AdvertisementController {
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    public AdvertisementController(AdvertisementRepository advertisementRepository,
                                   UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllAdvertisements(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size,
                                                                    @RequestParam(defaultValue = "asc") String sorting) {
        Sort sort = convertSortingParam(sorting);
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Advertisement> advertisementPage = advertisementRepository.findAll(paging);
        List<Advertisement> advertisements = advertisementPage.getContent();
        if (advertisements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("advertisements", advertisements);
        response.put("currentPage", advertisementPage.getNumber());
        response.put("totalPages", advertisementPage.getTotalPages());
        response.put("totalItems", advertisementPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Sort convertSortingParam(String sorting) {
        if (StringUtils.isNotBlank(sorting) && StringUtils.equalsIgnoreCase(sorting, "desc")) {
            return Sort.by(Sort.Direction.DESC, "publicationDate");
        }
        return Sort.by(Sort.Direction.ASC, "publicationDate");
    }

    @GetMapping("/details")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.badRequest().body("Given ID must not be null");
        }
        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(id);
        if (advertisementOptional.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Advertisement not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(advertisementOptional.get());
    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<?> createAdvertisement(@Valid @RequestBody CreateAdvertisementRequest createAdvertisementRequest) {
        Advertisement advertisement = createAdvertisementFromRequest(createAdvertisementRequest);
        User owner = findCurrentUser();
        advertisement.setOwner(owner);
        advertisementRepository.save(advertisement);
        return ResponseEntity.ok(new MessageResponse("Advertisement was saved successfully"));
    }

    @Transactional
    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<?> deleteAdvertisement(@RequestParam Long id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.badRequest().body("Given ID must not be null");
        }
        User owner = findCurrentUser();
        Set<Role> roles = owner.getRoles();
        if (userHasRootsOf(RoleName.ROLE_ADMIN, roles) || userHasRootsOf(RoleName.ROLE_MODERATOR, roles)) {
            advertisementRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Advertisement has been deleted successfully"));
        }
        List<Advertisement> userAdvertisements = advertisementRepository.findAllByOwner(owner);
        if (userAdvertisements.stream().anyMatch(advertisement -> Objects.equals(advertisement.getId(), id))) {
            advertisementRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Advertisement has been deleted successfully"));
        }
        return new ResponseEntity<>(new MessageResponse("User has no access for deleting advertisements of another user"), HttpStatus.FORBIDDEN);
    }

    private boolean userHasRootsOf(RoleName roleName, Set<Role> roles) {
        return roles
                .stream()
                .map(Role::getName)
                .anyMatch(userRoleName -> userRoleName.equals(roleName));
    }

    private User findCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
    }

    private Advertisement createAdvertisementFromRequest(CreateAdvertisementRequest createAdvertisementRequest) {
        String title = createAdvertisementRequest.getTitle();
        Double price = createAdvertisementRequest.getPrice();
        String description = createAdvertisementRequest.getDescription();
        String photoUrl = createAdvertisementRequest.getPhotoUrl();
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setPrice(price);
        advertisement.setDescription(description);
        advertisement.setPhotoUrl(photoUrl);
        advertisement.setPublicationDate(LocalDateTime.now());
        return advertisement;
    }

}

