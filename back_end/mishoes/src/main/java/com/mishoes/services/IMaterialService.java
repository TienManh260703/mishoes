package com.mishoes.services;


import com.mishoes.dtos.requests.create.product.MaterialRequest;
import com.mishoes.entity.Material;

import java.util.List;

public interface IMaterialService {
    Material createMaterial(MaterialRequest dto);

    Material updateMaterial(String id, MaterialRequest dto);

    List<Material> getMaterials();

    Material getMaterial(String id);
    String deleteMaterial (String id);
}
