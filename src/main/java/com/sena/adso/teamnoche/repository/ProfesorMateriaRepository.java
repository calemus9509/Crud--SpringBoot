package com.sena.adso.teamnoche.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.teamnoche.dtos.ProfesorMateriaDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateria;

public interface ProfesorMateriaRepository extends JpaRepository<ProfesorMateria, Long> {

	@Query(value = "SELECT * FROM profesores_materias WHERE deleted_at IS NULL",nativeQuery = true)
	List<ProfesorMateria>findAllCustom();
	
	@Query(value="SELECT id, nombre FROM profesores_materias " + "WHERE nombre LIKE CONCAT('%', :searchText, '%') ", nativeQuery = true)
	Page<ProfesorMateriaDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	@Query(value = "SELECT * FROM profesores_materias WHERE id =:id AND deleted_at is Null",nativeQuery = true)
	Optional<ProfesorMateria>findByIdCustom(@Param("id")Long id);
}
