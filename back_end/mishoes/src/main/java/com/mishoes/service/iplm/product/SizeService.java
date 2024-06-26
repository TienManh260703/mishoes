package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.SizeRequest;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.product.SizeMapper;
import com.mishoes.entity.Size;
import com.mishoes.repository.SizeRepository;
import com.mishoes.service.ISizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeService implements ISizeService {
    SizeRepository sizeRepository;
    SizeMapper sizeMapper;

    @Override
    public Size createSize(SizeRequest dto) {
        if (sizeRepository.existsByCode(dto.getCode())) {
            throw new DataAlreadyExistsException("Size code already exist");
        }
        if (sizeRepository.existsByName(dto.getName())) {
            throw new DataAlreadyExistsException("Size name already exist");
        }
        return sizeRepository.save(
                sizeMapper.toSize(dto)
        );
    }

    @Override
    public Size updateSize(String id, SizeRequest dto) {
        if (sizeRepository.existsByName(dto.getName())) {
            throw new DataAlreadyExistsException("Size name already exist");
        }
        Size existingSize = sizeRepository.findById(id).orElseThrow(() -> {
                    throw new DataNotFoundException("Cannot find size with id : " + id);
                }
        );
        sizeMapper.updateSize(existingSize, dto);
        return sizeRepository.save(existingSize);
    }

    @Override
    public Size getSize(String id) {
        return sizeRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("Cannot find size with id :" + id);
        });
    }

    @Override
    public List<Size> getSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public String deleteSize(String id) {
        Optional<Size> sizeOptional = sizeRepository.findById(id);
        if(sizeOptional.isPresent()){
            sizeRepository.deleteById(id);
            return "Size deleted";
        }
        return "Delete failed size";
    }
}
