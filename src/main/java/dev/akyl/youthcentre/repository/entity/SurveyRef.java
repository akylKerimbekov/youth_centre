package dev.akyl.youthcentre.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SURVEY_REF")
@Data
public class SurveyRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "detail")
    private String detail;

    @Column(name = "is_number")
    private Boolean isNumber;

    @Column(name = "is_string")
    private Boolean isString;

    @Column(name = "is_boolean")
    private Boolean isBoolean;

    @Column(name = "is_reference")
    private Boolean isReference;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private Integer version;
}
