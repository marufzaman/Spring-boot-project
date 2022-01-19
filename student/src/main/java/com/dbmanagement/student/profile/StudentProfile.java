package com.dbmanagement.student.profile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "StudentProfile")
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @SequenceGenerator(
            name = "Student_sequence",
            sequenceName = "Student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "dob",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dob;

    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "VARCHAR(6)"
    )
    private String gender;

    @Column(
            name = "note",
            columnDefinition = "TEXT"
    )
    private String note;

    public StudentProfile() {
    }

    public StudentProfile(Long id, String name, LocalDate dob, String gender, String note) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.note = note;
    }

    public StudentProfile(String name, LocalDate dob, String gender, String note) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
