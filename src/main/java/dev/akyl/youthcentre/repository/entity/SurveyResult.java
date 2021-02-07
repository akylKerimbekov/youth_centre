package dev.akyl.youthcentre.repository.entity;

import javafx.beans.property.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SURVEY_RESULT")
@Access(value = AccessType.PROPERTY)
@EqualsAndHashCode
@ToString
public class SurveyResult {

    private LongProperty id = new SimpleLongProperty(this, "id");
    private ObjectProperty<SurveyRef> surveyRef = new SimpleObjectProperty<>(this, "surveyRef");
    private ObjectProperty<Survey> survey = new SimpleObjectProperty<>(this, "survey");
    private StringProperty value = new SimpleStringProperty(this, "vavlue");
    private ObjectProperty<LocalDateTime> created = new SimpleObjectProperty<>(this, "created");
    private ObjectProperty<LocalDateTime> updated = new SimpleObjectProperty<>(this, "updated");
    private IntegerProperty version = new SimpleIntegerProperty(this, "version");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @ManyToOne(targetEntity = SurveyRef.class)
    @JoinColumn(name = "survey_ref_id", referencedColumnName = "id")
    public SurveyRef getSurveyRef() {
        return surveyRef.get();
    }

    public ObjectProperty<SurveyRef> surveyRefProperty() {
        return surveyRef;
    }

    public void setSurveyRef(SurveyRef surveyRef) {
        this.surveyRef.set(surveyRef);
    }

    @ManyToOne(targetEntity = Survey.class)
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    public Survey getSurvey() {
        return survey.get();
    }

    public ObjectProperty<Survey> surveyProperty() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey.set(survey);
    }

    @Column(name = "value")
    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created.get();
    }

    public ObjectProperty<LocalDateTime> createdProperty() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created.set(created);
    }

    @Column(name = "updated")
    public LocalDateTime getUpdated() {
        return updated.get();
    }

    public ObjectProperty<LocalDateTime> updatedProperty() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated.set(updated);
    }

    @Column(name = "version")
    public int getVersion() {
        return version.get();
    }

    public IntegerProperty versionProperty() {
        return version;
    }

    public void setVersion(int version) {
        this.version.set(version);
    }
}
