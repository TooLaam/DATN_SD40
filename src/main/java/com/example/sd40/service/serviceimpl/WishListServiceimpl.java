package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Product;
import com.example.sd40.entity.ProductDetail;
import com.example.sd40.entity.ProductImage;
import com.example.sd40.entity.ViewModels.WishListView;
import com.example.sd40.entity.WishList;
import com.example.sd40.repository.*;
import com.example.sd40.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WishListServiceimpl implements WishListService {
    @Autowired
    WishListRespository responitory;
    @Autowired
    ProductResponsitory productResponitory;
    @Autowired
    ProductDetailResponsitory productDetailResponitory;
    @Autowired
    ProductImageResponsitory productImageResponsitory;
    @Autowired
    CustomerRepository customerResponsitory;

    @Override
    public void add(WishList wishList) {
        responitory.saveAndFlush(wishList);
    }

    @Override
    public void delete(UUID id) {
        responitory.deleteById(id);
    }

    @Override
    public void update(UUID id, WishList wishList) {
        WishList a = getOne(id);
        a.setCustomer(wishList.getCustomer());
        a.setProduct(wishList.getProduct());
        responitory.flush();
    }

    @Override
    public List<WishList> getAll() {
        return responitory.findAll();
    }

    @Override
    public void Like(UUID customerId, UUID productDetailId) {
        ProductDetail productDetail = productDetailResponitory.findById(productDetailId).get();
        Product product = productResponitory.findById(productDetail.getProduct().getId()).get();
        Optional<WishList> check = responitory.getWishListByCustomerLike(customerId, product.getId());
        if(check.isPresent()){
            responitory.deleteById(check.get().getId());
        }
        else{
            WishList wishList = new WishList();
            wishList.setId(UUID.randomUUID());
            wishList.setCustomer(customerResponsitory.findById(customerId).get());
            wishList.setProduct(productResponitory.findById(product.getId()).get());
            responitory.saveAndFlush(wishList);
        }
    }

    @Override
    public ArrayList<WishListView> getAllByCustomerId(UUID id) {
        List<WishList> wishLists = responitory.findByCustomerId(id);

        ArrayList<WishListView> wlv = new ArrayList<>();

        for (WishList wishList : wishLists) {
            Product product = productResponitory.findById(wishList.getProduct().getId()).get();
            if (product != null) {

                ProductDetail productDetail = productDetailResponitory.findByProductId(product.getId()).get(0);

                if (productDetail != null) {
                    WishListView wishListView = new WishListView();
                    wishListView.setId(wishList.getId());
                    wishListView.setCustomerId(wishList.getCustomer().getId());
                    wishListView.setProductId(wishList.getProduct().getId());
                    wishListView.setProductDetailId(productDetail.getId());
                    wishListView.setPrice(productDetail.getPrice());
                    wishListView.setCustomer(wishList.getCustomer());
                    wishListView.setProduct(wishList.getProduct());

                    ProductImage productImage = productImageResponsitory.findByProductDetailId(productDetail.getId()).get(0);

                    if (productImage != null) {
                        wishListView.setImage(productImage.getName());
                    } else {
                        wishListView.setImage("deafault.png");
                    }
                    wlv.add(wishListView);
                }
            }
        }

        return wlv;
    }

    @Override
    public Page<WishListView> getAllByCustomerIdWithPagination(UUID id, Pageable pageable) {
        List<WishList> wishLists = responitory.findByCustomerId(id);

        ArrayList<WishListView> wlv = new ArrayList<>();

        for (WishList wishList : wishLists) {
            Product product = productResponitory.findById(wishList.getProduct().getId()).get();
            if (product != null) {

                ProductDetail productDetail = productDetailResponitory.findByProductId(product.getId()).get(0);

                if (productDetail != null) {
                    WishListView wishListView = new WishListView();
                    wishListView.setId(wishList.getId());
                    wishListView.setCustomerId(wishList.getCustomer().getId());
                    wishListView.setProductId(wishList.getProduct().getId());
                    wishListView.setProductDetailId(productDetail.getId());
                    wishListView.setPrice(productDetail.getPrice());
                    wishListView.setCustomer(wishList.getCustomer());
                    wishListView.setProduct(wishList.getProduct());

                    ProductImage productImage = productImageResponsitory.findByProductDetailId(productDetail.getId()).get(0);

                    if (productImage != null) {
                        wishListView.setImage(productImage.getName());
                    } else {
                        wishListView.setImage("deafault.png");
                    }
                    wlv.add(wishListView);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), wlv.size());

        List<WishListView> sublist = wlv.subList(start, end);
        return new PageImpl<>(sublist, pageable, wlv.size());
    }

    @Override
    public WishList getOne(UUID id) {
        return responitory.findById(id).get();
    }
}
