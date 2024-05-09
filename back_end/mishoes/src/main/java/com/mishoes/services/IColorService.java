package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.ColorRequest;
import com.mishoes.entity.Color;

import java.util.List;

public interface IColorService {
    Color createColor (ColorRequest dto);
    Color updateColor (String id , ColorRequest dto);
    List<Color> getColors ();
    Color getColor (String id);
    String deletedColor (String id);
}
