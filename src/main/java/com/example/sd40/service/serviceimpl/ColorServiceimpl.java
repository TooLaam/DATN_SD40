package com.example.sd40.service.serviceimpl;


import com.example.sd40.entity.Color;
import com.example.sd40.entity.ProductDetail;
import com.example.sd40.entity.ViewModels.ColorView;
import com.example.sd40.repository.ColorResponsitory;
import com.example.sd40.repository.ProductDetailResponsitory;
import com.example.sd40.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ColorServiceimpl implements ColorService {
    @Autowired
    ColorResponsitory  responsitory;
    @Autowired
    ProductDetailResponsitory productDetailResponsitory;
    @Override
    public ArrayList<Color> getAll() {
        return (ArrayList<Color>) responsitory.findAll();
    }

    @Override
    public ArrayList<ColorView> getAllByProductDetailId(UUID id) {
        var productDetail = productDetailResponsitory.findById(id).get();
        var productDetails = productDetailResponsitory.findByProductId(productDetail.getProduct().getId());
        ArrayList<ColorView> colors = new ArrayList<>();
        for (ProductDetail pd : productDetails){
            ColorView color = new ColorView();
            color.setId(pd.getColor().getId());
            color.setName(pd.getColor().getName());
            color.setPrice(pd.getColor().getPrice());
            color.setImage(pd.getColor().getImage());
            color.setStatus(pd.getColor().getStatus());
            color.setProductDetailId(pd.getId());
            color.setBrand(pd.getColor().getBrand());
            colors.add(color);
        }
        return colors;
    }

    @Override
    public void save(Color color) {
            responsitory.saveAndFlush(color);
    }

    @Override
    public void delete(UUID id) {
       responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, Color color) {
         Color a = getOne(id);
         a.setName(color.getName());
        a.setPrice(color.getPrice());
        a.setImage(color.getImage());
        a.setStatus(color.getStatus());
        a.setBrand(color.getBrand());
         responsitory.flush();
    }

    @Override
    public Color getOne(UUID id) {
        return responsitory.findById(id).get();
    }


}
