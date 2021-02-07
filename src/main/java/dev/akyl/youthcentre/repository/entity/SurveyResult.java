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
    private StringProperty value = new SimpleStringProperty(this, "value");
    private ObjectProperty<LocalDateTime> created = new SimpleObjectProperty<>(this, "created");
    private ObjectProperty<LocalDateTime> updated = new SimpleObjectProperty<>(this, "updated");
    private IntegerProperty version = new SimpleIntegerProperty(this, "version");
    private ObjectProperty<Teenager> teenager = new SimpleObjectProperty<>(this, "teenager");
    private BooleanProperty actual = new SimpleBooleanProperty(this, "actual");

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

    @ManyToOne(targetEntity = Teenager.class)
    @JoinColumn(name = "teenager_id", referencedColumnName = "id")
    public Teenager getTeenager() {
        return teenager.get();
    }

    public ObjectProperty<Teenager> teenagerProperty() {
        return teenager;
    }

    public void setTeenager(Teenager teenager) {
        this.teenager.set(teenager);
    }

    @Column(name = "actual")
    public boolean getActual() {
        return actual.get();
    }

    public BooleanProperty actualProperty() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual.set(actual);
    }
}
