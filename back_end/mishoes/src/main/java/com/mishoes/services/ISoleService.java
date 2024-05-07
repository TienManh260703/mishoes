package com.mishoes.services;

import com.mishoes.dtos.SoleDTO;
import com.mishoes.models.Sole;

import java.util.List;

public interface ISoleService {
    Sole createSole(SoleDTO dto);

    Sole updateSole(String id, SoleDTO dto);

    Sole getSole(String id);

    List<Sole> getSoles();

    String deleteSoles(String id);
}
