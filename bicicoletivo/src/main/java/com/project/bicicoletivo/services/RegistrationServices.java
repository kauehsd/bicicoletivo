package com.project.bicicoletivo.services;

import com.project.bicicoletivo.repositories.BikeRepository;
import com.project.bicicoletivo.repositories.RegistrationRepository;
import com.project.bicicoletivo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationServices {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BikeRepository bikeRepository;

    // implementar services

}
