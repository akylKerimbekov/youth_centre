package dev.akyl.youthcentre.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HARD_LIFE_REF")
@Data
public class HardLifeRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "detail")
    private String detail;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private Integer version;
}
