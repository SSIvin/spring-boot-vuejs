package SpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Person")
@ToString(of = {"id"})

public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Id.class)
    private Long id;

    @Column(name = "f", length = 50)
    @JsonView(Views.IdName.class)
    private String f;

    @Column(name = "i",length = 50)
    @JsonView(Views.IdName.class)
    private String i;

    @Column(name = "o", length = 50)
    @JsonView(Views.IdName.class)
    private String o;

    @Column(name = "placeJob")
    private Long placeJob;

    @Column(name = "birthday")
    private Date birthday;

    public Person() {
    }

    public Person(long id, String f, String i, String o) {
        this.id = id;
        this.f = f;
        this.i = i;
        this.o = o;
    }

    public Person(String f, String i, String o, Long placeJob, Date birthday) {
        this.f = f;
        this.i = i;
        this.o = o;
        this.placeJob = placeJob;
        this.birthday = birthday;
    }

    public Person(long id, String f, String i, String o, java.sql.Date birthday, long placeJob) {

    }

    public Long getPlaceJob() {
        return placeJob;
    }

    public void setPlaceJob(Long placeJob) {
        this.placeJob = placeJob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
