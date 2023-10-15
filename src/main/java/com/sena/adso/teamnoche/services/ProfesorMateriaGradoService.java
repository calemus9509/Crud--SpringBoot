package com.sena.adso.teamnoche.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sena.adso.teamnoche.dtos.ProfesorMateriaGradoDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateriaGrado;
import com.sena.adso.teamnoche.interfaces.IProfesorMateriaGradoService;
import com.sena.adso.teamnoche.repository.ProfesorMateriaGradoRepository;
@Service
public class ProfesorMateriaGradoService implements IProfesorMateriaGradoService {
	
	@Autowired
	private ProfesorMateriaGradoRepository repository;
	
	@Override
	public Page<ProfesorMateriaGradoDatatableDto> getDatatable(Pageable pageable, String searchText){
		return repository.getDatatable(pageable, searchText);
	}

	@Override
	public List<ProfesorMateriaGrado> getAll() {
		// TODO Auto-generated method stub
		return repository.findAllCustom();
	}

	@Override
	public ProfesorMateriaGrado getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProfesorMateriaGrado>profesormateriagradoO = repository.findByIdCustom(id);
		if(profesormateriagradoO.isEmpty())throw new Exception("no existe");
		return profesormateriagradoO.get();
	}

	@Override
	public ProfesorMateriaGrado save(ProfesorMateriaGrado profesormateriagrado) {
		// TODO Auto-generated method stub
		return repository.save(profesormateriagrado);
	}

	@Override
	public void update(Long id, ProfesorMateriaGrado profesormateriagrado) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProfesorMateriaGrado>profesormateriagradoO = repository.findByIdCustom(id);
		if(profesormateriagradoO.isEmpty())throw new Exception("ha sido actualizado");
		ProfesorMateriaGrado  profesormateriagradoDatabase = profesormateriagradoO.get();
		profesormateriagradoDatabase.setGrado(profesormateriagradoDatabase.getGrado());
		profesormateriagradoDatabase.setProfesor(profesormateriagradoDatabase.getProfesor());
		repository.save(profesormateriagradoDatabase);
		
		
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProfesorMateriaGrado>profesormateriagradoO = repository.findByIdCustom(id);
		if(profesormateriagradoO.isEmpty())throw new Exception("ha sido modificado");
		ProfesorMateriaGrado  profesormateriagradoDatabase = profesormateriagradoO.get();
		repository.save(profesormateriagradoDatabase);
		
		
	}

	
	
}
