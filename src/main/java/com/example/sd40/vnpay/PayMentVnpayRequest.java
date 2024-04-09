package com.example.sd40.vnpay;

import lombok.Getter;
import lombok.Setter;

/**
 * @author thangdt
 */
@Getter
@Setter
public class PayMentVnpayRequest {
    private String vnp_Amount;
    private String vnp_ResponseCode;
    public String vnp_TxnRef;
    public String vnp_OrderInfo;
    private String vnp_TransactionNo;
}
