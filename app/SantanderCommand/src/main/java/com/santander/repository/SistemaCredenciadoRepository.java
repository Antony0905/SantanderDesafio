package com.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.domain.SistemaCredenciado;

@Repository
public interface SistemaCredenciadoRepository extends JpaRepository<SistemaCredenciado, Integer> {

	public SistemaCredenciado findOneByEmail(String email);

}
