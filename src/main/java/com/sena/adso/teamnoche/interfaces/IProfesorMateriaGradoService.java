package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.adso.teamnoche.dtos.ProfesorMateriaGradoDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateriaGrado;

public interface IProfesorMateriaGradoService {
	
	//consulta los datos del datatable
	Page<ProfesorMateriaGradoDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	//consultar todos 
		List<ProfesorMateriaGrado>getAll();
		
		//consultar  por id
		ProfesorMateriaGrado getById(Long id)throws Exception;
		
		//guardar grado
		ProfesorMateriaGrado save(ProfesorMateriaGrado profesormateriagrado);
		
		//actualizar un registro por 1 id
		void update(Long id, ProfesorMateriaGrado profesormateriagrado) throws Exception;
		
		//eliminar por id
		void delete(Long id)throws Exception;

}
