package com.example.sd40.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "Bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Bill {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "ReceiverName")
    private String ReceiverName;

    @Column(name = "TotalMoney")
    private BigDecimal TotalMoney;

    @Column(name = "CustomerPhone")
    private String CustomerPhone;

    @Column(name = "AddressDelivery")
    private String AddressDelivery;

    @Column(name = "CreatedDate")
    private Date CreatedDate;

    @ManyToOne
    @JoinColumn(name = "BillStatusId", referencedColumnName = "Id", nullable = true)
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id", nullable = true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "Id", nullable = true)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "PaymentId", referencedColumnName = "Id", nullable = true)
    private Payment payment;

}
