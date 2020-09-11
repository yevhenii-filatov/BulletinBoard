package yevhenii.bulletinboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import yevhenii.bulletinboard.model.entities.Advertisement;
import yevhenii.bulletinboard.model.entities.User;

import java.time.LocalDateTime;
import java.util.List;

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
    Page<Advertisement> findAllByPublicationDateBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);
    List<Advertisement> findAllByOwner(User owner);
    @Modifying
    void deleteById(Long id);
}
