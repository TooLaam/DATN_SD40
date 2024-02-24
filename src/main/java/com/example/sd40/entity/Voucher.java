package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Voucher")
public class Voucher {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Value")
    private BigDecimal value;
    @Column(name = "TimeStart")
    private Date timeStart;
    @Column(name = "TimeEnd")
    private Date timeEnd;
    @Column(name = "CreatedDate")
    private Date createdDate;
    @Column(name = "Status")
    private Integer status;
}
