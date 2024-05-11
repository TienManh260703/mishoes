package com.mishoes.service;


import com.mishoes.dto.request.create.product.MaterialRequest;
import com.mishoes.entity.Material;

import java.util.List;

public interface IMaterialService {
    Material createMaterial(MaterialRequest dto);

    Material updateMaterial(String id, MaterialRequest dto);

    List<Material> getMaterials();

    Material getMaterial(String id);
    String deleteMaterial (String id);
}
