package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.SoleRequest;
import com.mishoes.entity.Sole;

import java.util.List;

public interface ISoleService {
    Sole createSole(SoleRequest dto);

    Sole updateSole(String id, SoleRequest dto);

    Sole getSole(String id);

    List<Sole> getSoles();

    String deleteSoles(String id);
}
