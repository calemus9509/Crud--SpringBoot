package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.adso.teamnoche.dtos.MateriaDatatableDto;
import com.sena.adso.teamnoche.entity.Materia;

public interface IMateriaService {
	
	//consulta los datos del datatable
	Page<MateriaDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	//consultar todas las materias
	List<Materia>getAll();
	//consultar materia por id
	Optional<Materia> getById(Long id)throws Exception;
	//guardar materia
	Materia save(Materia materia);
	//actualizar 1 registro por id
	void update(Long id, Materia materia)throws Exception;
	//eliminar id
	void delete(Long id)throws Exception;
}
