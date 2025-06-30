package com.project.bicicoletivo.services;

import com.project.bicicoletivo.repositories.BikeRepository;
import com.project.bicicoletivo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private UserRepository userRepository;

    // falta implementar services
}
