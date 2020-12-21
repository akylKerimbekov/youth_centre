package dev.akyl.youthcentre.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SURVEY_RESULT")
@Data
public class SurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = SurveyRef.class)
    @JoinColumn(name = "survey_ref_id", referencedColumnName = "id")
    private SurveyRef surveyRef;

    @ManyToOne(targetEntity = Survey.class)
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;

    @Column(name = "value")
    private String value;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private Integer version;

}
