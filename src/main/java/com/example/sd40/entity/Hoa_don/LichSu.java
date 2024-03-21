package com.example.sd40.entity.Hoa_don;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author quynh
 */
@Entity
@Table(name = "history_bill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LichSu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String note;

    @Column(name = "ngay_tao")
    private long ngayTao;

    @ManyToOne
    @JoinColumn(name = "voucher_id",referencedColumnName = "id")
    private HoaDon hoaDon;
}
