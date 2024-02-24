package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "BillStatus")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BillStatus {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Status")
    private Integer Status;

}
