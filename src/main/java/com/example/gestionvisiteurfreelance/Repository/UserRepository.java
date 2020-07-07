package com.example.gestionvisiteurfreelance.Repository;
import com.example.gestionvisiteurfreelance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    void deleteByCompanyClient_CompanyId(Long companyId);

    User findByCompanyClient_CompanyId(Long companyId);
}
