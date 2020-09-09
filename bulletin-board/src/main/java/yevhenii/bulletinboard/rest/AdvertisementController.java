package yevhenii.bulletinboard.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yevhenii.bulletinboard.model.entities.Advertisement;
import yevhenii.bulletinboard.repository.AdvertisementRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class AdvertisementController {
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementController(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @GetMapping("/advertisements")
    public ResponseEntity<Map<String, Object>> getAllAdvertisements(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        try {
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
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
