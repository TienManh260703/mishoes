package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.ProductRequest;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.product.ProductMapper;
import com.mishoes.entity.Product;
import com.mishoes.repository.ProductRepository;
import com.mishoes.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("Cannot find product with id: " + id);
        });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Product createProduct(ProductRequest productDTO) {
        if (productRepository.existsByName(productDTO.getName())) {
            throw new DataAlreadyExistsException("Product name already exists");
        }
        if (productRepository.existsByCode(productDTO.getCode())) {
            throw new DataNotFoundException("Product code already exists");
        }
        Product product = productMapper.toProduct(productDTO);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, ProductRequest productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Cannot find product with id : " + id);
                });
        productMapper.updateProduct(existingProduct, productDTO);
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStatus(1);
            productRepository.save(product);
            return "Product deleted";
        }
        return "Delete failed product";
    }
}
