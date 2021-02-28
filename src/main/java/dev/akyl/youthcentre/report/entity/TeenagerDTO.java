package dev.akyl.youthcentre.report.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeenagerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate birthday;
    private String inn;
    private String sex;
    private String address;
    private String contact;
}
