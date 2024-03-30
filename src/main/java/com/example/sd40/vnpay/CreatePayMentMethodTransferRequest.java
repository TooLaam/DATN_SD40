package com.example.sd40.vnpay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thangdt
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePayMentMethodTransferRequest {

    public String vnp_Ammount ;
    public String vnp_OrderInfo = "Thanh toán hóa đơn";
    public String vnp_OrderType = "Thanh toán hóa đơn";
    public String vnp_TxnRef;

}