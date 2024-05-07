package com.mishoes.services;

import com.mishoes.dtos.SizeDTO;
import com.mishoes.models.Size;

import java.util.List;

public interface ISizeService {
    Size createSize(SizeDTO dto);

    Size updateSize(String id, SizeDTO dto);

    Size getSize(String id);

    List<Size> getSizes();

    String deleteSize(String id);
}
