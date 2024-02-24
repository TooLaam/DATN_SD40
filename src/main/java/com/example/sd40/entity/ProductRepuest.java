package com.example.sd40.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductRepuest {

    private List<MultipartFile> files;
    private BigDecimal importPrice;
    private BigDecimal price;
    private Integer quantity;
    private Date createdDate;
    private Integer status;
    private String descripTion;
    private UUID color;
    private UUID product;

}
