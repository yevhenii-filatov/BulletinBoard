package yevhenii.bulletinboard.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yevhenii.bulletinboard.model.entities.Advertisement;
import yevhenii.bulletinboard.model.entities.User;
import yevhenii.bulletinboard.model.payload.request.CreateAdvertisementRequest;
import yevhenii.bulletinboard.model.payload.response.MessageResponse;
import yevhenii.bulletinboard.repository.AdvertisementRepository;
import yevhenii.bulletinboard.repository.auth.UserRepository;
import yevhenii.bulletinboard.security.services.UserDetailsImpl;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
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
                                                                    @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
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

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<?> createAdvertisement(@Valid @RequestBody CreateAdvertisementRequest createAdvertisementRequest) {
        Advertisement advertisement = createAdvertisementFromRequest(createAdvertisementRequest);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
        advertisement.setOwner(owner);
        advertisementRepository.save(advertisement);
        return ResponseEntity.ok(new MessageResponse("Advertisement was saved successfully"));
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

