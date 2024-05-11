package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.MaterialRequest;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.product.MaterialMapper;
import com.mishoes.entity.Material;
import com.mishoes.repository.MaterialRepository;
import com.mishoes.service.IMaterialService;
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
    public Material createMaterial(MaterialRequest dto) {
        if (materialRepository.existsByCode(dto.getCode())) {
            throw new DataAlreadyExistsException("Material code already exist");
        }
        if (materialRepository.existsByName(dto.getName())) {
            throw new DataAlreadyExistsException("Material code already exist");
        }
        return materialRepository.save(
                materialMapper.toMaterial(dto)
        );
    }

    @Override
    public Material updateMaterial(String id, MaterialRequest dto) {
        Material existingMaterial = materialRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("Cannot find material with id : " + id);
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
            throw new DataNotFoundException("Cannot find material with id : " + id);
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
