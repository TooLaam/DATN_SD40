package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Color;
import com.example.sd40.entity.Product;
import com.example.sd40.entity.ProductDetail;
import com.example.sd40.entity.ViewModels.ProductDetailView;
import com.example.sd40.repository.ColorResponsitory;
import com.example.sd40.repository.ProductDetailResponsitory;
import com.example.sd40.repository.ProductResponsitory;
import com.example.sd40.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductDetailServiceimpl implements ProductDetailService {
    @Autowired
    ProductDetailResponsitory responsitory;
    @Autowired
    ProductResponsitory productResponsitory;
    @Autowired
    ColorResponsitory colorResponsitory;

    @Override
    public void add(ProductDetail productDetail) {
        productDetail.setCreatedDate(Date.valueOf(LocalDate.now()));
        responsitory.saveAndFlush(productDetail);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, ProductDetail productDetail) {
        ProductDetail detail = getOne(id);
        detail.setImportPrice(productDetail.getImportPrice());
        detail.setPrice(productDetail.getPrice());
        detail.setQuantity(productDetail.getQuantity());
        detail.setStatus(productDetail.getStatus());
        detail.setDescripTion(productDetail.getDescripTion());
        detail.setProduct(productDetail.getProduct());
        detail.setColor(productDetail.getColor());
//        detail.setListImages(productDetail.getListImages());
        responsitory.flush();
    }

    @Override
    public ArrayList<ProductDetailView> getAllProductDetail() {
        List<ProductDetail> productDetails = responsitory.findAll();

        ArrayList<ProductDetailView> pdv = new ArrayList<>();

        for (ProductDetail productDetail : productDetails) {
            Product product = productResponsitory.findById(productDetail.getProduct().getId()).get();
            Color color = colorResponsitory.findById(productDetail.getColor().getId()).get();

            if (product != null && color != null && product.getBrand() == color.getBrand()) {
                ProductDetailView productDetailView = new ProductDetailView();
                productDetailView.setId(productDetail.getId());
                productDetailView.setName(product.getName());
                productDetailView.setImportPrice(productDetail.getImportPrice());
                productDetailView.setPrice(productDetail.getPrice());
                productDetailView.setQuantity(productDetail.getQuantity());
                productDetailView.setAvailableQuantity(product.getAvailableQuantity());
                productDetailView.setSold(product.getSold());
                productDetailView.setCreatedDate(product.getCreatedDate());
                productDetailView.setStatus(product.getStatus());
                productDetailView.setDescripTion(product.getDescripTion());
                productDetailView.setProduct(product);
                productDetailView.setColor(color);

                pdv.add(productDetailView);
            }
        }

        return pdv;
    }

    @Override
    public ProductDetailView getProductDetailById(UUID id) {
        ProductDetail productDetail = responsitory.findById(id).get();

        ProductDetailView productDetailView = new ProductDetailView();

        productDetailView.setId(productDetail.getId());
        productDetailView.setImportPrice(productDetail.getImportPrice());
        productDetailView.setPrice(productDetail.getPrice());
        productDetailView.setQuantity(productDetail.getQuantity());
        productDetailView.setCreatedDate(productDetail.getCreatedDate());
        productDetailView.setStatus(productDetail.getStatus());
        productDetailView.setDescripTion(productDetail.getDescripTion());

        Product product = productResponsitory.findById(productDetail.getProduct().getId()).get();
        Color color = colorResponsitory.findById(productDetail.getColor().getId()).get();

        if (product != null && color != null /*&& product.getBrand() == color.getBrand()*/) {
            productDetailView.setName(product.getName());
            productDetailView.setAvailableQuantity(product.getAvailableQuantity());
            productDetailView.setSold(product.getSold());
            productDetailView.setProduct(product);
            productDetailView.setColor(color);

        }

        return productDetailView;
    }

    @Override
    public List<ProductDetail> getAll() {
        return responsitory.findAll();
    }

    @Override
    public ProductDetail getOne(UUID id) {
        return responsitory.findById(id).get();
    }


//    @Override
//    public List<ProductDetail> getAnh() {
//        return responsitory.getAnh();
//    }


}
