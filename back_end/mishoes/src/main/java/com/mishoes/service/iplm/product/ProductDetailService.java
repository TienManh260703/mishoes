package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.CreateProductDetailRequest;
import com.mishoes.dto.request.update.product.UpdateProductDetailRequest;
import com.mishoes.entity.*;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.product.ProductDetailMapper;
import com.mishoes.repository.*;
import com.mishoes.dto.response.product.ProductDetailResponse;
import com.mishoes.service.IProductDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailService implements IProductDetailService {
    BrandRepository brandRepository;
    CategoryRepository categoryRepository;
    ColorRepository colorRepository;
    MaterialRepository materialRepository;
    ProductRepository productRepository;
    SizeRepository sizeRepository;
    SoleRepository soleRepository;
    ProductDetailRepository productDetailRepository;
    ProductDetailMapper productDetailMapper;


    private <T> T getPropertyById(String id, JpaRepository<T, String> repository, String propertyName) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(propertyName + " not found with id : " + id));
    }

    @Override
    public List<ProductDetail> createProductDetail(CreateProductDetailRequest request) throws DataNotFoundException {
        Product existingProduct = getPropertyById(request.getProductId(), productRepository, "Product ");
        Brand existingBrand = getPropertyById(request.getBrandId(), brandRepository, "Brand ");
        Category existingCategory = getPropertyById(request.getCategoryId(), categoryRepository, "Category ");
        Material existingMaterial = getPropertyById(request.getMaterialId(), materialRepository, "Material ");
        Sole existingSole = getPropertyById(request.getSoleId(), soleRepository, "Sole ");

        List<ProductDetail> productDetails = new ArrayList<>();
        if (existingProduct != null) {
            Set<String> colorsId = request.getColorsId();
            Set<String> sizesId = request.getSizesId();
            for (String coloId : colorsId) {
                for (String sizeId : sizesId) {
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setProductId(existingProduct);
                    productDetail.setColorId(colorRepository.findById(coloId).orElseThrow(() ->
                            new DataNotFoundException("Color not found")));
                    productDetail.setSizeId(sizeRepository.findById(sizeId).orElseThrow(() ->
                            new DataNotFoundException("Size not found")));
                    productDetail.setBrandId(existingBrand);
                    productDetail.setCategoryId(existingCategory);
                    productDetail.setMaterialId(existingMaterial);
                    productDetail.setSoleId(existingSole);
                    productDetail.setName(
                            existingProduct.getName() + " - [ "
                                    + productDetail.getColorId().getName() + " ] " + " - [ "
                                    + productDetail.getSizeId().getName() + " ]");
                    productDetail.setPrice(request.getPrice());
                    productDetail.setImportPrice(request.getImportPrice());
                    productDetail.setQuantity(request.getQuantity());
                    productDetail.setDescription(request.getDescription());
                    productDetail.setNotes(request.getNotes());
                    ProductDetail createProductDetail = productDetailRepository.save(productDetail);
                    productDetails.add(createProductDetail);
                }
            }
        }
        return productDetails;
    }

    @Override
    public ProductDetail updateProductDetail(String id, UpdateProductDetailRequest request) throws DataNotFoundException {
        ProductDetail existingProductDetail = getPropertyById(id, productDetailRepository, "Product detail ");
        Product existingProduct = getPropertyById(request.getProductId(), productRepository, "Product ");
        Brand existingBrand = getPropertyById(request.getBrandId(), brandRepository, "Brand ");
        Category existingCategory = getPropertyById(request.getCategoryId(), categoryRepository, "Category ");
        Material existingMaterial = getPropertyById(request.getMaterialId(), materialRepository, "Material ");
        Sole existingSole = getPropertyById(request.getSoleId(), soleRepository, "Sole ");
        Color existingColor = getPropertyById(request.getColorId(), colorRepository, "Color ");
        Size existingSize = getPropertyById(request.getSizeId(), sizeRepository, "Size ");

        existingProductDetail.setProductId(existingProduct);
        existingProductDetail.setBrandId(existingBrand);
        existingProductDetail.setCategoryId(existingCategory);
        existingProductDetail.setMaterialId(existingMaterial);
        existingProductDetail.setSoleId(existingSole);
        existingProductDetail.setColorId(existingColor);
        existingProductDetail.setSizeId(existingSize);
        existingProductDetail.setName(
                existingProduct.getName() + " - [ "
                        + existingColor.getName() + " ] " + " - [ "
                        + existingSize.getName() + " ]");
        existingProductDetail.setPrice(request.getPrice());
        existingProductDetail.setImportPrice(request.getImportPrice());
        existingProductDetail.setQuantity(request.getQuantity());
        existingProductDetail.setDescription(request.getDescription());
        existingProductDetail.setNotes(request.getNotes());
        return existingProductDetail;
    }

    @Override
    public ProductDetail getProductDetail(String id) {
        return productDetailRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("Cannot find product detail with id : " + id);
        });
    }

    @Override
    public Page<ProductDetailResponse> getProductsDetail(PageRequest pageRequest) {
        return productDetailRepository.findAll(pageRequest)
                .map(productDetail ->
                        productDetailMapper.toProductDetailResponse(productDetail));
    }

    @Override
    public List<ProductDetailResponse> getProductsDetailByProductId(String productId) throws DataNotFoundException {
        Product existingProduct = productRepository.findById(productId).orElseThrow(()->
            new DataNotFoundException("Product not found")
        );
        return productDetailRepository.getProductDetailByProductId(existingProduct).stream()
                .map(productDetail ->
                        productDetailMapper.toProductDetailResponse(productDetail))
                .toList();
    }

    @Override
    public String deleteProductDetail(String id) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isPresent()) {
            // Xử lý image
           ProductDetail  productDetail = productDetailOptional.get();
           productDetail.setStatus(1);
            productDetailRepository.save(productDetail);
            return "Product detail deleted";
        }
        return "Delete failed product detail";
    }
}
