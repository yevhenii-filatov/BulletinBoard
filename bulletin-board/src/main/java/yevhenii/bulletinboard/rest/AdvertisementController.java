package yevhenii.bulletinboard.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yevhenii.bulletinboard.model.entities.Advertisement;
import yevhenii.bulletinboard.model.entities.AdvertisementOwner;
import yevhenii.bulletinboard.repository.AdvertisementOwnerRepository;
import yevhenii.bulletinboard.repository.AdvertisementRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private final AdvertisementOwnerRepository advertisementOwnerRepository;

    public AdvertisementController(AdvertisementRepository advertisementRepository,
                                   AdvertisementOwnerRepository advertisementOwnerRepository) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementOwnerRepository = advertisementOwnerRepository;
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

    @GetMapping("/owner-details")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<AdvertisementOwner> getAdvertisementOwnerDetails(@RequestParam(name = "id") Long id) {
        AdvertisementOwner owner = advertisementOwnerRepository.findById(id).orElse(null);
        if (Objects.isNull(owner)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
}

