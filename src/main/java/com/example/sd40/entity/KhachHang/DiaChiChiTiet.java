package com.example.sd40.entity.KhachHang;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "dia_chi_chi_tiet")
public class DiaChiChiTiet {

    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mac_dinh")
    private Integer macDinh;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "khach_hang_id",
            referencedColumnName = "id",
            nullable = true
    )
    @JsonBackReference
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "dia_chi_id",
            referencedColumnName = "id",
            nullable = true
    )
    @JsonBackReference
    private Dia_Chi diaChi;
}
