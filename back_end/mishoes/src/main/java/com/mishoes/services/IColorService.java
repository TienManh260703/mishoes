package com.mishoes.services;

import com.mishoes.dtos.ColorDTO;
import com.mishoes.models.Color;

import java.util.List;

public interface IColorService {
    Color createColor (ColorDTO dto);
    Color updateColor (String id , ColorDTO dto);
    List<Color> getColors ();
    Color getColor (String id);
    String deletedColor (String id);
}
