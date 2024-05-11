package com.mishoes.service;

import com.mishoes.dto.request.create.product.ColorRequest;
import com.mishoes.entity.Color;

import java.util.List;

public interface IColorService {
    Color createColor (ColorRequest dto);
    Color updateColor (String id , ColorRequest dto);
    List<Color> getColors ();
    Color getColor (String id);
    String deletedColor (String id);
}
