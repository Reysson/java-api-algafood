package com.algaworks.algafoodreysson.domain.repository;

import com.algaworks.algafoodreysson.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

}
