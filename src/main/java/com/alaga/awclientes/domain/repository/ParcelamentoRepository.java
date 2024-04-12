package com.alaga.awclientes.domain.repository;

import com.alaga.awclientes.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {


}
