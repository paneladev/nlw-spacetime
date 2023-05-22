package com.pdev.spacetime.repository;

import com.pdev.spacetime.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //desta forma o hibernate fara apenas uma querie para retornar o usuario com suas memorias
    @EntityGraph(attributePaths = "memories")
    User findDetailedById(Long id);

    User findBasicById(Long id);

}