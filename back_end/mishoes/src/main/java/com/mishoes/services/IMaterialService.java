package com.mishoes.services;


import com.mishoes.dtos.MaterialDTO;
import com.mishoes.models.Material;

import java.util.List;

public interface IMaterialService {
    Material createMaterial(MaterialDTO dto);

    Material updateMaterial(String id, MaterialDTO dto);

    List<Material> getMaterials();

    Material getMaterial(String id);
    String deleteMaterial (String id);
}
