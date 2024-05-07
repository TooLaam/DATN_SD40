package com.example.sd40.vnpay;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class VnPayConstant {
    public static String vnp_Version = "2.1.0";
    public static String vnp_Command = "2.1.0";
    public static String vnp_TmnCode = "KEAVXQXS";

    public static String vnp_HashSecret = "ILF2QLDME7D1GKWM99C9CX7O88UXGXSG";

    public static String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_BankCode = "";
    public static String vnp_CurrCode = "VND";
//    public static String vnp_IpAddr = "0:0:0:0:0:0:0:1";
    public static String vnp_Locale = "vn";
    public static String vnp_ReturnUrl = "http://localhost:8080/bill/payment-success";
    public static String vnp_ReturnUrlBuyOnline = "http://localhost:8080/bill/payment-success";

//    public static String vnp_ExpireDate = "";




    //vpn_Ammount = tien *100;
    //vpn_OrderInfo = Mo ta;
    //public static String vnp_OrderType = "200000";
    //vnp_TxnRef ma tham chieu giao dich (unique);
}
