package com.sena.adso.teamnoche.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.teamnoche.dtos.ProfesorMateriaGradoDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateriaGrado;

public interface ProfesorMateriaGradoRepository extends JpaRepository<ProfesorMateriaGrado, Long> {

	@Query(value = "SELECT * FROM profesores_materias_grados WHERE deleted_at IS NULL",nativeQuery = true)
	List<ProfesorMateriaGrado>findAllCustom();
	
	@Query(value="SELECT id, nombre FROM profesores_materias_grados " + "WHERE nombre LIKE CONCAT('%', :searchText, '%') ", nativeQuery = true)
	Page<ProfesorMateriaGradoDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	@Query(value = "SELECT * FROM profesores_materias_grados WHERE id =:id AND deleted_at is Null",nativeQuery = true)
	Optional<ProfesorMateriaGrado>findByIdCustom(@Param("id")Long id);
}
