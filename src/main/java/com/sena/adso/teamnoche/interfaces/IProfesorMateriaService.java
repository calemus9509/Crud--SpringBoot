package com.sena.adso.teamnoche.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sena.adso.teamnoche.dtos.ProfesorMateriaDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateria;



public interface IProfesorMateriaService {
	
	//consulta los datos del datatable
	Page<ProfesorMateriaDatatableDto> getDatatable(Pageable pageable, String searchText);
	
	//consultar todos 
	List<ProfesorMateria>getAll();
	
	//consultar  por id
	Optional<ProfesorMateria> getById(Long id)throws Exception;
	
	//guardar grado
	ProfesorMateria save(ProfesorMateria profesormateria);
	
	//actualizar un registro por 1 id
	void update(Long id, ProfesorMateria profesormateria) throws Exception;
	
	//eliminar por id
	void delete(Long id)throws Exception;
}
