package com.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.domain.Transacoes;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Integer> {

	public Transacoes findOneById(Integer id);

}
