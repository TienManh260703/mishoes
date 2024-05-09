package com.mishoes.services.iplm.product;

import com.mishoes.dtos.requests.create.product.ColorRequest;
import com.mishoes.mappers.product.ColorMapper;
import com.mishoes.entity.Color;
import com.mishoes.repositories.ColorRepository;
import com.mishoes.services.IColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorService implements IColorService {
    ColorRepository colorRepository;
    ColorMapper colorMapper;

    @Override
    public Color createColor(ColorRequest dto) {

        if (colorRepository.existsByCode(dto.getCode())){
            throw new RuntimeException("Color code already exist");
        }
        if (colorRepository.existsByName(dto.getName())){
            throw new RuntimeException("Color name already exist");
        }
        return colorRepository.save(colorMapper.toColor(dto));
    }

    @Override
    public Color updateColor(String id, ColorRequest dto) {
        Color existingColor = colorRepository.findById(id).orElseThrow(() -> {
                    throw new RuntimeException("Cannot find Color with id : " + id);
                }
        );
        colorMapper.updateColor(existingColor, dto);
        return colorRepository.save(existingColor);
    }

    @Override
    public List<Color> getColors() {
        return colorRepository.findAll();

    }

    @Override
    public Color getColor(String id) {
        return colorRepository.findById(id).orElseThrow(()-> {
                    throw new RuntimeException("Cannot find Color with id : "+id);
                }
        );
    }

    @Override
    public String deletedColor(String id) {
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (colorOptional.isPresent()){
            colorRepository.deleteById(id);
            return "Color deleted";
        }
        return "Delete failed color";
    }
}