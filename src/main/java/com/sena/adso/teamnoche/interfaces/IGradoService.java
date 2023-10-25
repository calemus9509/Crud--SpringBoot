package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.adso.teamnoche.dtos.GradoDatatableDto;
import com.sena.adso.teamnoche.entity.Grado;



public interface IGradoService {
	
	//consulta los datos del datatable
	Page<GradoDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	//consultar todos los grados
			List<Grado>getAll();
			
			//consultar grado por id
			Grado getById(Long id)throws Exception;
			
			//guardar grado
			Grado save(Grado grado);
			
			//actualizar un registro por 1 id
			void update(Long id, Grado grado) throws Exception;
			
			//eliminar por id
			void delete(Long id)throws Exception;
}
