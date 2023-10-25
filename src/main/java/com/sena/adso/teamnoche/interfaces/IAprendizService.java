package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.adso.teamnoche.dtos.AprendizDatatableDto;
import com.sena.adso.teamnoche.entity.Aprendiz;



public interface IAprendizService {
	//consulta los datos del datatable
	Page<AprendizDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	//consultar todos los profesores
		List<Aprendiz>getAll();
		
		//consultar profesor por id
		Aprendiz getById(Long id)throws Exception;
		
		//guardar profesor
		Aprendiz save(Aprendiz aprendiz);
		
		//actualizar un registro por 1 id
		void update(Long id, Aprendiz aprendiz) throws Exception;
		
		//eliminar por id
		void delete(Long id)throws Exception;

}
