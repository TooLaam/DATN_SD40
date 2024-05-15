package com.example.sd40.service.HoaDon;


import com.example.sd40.vnpay.CreatePayMentMethodTransferRequest;
import com.example.sd40.vnpay.PayMentVnpayRequest;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author quynh
 */
public interface PhuongThucThanhToanService {

    String createVnpay(CreatePayMentMethodTransferRequest createPayMentMethodTransferRequest, HttpServletRequest request) ;

    boolean paymentSuccess(PayMentVnpayRequest request);
}
