package com.pdev.spacetime.repository;

import com.pdev.spacetime.entity.Memory;
import com.pdev.spacetime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findByUserOrIsPublicTrue(User user);
}
