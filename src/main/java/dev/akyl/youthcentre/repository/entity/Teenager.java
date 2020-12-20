package dev.akyl.youthcentre.repository.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TEENAGER")
@Data
public class Teenager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "inn")
    private String inn;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private Integer version;

    @OneToMany(targetEntity = Request.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teenager_id", nullable = true)
    private List<Request> requests = new ArrayList<>();

    @OneToMany(targetEntity = Caretaker.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teenager_id", nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Caretaker> caretakers = new ArrayList<>();

}
