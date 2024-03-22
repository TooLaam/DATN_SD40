package com.example.sd40.entity.Tai_khoan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vai_tro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaiTro {
    public VaiTro(String ten) {
        this.ten = ten;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @JsonIgnore
    @OneToMany(mappedBy = "vaiTro",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<TaiKhoan> taiKhoanList;


}
