package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name ="Customer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "DateOfBirth")
    private Date dateofbirth;

    @Column(name = "Address")
    private String address;

    @Column(name = "PhoneNumber")
    private String phone;

    @Column(name = "CreatedDate")
    private String datecreated;



    @Column(name = "Email")
    private String email;

    @Column(name = "Gender")
    private Integer gender;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;
}

