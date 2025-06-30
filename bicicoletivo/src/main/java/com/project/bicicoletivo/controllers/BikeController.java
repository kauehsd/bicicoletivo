package com.project.bicicoletivo.controllers;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.bicicoletivo.dto.BikeDTO;

@RestController
@RequestMapping("/api/bikes")
@CrossOrigin(origins = "*")
public class BikeController {

    private final List<BikeDTO> bikes = new ArrayList<>();

    @GetMapping
    public List<BikeDTO> listar() {
        return bikes;
    }

    @PostMapping
    public BikeDTO registrar(@RequestBody BikeDTO dto) {
        dto.setCodigo(String.valueOf((int)(Math.random() * 90000 + 10000)));
        dto.setDataHora(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        bikes.add(dto);
        return dto;
    }

    @PostMapping("/retirar")
    public void retirar(@RequestBody BikeDTO dto) {
        bikes.removeIf(b -> b.getRa().equals(dto.getRa()) && b.getCodigo().equals(dto.getCodigo()));
    }
}
