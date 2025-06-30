package com.project.bicicoletivo.services;

import com.project.bicicoletivo.dto.CreateUserDTO;
import com.project.bicicoletivo.dto.UserDTO;
import com.project.bicicoletivo.entities.Bike;
import com.project.bicicoletivo.entities.Role;
import com.project.bicicoletivo.entities.User;
import com.project.bicicoletivo.projections.UserDetailsProjection;
import com.project.bicicoletivo.repositories.UserRepository;
import com.project.bicicoletivo.services.exceptions.AccessDeniedException;
import com.project.bicicoletivo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }

        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return repository.findByEmail(username).get();
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User entity = authenticated();
        return new UserDTO(entity);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(String name, Pageable pageable) {
        Page<User> result = repository.searchByName(name, pageable);
        return result.map(x -> new UserDTO(x));
    }

    @Transactional
    public UserDTO insert(CreateUserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        Bike bike = new Bike();
        bike.setMarca(dto.getMarcabike());
        bike.setModelo(dto.getModelobike());
        bike.setColor(dto.getCorbike());
        bike.setProprietary(entity);

        entity.getBikes().add(bike);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO updateBike(Long ra, Long bikeId, CreateUserDTO dto){
        User authenticatedUser = authenticated();
        if (authenticatedUser.getId() != ra) {
            throw new AccessDeniedException("Você não tem permissão para alterar esta bicicleta");
        }
        User entity = repository.findById(ra).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado"));

        Bike bike = entity.getBikes().stream()
                .filter(b -> b.getId().equals(bikeId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Bicicleta não encontrada"));

        bike.setMarca(dto.getMarcabike());
        bike.setModelo(dto.getModelobike());
        bike.setColor(dto.getCorbike());

        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    private void copyDtoToEntity(CreateUserDTO dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setRa(dto.getRa());


    }

}
