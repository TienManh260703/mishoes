package com.mishoes.repositories;

import com.mishoes.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {
    boolean existsByName(String code);

    List<ProductDetail> getProductDetailByProductId(Product product);

    List<ProductDetail> getProductDetailByBrandId(Brand brand);

    List<ProductDetail> getProductDetailByCategoryId(Category category);

    List<ProductDetail> getProductDetailByColorId(Color color);

    List<ProductDetail> getProductDetailByMaterialId(Material material);

    List<ProductDetail> getProductDetailBySizeId(Size size);

    List<ProductDetail> getProductDetailBySizeId(Sole sole);
}
