package yevhenii.bulletinboard.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "price", nullable = false)
    Double price;

    @Lob
    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "publication_date", nullable = false)
    LocalDateTime publicationDate;

    @Column(name = "photo_url")
    String photoUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User owner;
}
