package yevhenii.bulletinboard.model.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Yevhenii Filatov
 * @since 9/8/20
 */

@Data
@Entity
@Table(name = "advertisement_owner_phone")
public class AdvertisementOwnerPhone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone", unique = true)
    private String phone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "advertisement_owner_id")
    private AdvertisementOwner advertisementOwner;
}
