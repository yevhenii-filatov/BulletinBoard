package yevhenii.bulletinboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yevhenii.bulletinboard.model.entities.AdvertisementOwner;

/**
 * @author Yevhenii Filatov
 * @since 9/10/20
 */

@Repository
public interface AdvertisementOwnerRepository extends CrudRepository<AdvertisementOwner, Long> {
}
