package yevhenii.bulletinboard.model.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Yevhenii Filatov
 * @since 9/8/20
 */

@Data
@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Double price;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "publication_date", nullable = false)
    private LocalDateTime publicationDate;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "advertisement_owner_id", nullable = false)
    private AdvertisementOwner owner;
}
