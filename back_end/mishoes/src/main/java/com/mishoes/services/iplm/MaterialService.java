package com.mishoes.services.iplm;

import com.mishoes.dtos.MaterialDTO;
import com.mishoes.mappers.MaterialMapper;
import com.mishoes.models.Material;
import com.mishoes.repositories.MaterialRepository;
import com.mishoes.services.IMaterialService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaterialService implements IMaterialService {
    MaterialRepository materialRepository;
    MaterialMapper materialMapper;

    @Override
    public Material createMaterial(MaterialDTO dto) {
        if (materialRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Material code already exist");
        }
        if (materialRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Material code already exist");
        }
        return materialRepository.save(
                materialMapper.toMaterial(dto)
        );
    }

    @Override
    public Material updateMaterial(String id, MaterialDTO dto) {
        Material existingMaterial = materialRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Cannot find material with id : " + id);
        });
        materialMapper.updateMaterial(existingMaterial, dto);
        return materialRepository.save(existingMaterial);
    }

    @Override
    public List<Material> getMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material getMaterial(String id) {
        return materialRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Cannot find material with id : " + id);
        });
    }

    @Override
    public String deleteMaterial(String id) {
        Optional<Material> materialOptional = materialRepository.findById(id);
        if (materialOptional.isPresent()) {
            materialRepository.deleteById(id);
            return "Deleted material";
        }
        return "Delete failed material";
    }
}
