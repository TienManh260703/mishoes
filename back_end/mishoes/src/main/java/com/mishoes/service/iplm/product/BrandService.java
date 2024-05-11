package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.BrandRequest;
import com.mishoes.entity.Brand;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.product.BrandMapper;
import com.mishoes.repository.BrandRepository;
import com.mishoes.service.IBrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandService implements IBrandService {
    BrandRepository brandRepository;
    BrandMapper brandMapper;



    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(String id) {
        return brandRepository.findById(id).orElseThrow(() -> {
                    throw new DataNotFoundException("Cannot find brand with id: " + id);
                }
        );
    }

    @Override
    public Brand createBrand(BrandRequest brandDTO) {
        if (brandRepository.existsByCode(brandDTO.getCode())) {
            throw new DataAlreadyExistsException("Brand already exists");
        }
        if (brandRepository.existsByName(brandDTO.getName())) {
            throw new DataAlreadyExistsException("Brand already exists");
        }
        return brandRepository.save(brandMapper.toBrand(brandDTO));
    }

    @Override
    public String deleteBrand(String id) {
        Optional<Brand> existingBrand = brandRepository.findById(id);
        if (existingBrand.isPresent()) {
            brandRepository.deleteById(id);
            return "Deleted Brand";
        }
        return "Delete failed brand";
    }

    @Override
    public Brand updateBrand(String id, BrandRequest brandDTO) {
        Brand existingBrand = brandRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Cannot find brand with id: " + id));
        if (brandRepository.existsByName(brandDTO.getName().trim())) {
            throw new DataAlreadyExistsException("Brand name already exists");
        }
        brandMapper.updateBrand(existingBrand, brandDTO);
        return brandRepository.save(existingBrand);
    }

}
