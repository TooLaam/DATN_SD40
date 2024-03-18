package com.example.sd40.entity.Tai_khoan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
