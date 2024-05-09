package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.SizeRequest;
import com.mishoes.entity.Size;

import java.util.List;

public interface ISizeService {
    Size createSize(SizeRequest dto);

    Size updateSize(String id, SizeRequest dto);

    Size getSize(String id);

    List<Size> getSizes();

    String deleteSize(String id);
}
