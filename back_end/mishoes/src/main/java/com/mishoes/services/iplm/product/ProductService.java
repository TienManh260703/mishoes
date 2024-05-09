package com.mishoes.services.iplm.product;

import com.mishoes.dtos.requests.create.product.ProductRequest;
import com.mishoes.mappers.product.ProductMapper;
import com.mishoes.entity.Product;
import com.mishoes.repositories.ProductRepository;
import com.mishoes.services.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
            throw new RuntimeException("Cannot find product with id: " + id);
        });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductRequest productDTO) {
        if (productRepository.existsByName(productDTO.getName())) {
            throw new RuntimeException("Product name already exists");
        }
        if (productRepository.existsByCode(productDTO.getCode())) {
            throw new RuntimeException("Product code already exists");
        }
        Product product = productMapper.toProduct(productDTO);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, ProductRequest productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cannot find product with id : " + id);
                });
        productMapper.updateProduct(existingProduct, productDTO);
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(productOptional.get().getId());
            return "Product deleted";
        }
        return "Delete failed product";
    }
}
