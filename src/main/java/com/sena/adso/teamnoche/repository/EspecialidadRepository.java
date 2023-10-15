package com.sena.adso.teamnoche.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.adso.teamnoche.dtos.EspecialidadDatatableDto;
import com.sena.adso.teamnoche.entity.Especialidad;


@Repository 
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long>  {
@Query(value ="SELECT * FROM especialidades WHERE deleted_at IS NULL", nativeQuery =true )
	List<Especialidad> findAllCustom();

@Query(value="SELECT id, nombre FROM especialidades " + "WHERE nombre LIKE CONCAT('%', :searchText, '%') ", nativeQuery = true)
Page<EspecialidadDatatableDto> getDatatable(Pageable pageable, String searchText);

@Query(value ="SELECT * FROM especialidades WHERE id = ?1 AND deleted_at IS NULL", nativeQuery = true)
Optional<Especialidad>findByIdCustom(Long id);
}

