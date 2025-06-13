package com.schoolSystem.repository;

import com.schoolSystem.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
