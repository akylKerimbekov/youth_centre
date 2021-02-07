package dev.akyl.youthcentre.repository.entity;

import javafx.beans.property.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SURVEY_REF")
@Access(value = AccessType.PROPERTY)
@EqualsAndHashCode
@ToString
public class SurveyRef {

    private LongProperty id = new SimpleLongProperty(this, "id");
    private LongProperty parentId = new SimpleLongProperty(this, "parentId");
    private StringProperty code = new SimpleStringProperty(this, "code");
    private StringProperty detail = new SimpleStringProperty(this, "detail");
    private BooleanProperty numField = new SimpleBooleanProperty(this, "numField");
    private BooleanProperty strField = new SimpleBooleanProperty(this, "strField");
    private BooleanProperty boolField = new SimpleBooleanProperty(this, "boolField");
    private BooleanProperty refField = new SimpleBooleanProperty(this, "refField");
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

    @Column(name = "parent_id")
    public long getParentId() {
        return parentId.get();
    }

    public LongProperty parentIdProperty() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId.set(parentId);
    }

    @Column(name = "code")
    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    @Column(name = "detail")
    public String getDetail() {
        return detail.get();
    }

    public StringProperty detailProperty() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail.set(detail);
    }

    @Column(name = "is_number")
    public Boolean getNumField() {
        return numField.get();
    }

    public BooleanProperty numFieldProperty() {
        return numField;
    }

    public void setNumField(Boolean numField) {
        this.numField.set(numField);
    }

    @Column(name = "is_string")
    public Boolean getStrField() {
        return strField.get();
    }

    public BooleanProperty strFieldProperty() {
        return strField;
    }

    public void setStrField(Boolean strField) {
        this.strField.set(strField);
    }

    @Column(name = "is_boolean")
    public Boolean getBoolField() {
        return boolField.get();
    }

    public BooleanProperty boolFieldProperty() {
        return boolField;
    }

    public void setBoolField(Boolean boolField) {
        this.boolField.set(boolField);
    }

    @Column(name = "is_reference")
    public Boolean getRefField() {
        return refField.get();
    }

    public BooleanProperty refFieldProperty() {
        return refField;
    }

    public void setRefField(Boolean refField) {
        this.refField.set(refField);
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