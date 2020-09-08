package yevhenii.bulletinboard.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevhenii Filatov
 * @since 9/8/20
 */

@Data
@Entity
@Table(name = "advertisement_owner")
public class AdvertisementOwner implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "advertisementOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdvertisementOwnerPhone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "advertisementOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdvertisementOwnerEmail> emails = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Advertisement> advertisements;
}