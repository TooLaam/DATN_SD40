package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "UsedVoucher")
public class UsedVoucher {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "SubTotal")
    private BigDecimal subTotal;
    @ManyToOne
    @JoinColumn(name = "BillId", referencedColumnName = "Id", nullable = true)
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "VoucherId", referencedColumnName = "Id", nullable = true)
    private Voucher voucher;
}
