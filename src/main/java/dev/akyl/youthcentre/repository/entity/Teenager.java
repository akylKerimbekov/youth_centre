package dev.akyl.youthcentre.repository.entity;

import dev.akyl.youthcentre.service.TeenagerService;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TEENAGER")
@Access(value = AccessType.PROPERTY)
@EqualsAndHashCode
@ToString
public class Teenager implements Serializable {

    private LongProperty id = new SimpleLongProperty(this, "id");
    private StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private StringProperty middleName = new SimpleStringProperty(this, "middleName");
    private StringProperty email = new SimpleStringProperty(this, "email");
    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>(this, "birthday");
    private StringProperty inn = new SimpleStringProperty(this, "inn");
    private StringProperty sex = new SimpleStringProperty(this, "sex");
    private StringProperty address = new SimpleStringProperty(this, "address");
    private StringProperty contact = new SimpleStringProperty(this, "contact");
    private ObjectProperty<LocalDateTime> created = new SimpleObjectProperty<>(this, "created");
    private ObjectProperty<LocalDateTime> updated = new SimpleObjectProperty<>(this, "updated");
    private ListProperty<Request> requests = new SimpleListProperty<>();//new ArrayList<>();
    private ListProperty<Caretaker> caretakers = new SimpleListProperty<>();//new ArrayList<>();

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public ObjectProperty<LocalDate> birthdayProperty(){
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public StringProperty innProperty() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn.set(inn);
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public ObjectProperty<LocalDateTime> createdProperty(){
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created.set(created);
    }

    public ObjectProperty<LocalDateTime> updatedProperty(){
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated.set(updated);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id.get();
    }


    @Column(name = "first_name")
    public String getFirstName() {
        return firstName.get();
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName.get();
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName.get();
    }

    @Column(name = "email")
    public String getEmail() {
        return email.get();
    }

    @Column(name = "birthday")
    public LocalDate getBirthday() {
        return birthday.get();
    }

    @Column(name = "inn")
    public String getInn() {
        return inn.get();
    }

    @Column(name = "sex")
    public String getSex() {
        return sex.get();
    }

    @Column(name = "address")
    public String getAddress() {
        return address.get();
    }

    @Column(name = "contact")
    public String getContact() {
        return contact.get();
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created.get();
    }

    @Column(name = "updated")
    public LocalDateTime getUpdated() {
        return updated.get();
    }

    @OneToMany(targetEntity = Request.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teenager_id", nullable = true)
    public List<Request> getRequests() {
        return requests.get();
    }

    public void setRequests(List<Request> requests) {
        this.requests.set(FXCollections.observableArrayList(requests));
    }

    public ListProperty requestsProperty() {
        return requests;
    }

    @OneToMany(targetEntity = Caretaker.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teenager_id", nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Caretaker> getCaretakers() {
        return caretakers.get();
    }

    public void setCaretakers(List<Caretaker> caretakers) {
        this.caretakers.set(FXCollections.observableArrayList(caretakers));
    }

    public ListProperty caretakersProperty(){
        return caretakers;
    }
}
