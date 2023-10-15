package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.adso.teamnoche.dtos.ProfesorDatatableDto;
import com.sena.adso.teamnoche.entity.Profesor;

public interface IProfesorService {
	//consulta los datos del datatable
	Page<ProfesorDatatableDto> getDatatable(Pageable pageable, String searchText);

	//consultar todos los profesores
	List<Profesor>getAll();
	
	//consultar profesor por id
	Optional<Profesor> getById(Long id)throws Exception;
	
	//guardar profesor
	Profesor save(Profesor profesor);
	
	//actualizar un registro por 1 id
	void update(Long id, Profesor profesor) throws Exception;
	
	//eliminar por id
	void delete(Long id)throws Exception;
}
