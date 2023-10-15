package com.sena.adso.teamnoche.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.adso.teamnoche.interfaces.IEspecialidadService;
import com.sena.adso.teamnoche.repository.EspecialidadRepository;
import com.sena.adso.teamnoche.dtos.EspecialidadDatatableDto;
import com.sena.adso.teamnoche.entity.Especialidad;

@Service
public class EspecialidadService implements IEspecialidadService {
	
	@Autowired
	private EspecialidadRepository repository;
	
	@Override
	public Page<EspecialidadDatatableDto> getDatatable(Pageable pageable, String searchText){
		return repository.getDatatable(pageable, searchText);
	}

	@Override
	public List<Especialidad> getAll() {
		// TODO Auto-generated method stub
		return repository.findAllCustom();
	}

	@Override
	public Especialidad getByID(Long id) throws Exception{
		// TODO Auto-generated method stub
		Optional<Especialidad> especialidadO = repository.findByIdCustom(id);
		if (especialidadO.isEmpty()) throw new Exception("La especialidad no existe");
		return especialidadO.get();
	}

	@Override
	public Especialidad save(Especialidad especialidad) {
		// TODO Auto-generated method stub
		especialidad.setCreatedAt(LocalDateTime.now());
		return repository.save(especialidad);
	}

/*	1. necesito consultar el registro por id
	*2. validar si ese registro existe
	*3.actualizar los valores, parametros ->parametro BD
	*/
	@Override
	public void update(Long id, Especialidad especialidad)throws Exception {
		// TODO Auto-generated method stub
		Optional<Especialidad> especialidadO = repository.findById(id);
		if (especialidadO.isEmpty())throw  new Exception("La especialidad ha sido actualizada") ;
		
		Especialidad especialidadDatabase =especialidadO.get();
		especialidadDatabase.setNombre(especialidad.getNombre());
		especialidadDatabase.setUpdateAt(LocalDateTime.now());
		repository.save(especialidadDatabase);
		
	}
	
	/*1.consultar el registro por el id
	 * validar si existe el registro consultado
	 * enviamos a elmininar
	 * 
	 */
	@Override
	public void delete(Long id)  throws Exception{
		// TODO Auto-generated method stub
		Optional<Especialidad> especialidadO = repository.findById(id);
		if(especialidadO.isEmpty())throw new Exception("La especialidad no existe");
		Especialidad especialidadDatabase =especialidadO.get();
		especialidadDatabase.setDeletedAt(LocalDateTime.now());
		repository.save(especialidadDatabase);
	}

}
