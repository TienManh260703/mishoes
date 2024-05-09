package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.BrandRequest;
import com.mishoes.entity.Brand;

import java.util.List;

public interface IBrandService {
     List<Brand> getAllBrands();
     Brand getBrandById(String id);
     Brand createBrand( BrandRequest brandDTO);
     String deleteBrand(String  id);
     Brand updateBrand(String id, BrandRequest brandDTO);
}
