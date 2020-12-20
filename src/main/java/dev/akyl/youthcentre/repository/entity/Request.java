package dev.akyl.youthcentre.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REQUEST")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "num")
    private String number;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "teenager_id")
    private Long teenagerId;

    @ManyToOne
    @JoinColumn(name = "delivery_service_id")
    private DeliveryServiceRef deliveryServiceRef;

    @ManyToOne
    @JoinColumn(name = "hard_life_id")
    private HardLifeRef hardLifeRef;

    @ManyToOne
    @JoinColumn(name = "psycho_active_id")
    private PsychoActiveRef psychoActiveRef;

    @Column(name = "addiction", length = Integer.MAX_VALUE)
    private String addiction;

    @Column(name = "deviation", length = Integer.MAX_VALUE)
    private String deviation;

    @Column(name = "consultation", length = Integer.MAX_VALUE)
    private String consultation;

    @Column(name = "support", length = Integer.MAX_VALUE)
    private String support;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private Integer version;
}
