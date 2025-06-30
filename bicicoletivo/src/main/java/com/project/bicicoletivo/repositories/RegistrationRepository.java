package com.project.bicicoletivo.repositories;

import com.project.bicicoletivo.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegistrationRepository extends JpaRepository<Registration,Long> {

}
