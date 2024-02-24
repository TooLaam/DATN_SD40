package com.example.sd40.service;

import com.example.sd40.entity.Color;
import com.example.sd40.entity.ViewModels.ColorView;

import java.util.ArrayList;
import java.util.UUID;

public interface ColorService {
    ArrayList<Color> getAll();

    ArrayList<ColorView> getAllByProductDetailId(UUID id);

    void save(Color color);

    void delete(UUID id);

    void update(UUID id, Color color);

    Color getOne(UUID id);
}
