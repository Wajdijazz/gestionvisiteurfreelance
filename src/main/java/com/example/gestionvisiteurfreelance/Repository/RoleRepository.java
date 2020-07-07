package com.example.gestionvisiteurfreelance.Repository;

import java.util.Optional;

import com.example.gestionvisiteurfreelance.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleDescription(String roleDescription);

}