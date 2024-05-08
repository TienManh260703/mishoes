package com.mishoes.services.iplm;

import com.mishoes.dtos.ProductDetailDTO;
import com.mishoes.mappers.ProductDetailMapper;
import com.mishoes.models.Product;
import com.mishoes.models.ProductDetail;
import com.mishoes.repositories.ProductDetailRepository;
import com.mishoes.responses.ProductDetailResponse;
import com.mishoes.services.IProductDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailService implements IProductDetailService {
    ProductDetailRepository productDetailRepository;
    ProductDetailMapper productDetailMapper;

    @Override
    public ProductDetail createProductDetail(ProductDetailDTO dto) {
        return productDetailRepository.save(
                productDetailMapper.toProductDetail(dto)
        );
    }

    @Override
    public ProductDetail updateProductDetail(String id, ProductDetailDTO dto) {
        ProductDetail existingProductDetail = productDetailRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Cannot find Product Detail with id : " + id);
        });
        productDetailMapper.updateProductDetail(existingProductDetail, dto);
        return productDetailRepository.save(existingProductDetail);
    }

    @Override
    public ProductDetail getProductDetail(String id) {
        return productDetailRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Cannot find product detail with id : " + id);
        });
    }

    @Override
    public Page<ProductDetailResponse> getProductsDetail(PageRequest pageRequest) {
        return productDetailRepository.findAll(pageRequest)
                .map(productDetail ->
                        productDetailMapper.toProductDetailResponse(productDetail));
    }

    @Override
    public String deleteProductDetail(String id) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isPresent()) {
            productDetailRepository.deleteById(id);
            return "Product detail deleted";
        }
        return "Delete failed product detail";
    }
}
