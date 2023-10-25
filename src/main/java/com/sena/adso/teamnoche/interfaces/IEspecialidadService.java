package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.adso.teamnoche.dtos.EspecialidadDatatableDto;
import com.sena.adso.teamnoche.entity.Especialidad;

public interface IEspecialidadService {
	//consulta los datos del datatable
	Page<EspecialidadDatatableDto> getDatatable(Pageable pageable, String searchText);
	//consultar todad las especialidades
	List<Especialidad>getAll();
	//consultar especialidad por id
	Especialidad getByID(Long id) throws Exception;
	//guardar especialidad
	Especialidad save(Especialidad especialidad);
	// actualizar 1 registro por id
	void update(Long id, Especialidad especialidad) throws Exception;
	//eliminar registro por id
	void delete(Long id)throws Exception;
	
}