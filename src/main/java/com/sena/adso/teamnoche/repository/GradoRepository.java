package com.sena.adso.teamnoche.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sena.adso.teamnoche.dtos.GradoDatatableDto;
import com.sena.adso.teamnoche.entity.Grado;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long>{
@Query(value = " SELECT * FROM grados WHERE deleted_at IS NULL", nativeQuery = true)
	List<Grado>findAllCustom();

@Query(value="SELECT id, nombre FROM grados " + "WHERE nombre LIKE CONCAT('%', :searchText, '%') ", nativeQuery = true)
Page<GradoDatatableDto> getDatatable(Pageable pageable, String searchText);

@Query(value = " SELECT * FROM grados  WHERE id = :id AND deleted_at IS NULL", nativeQuery = true)
	Optional<Grado>findByIdCustom(@Param("id")Long id);
}
