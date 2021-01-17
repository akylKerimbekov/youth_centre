package dev.akyl.youthcentre.repository.entity;

import javafx.beans.property.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "REQUEST")
@Access(value = AccessType.PROPERTY)
@EqualsAndHashCode
@ToString
public class Request  implements Serializable {

    private LongProperty id = new SimpleLongProperty(this, "id");
    private StringProperty number = new SimpleStringProperty(this, "number");
    private ObjectProperty<LocalDateTime> date = new SimpleObjectProperty<>(this, "date");
    private LongProperty teenagerId = new SimpleLongProperty(this, "teenagerId");
    private ObjectProperty<DeliveryServiceRef> deliveryServiceRef = new SimpleObjectProperty<>(this, "deliveryServiceRef");
    private ObjectProperty<HardLifeRef> hardLifeRef = new SimpleObjectProperty<>(this, "hardLifeRef");
    private ObjectProperty<PsychoActiveRef> psychoActiveRef = new SimpleObjectProperty<>(this, "psychoActiveRef");
    private StringProperty addiction = new SimpleStringProperty(this, "addiction");
    private StringProperty deviation = new SimpleStringProperty(this, "deviation");
    private StringProperty consultation = new SimpleStringProperty(this, "consultation");
    private StringProperty support = new SimpleStringProperty(this, "support");
    private ObjectProperty<LocalDateTime> created = new SimpleObjectProperty<>(this, "created");
    private ObjectProperty<LocalDateTime> updated = new SimpleObjectProperty<>(this, "updated");


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Column(name = "num")
    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    @Column(name = "date")
    public LocalDateTime getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDateTime> dateProperty() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date.set(date);
    }

    @Column(name = "teenager_id")
    public long getTeenagerId() {
        return teenagerId.get();
    }

    public LongProperty teenagerIdProperty() {
        return teenagerId;
    }

    public void setTeenagerId(long teenagerId) {
        this.teenagerId.set(teenagerId);
    }

    @ManyToOne
    @JoinColumn(name = "delivery_service_id")
    public DeliveryServiceRef getDeliveryServiceRef() {
        return deliveryServiceRef.get();
    }

    public ObjectProperty<DeliveryServiceRef> deliveryServiceRefProperty() {
        return deliveryServiceRef;
    }

    public void setDeliveryServiceRef(DeliveryServiceRef deliveryServiceRef) {
        this.deliveryServiceRef.set(deliveryServiceRef);
    }

    @ManyToOne
    @JoinColumn(name = "hard_life_id")
    public HardLifeRef getHardLifeRef() {
        return hardLifeRef.get();
    }

    public ObjectProperty<HardLifeRef> hardLifeRefProperty() {
        return hardLifeRef;
    }

    public void setHardLifeRef(HardLifeRef hardLifeRef) {
        this.hardLifeRef.set(hardLifeRef);
    }

    @ManyToOne
    @JoinColumn(name = "psycho_active_id")
    public PsychoActiveRef getPsychoActiveRef() {
        return psychoActiveRef.get();
    }

    public ObjectProperty<PsychoActiveRef> psychoActiveRefProperty() {
        return psychoActiveRef;
    }

    public void setPsychoActiveRef(PsychoActiveRef psychoActiveRef) {
        this.psychoActiveRef.set(psychoActiveRef);
    }

    @Column(name = "addiction", length = Integer.MAX_VALUE)
    public String getAddiction() {
        return addiction.get();
    }

    public StringProperty addictionProperty() {
        return addiction;
    }

    public void setAddiction(String addiction) {
        this.addiction.set(addiction);
    }

    @Column(name = "deviation", length = Integer.MAX_VALUE)
    public String getDeviation() {
        return deviation.get();
    }

    public StringProperty deviationProperty() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation.set(deviation);
    }

    @Column(name = "consultation", length = Integer.MAX_VALUE)
    public String getConsultation() {
        return consultation.get();
    }

    public StringProperty consultationProperty() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation.set(consultation);
    }

    @Column(name = "support", length = Integer.MAX_VALUE)
    public String getSupport() {
        return support.get();
    }

    public StringProperty supportProperty() {
        return support;
    }

    public void setSupport(String support) {
        this.support.set(support);
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
}
