package com.mishoes.service.iplm;

import com.mishoes.entity.Product;
import com.mishoes.repository.ProductRepository;
import com.mishoes.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    ProductRepository productRepository;
    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> getProducts = productRepository.findAll();
        return getProducts;
    }

    @Override
    public Product createProduct(Product product) {
        if(productRepository.existsByName(product.getName())){
            throw new RuntimeException("Product name already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
