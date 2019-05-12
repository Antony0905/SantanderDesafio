package com.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	public Cliente findOneByCodigoUsuario(Integer codigoUsuario);

	public Cliente findOneByEmail(String email);

}
