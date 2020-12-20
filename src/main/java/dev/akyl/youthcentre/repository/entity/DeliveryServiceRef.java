package dev.akyl.youthcentre.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "DELIVERY_SERVICE_REF")
@Data
public class DeliveryServiceRef implements Serializable {
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
