package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import com.example.sd40.vnpay.CreatePayMentMethodTransferRequest;
import com.example.sd40.vnpay.PayMentVnpayRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author quynh
 */
public interface PhuongThucThanhToanService {

    String createVnpay(CreatePayMentMethodTransferRequest createPayMentMethodTransferRequest, HttpServletRequest request) ;

    boolean paymentSuccess(PayMentVnpayRequest request);
}
