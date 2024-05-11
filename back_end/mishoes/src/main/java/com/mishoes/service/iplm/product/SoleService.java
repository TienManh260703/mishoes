package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.SoleRequest;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.product.SoleMapper;
import com.mishoes.entity.Sole;
import com.mishoes.repository.SoleRepository;
import com.mishoes.service.ISoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class SoleService implements ISoleService {
    SoleRepository soleRepository;
    SoleMapper soleMapper;

    @Override
    public Sole createSole(SoleRequest dto) {
        if (soleRepository.existsByCode(dto.getCode())) {
            throw new DataAlreadyExistsException("Sole code already exist");
        }
        if (soleRepository.existsByName(dto.getName())) {
            throw new DataAlreadyExistsException("Sole name already exist");
        }
        return soleRepository.save(
                soleMapper.toSole(dto)
        );
    }

    @Override
    public Sole updateSole(String id, SoleRequest dto) {
        if (soleRepository.existsByName(dto.getName())) {
            throw new DataAlreadyExistsException("Sole name already exist");
        }
        Sole existingSole = soleRepository.findById(id).orElseThrow(()-> {
            throw  new DataNotFoundException("Cannot find sole with id : "+id);
        });
        soleMapper.updateSole(existingSole  , dto);
        return soleRepository.save(
               existingSole
        );
    }

    @Override
    public Sole getSole(String id) {
        return soleRepository.findById(id).orElseThrow(()->{
            throw  new DataNotFoundException("Cannot find sole with id : "+id);
        });
    }

    @Override
    public List<Sole> getSoles() {
        return soleRepository.findAll();
    }

    @Override
    public String deleteSoles(String id) {
        Optional<Sole> soleOptional =soleRepository.findById(id);
        if(soleOptional.isPresent()){
            soleRepository.deleteById(id);
            return "Sole deleted";
        }
        return "Delete failed sole";
    }
}
