package com.sena.adso.teamnoche.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.adso.teamnoche.dtos.ProfesorMateriaDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateria;
import com.sena.adso.teamnoche.interfaces.IProfesorMateriaService;
import com.sena.adso.teamnoche.repository.ProfesorMateriaRepository;

@Service
public class ProfesorMateriaService implements IProfesorMateriaService{
	
	@Autowired 
	private ProfesorMateriaRepository repository;
	
	@Override
	public Page<ProfesorMateriaDatatableDto> getDatatable(Pageable pageable, String searchText){
		return repository.getDatatable(pageable, searchText);
	}

	@Override
	public List<ProfesorMateria> getAll() {
		// TODO Auto-generated method stub
		return repository.findAllCustom();
	}

	@Override
	public ProfesorMateria getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProfesorMateria>profesormateriaO = repository.findByIdCustom(id);
		if(profesormateriaO.isEmpty())throw new Exception("no existe");
		return profesormateriaO.get();
	}

	@Override
	public ProfesorMateria save(ProfesorMateria profesormateria) {
		// TODO Auto-generated method stub
		return repository.save(profesormateria);
	}

	@Override
	public void update(Long id, ProfesorMateria profesormateria) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProfesorMateria>profesormateriaO = repository.findByIdCustom(id);
		if(profesormateriaO.isEmpty())throw new Exception("ha sido modificada");
		ProfesorMateria profesormateriaDatabase = profesormateriaO.get();
		profesormateriaDatabase.setProfesor(profesormateriaDatabase.getProfesor());
		profesormateriaDatabase.setMateria(profesormateriaDatabase.getMateria());
		repository.save(profesormateriaDatabase);
		
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProfesorMateria>profesormateriaO = repository.findByIdCustom(id);
		if(profesormateriaO.isEmpty())throw new Exception("ha sido eliminado");
		ProfesorMateria profesormateriaDatabase = profesormateriaO.get();
		repository.save(profesormateriaDatabase);
		
		
	}
		
	

}
