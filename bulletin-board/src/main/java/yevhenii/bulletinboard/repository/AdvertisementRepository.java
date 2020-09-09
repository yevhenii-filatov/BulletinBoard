package yevhenii.bulletinboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yevhenii.bulletinboard.model.entities.Advertisement;

import java.time.LocalDateTime;

/**
 * @author Yevhenii Filatov
 * @since 9/9/20
 */

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Page<Advertisement> findAll(Pageable pageable);
    Page<Advertisement> findByPublicationDateGreaterThan(LocalDateTime date, Pageable pageable);
    Page<Advertisement> findByPublicationDateLessThan(LocalDateTime date, Pageable pageable);
    Page<Advertisement> findByTitleContaining(String title, Pageable pageable);
}
