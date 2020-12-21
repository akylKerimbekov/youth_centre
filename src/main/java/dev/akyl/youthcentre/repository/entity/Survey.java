package dev.akyl.youthcentre.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SURVEY")
@Data
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Teenager.class)
    @JoinColumn(name = "teenager_id", referencedColumnName = "id")
    private Teenager teenager;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private Integer version;

}
