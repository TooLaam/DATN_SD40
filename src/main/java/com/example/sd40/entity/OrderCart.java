package com.example.sd40.entity;


import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCart {
    private ArrayList<BillDetail> billDetails;
}
