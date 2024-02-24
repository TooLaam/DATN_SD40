package com.example.sd40.service.serviceimpl;
import com.example.sd40.entity.*;
import com.example.sd40.entity.ViewModels.CartDetailView;
import com.example.sd40.repository.*;
import com.example.sd40.service.CartdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartdetailServiceImpl implements CartdetailService  {
    @Autowired
    CartdetailRepository responitory;
    @Autowired
    ProductDetailResponsitory productDetailResponsitory;
    @Autowired
    ProductResponsitory productResponsitory;
    @Autowired
    ProductImageResponsitory productImageResponsitory;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ColorResponsitory colorRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ArrayList<CartDetail> getAll() {
        return (ArrayList<CartDetail>) responitory.findAll();
    }

    @Override
    public ArrayList<CartDetailView> getCartDetailByCustomerId(UUID customerId) {
        List<CartDetail> cartDetails = responitory.getCartDetailsByCustomerId(customerId);

        ArrayList<CartDetailView> cs = new ArrayList<>();

        for (CartDetail cartDetail : cartDetails) {
            UUID productDetailId = cartDetail.getProductDetail().getId();
            ProductDetail productDetail = productDetailResponsitory.findById(productDetailId).orElse(null);

            if (productDetail != null) {
                UUID productId = productDetail.getProduct().getId();
                Product product = productResponsitory.findById(productId).orElse(null);

                UUID colorId = productDetail.getColor().getId();
                Color color = colorRepository.findById(colorId).orElse(null);

                if (product != null) {
                    Cart cart = cartRepository.findById(productDetailId).orElse(null);

                    CartDetailView c = new CartDetailView();
                    c.setId(cartDetail.getId());
                    c.setName(product.getName());
                    c.setColorName(color.getName());
                    c.setQuantity(cartDetail.getQuantity());
                    c.setPrice(cartDetail.getPrice());
                    c.setProductPrice(productDetail.getPrice());
                    c.setProductId(product.getId());
                    c.setProductDetailId(productDetail.getId());
                    c.setCart(cart);
                    c.setProductDetail(productDetail);

                    ProductImage productImage = productImageResponsitory.findByProductDetailId(productDetail.getId()).get(0);

                    if (productImage != null) {
                        c.setImage(productImage.getName());
                    }
                    else{
                        c.setImage("deafault.png");
                    }

                    cs.add(c);
                }
            }
        }

        return cs;
    }

    @Override
    public List<CartDetail> getCartDetailByCusId(UUID customerId) {
        List<CartDetail> cartDetails = responitory.getCartDetailsByCustomerId(customerId);
        return cartDetails;
    }

    @Override
    public CartDetail getOneCartDetail(UUID customerId, UUID productDetailId) {
        var cartDetail = responitory.getOneCartDetail(customerId, productDetailId);
        if(cartDetail == null){
            return new CartDetail();
        }
        return cartDetail;
    }

    @Override
    public void save(CartDetail cartdetail)   {
        responitory.saveAndFlush(cartdetail);
    }

    @Override
    public void delete(UUID id)   {
        responitory.deleteById(id);
    }

    @Override
    public void deleteAll(UUID id) {
        var cartDetails = responitory.getCartDetailsByCustomerId(id);
        for (CartDetail cartDetail: cartDetails) {
            responitory.deleteById(cartDetail.getId());
        }
    }

    @Override
    public void update(UUID id, CartDetail cartdetail) {
        {
            CartDetail a = getOne(id).get();
            a.setProductDetail(cartdetail.getProductDetail());
            a.setQuantity(cartdetail.getQuantity());
            a.setPrice(cartdetail.getPrice());
            responitory.flush();
        }
    }

    @Override
    public Optional<CartDetail> getOne(UUID id)  {
        return responitory.findById(id);
    }

    @Override
    public boolean checkExistCartDetail(UUID customerId, UUID productDetailId) {
        var check = responitory.getOneCartDetail(customerId, productDetailId);
        if(check == null){
            return false;
        }
        return true;
    }

}
