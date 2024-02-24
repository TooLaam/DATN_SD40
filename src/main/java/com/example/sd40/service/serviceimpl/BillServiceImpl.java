package com.example.sd40.service.serviceimpl;


import com.example.sd40.entity.Bill;
import com.example.sd40.repository.BillDetailRepository;

import com.example.sd40.entity.*;

import com.example.sd40.repository.BillRepository;
import com.example.sd40.repository.BillStatusRepository;
import com.example.sd40.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;


@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    BillStatusRepository billStatusRepository;

    @Override
    public Bill create_new_bill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill update_bill(Bill bill) {
        Bill bill1 = billRepository.findById(bill.getId()).get();
        bill1.setReceiverName(bill.getReceiverName());
        bill1.setTotalMoney(bill.getTotalMoney());
        bill1.setCustomerPhone(bill.getCustomerPhone());
        bill1.setAddressDelivery(bill.getAddressDelivery());
        return billRepository.save(bill1);
    }

    @Override
    public List<Bill> get_all_bill() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> get_all_byCusId(UUID cusId) {
        return billRepository.getAllByCustomerId(cusId);
    }

    @Override
    public List<Bill> get_all_by_Date(Date startDate, Date endDate) {
        return billRepository.getAllBillsInDateRange(startDate, endDate);
    }


    @Override
    public Bill get_one_bill(UUID billId) {
        return billRepository.findById(billId).get();
    }
    @Override
    public void change_bill_status(UUID billId) {
        Bill bill = billRepository.findById(billId).get();
        BillStatus billStatusConfirmed = billStatusRepository.findById(UUID.fromString("159b8bc3-5489-47c0-a115-b94a0cf6286f")).get();
        BillStatus billStatusUnconfirmed = billStatusRepository.findById(UUID.fromString("259b8bc3-5489-47c0-a115-b94a0cf6286f")).get();
        System.out.println("Như nào"+bill.toString());
        if (bill.getBillStatus().getId()==billStatusUnconfirmed.getId()) {
//            System.out.println("Quân đẹp trai" + billStatusUnconfirmed.getName());
            bill.setBillStatus(billStatusConfirmed);
//            System.out.println("Bill changed" + bill.getBillStatus().getName());
            billRepository.save(bill);
        } else if (bill.getBillStatus().getId()==billStatusConfirmed.getId()) {
            bill.setBillStatus(billStatusRepository.findById(billStatusUnconfirmed.getId()).get());
            billRepository.save(bill);
        }
    }


    @Override
    public void delete_bill(UUID billId) {
        billRepository.delete(billRepository.findById(billId).get());
    }
}
