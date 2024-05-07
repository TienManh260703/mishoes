package com.mishoes.services;

import com.mishoes.dtos.BrandDTO;
import com.mishoes.models.Brand;

import java.util.List;

public interface IBrandService {
     List<Brand> getAllBrands();
     Brand getBrandById(String id);
     Brand createBrand( BrandDTO brandDTO);
     String deleteBrand(String  id);
     Brand updateBrand(String id, BrandDTO brandDTO);
}
