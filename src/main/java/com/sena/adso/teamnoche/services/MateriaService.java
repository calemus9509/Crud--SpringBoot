package com.sena.adso.teamnoche.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sena.adso.teamnoche.dtos.MateriaDatatableDto;
import com.sena.adso.teamnoche.entity.Materia;
import com.sena.adso.teamnoche.interfaces.IMateriaService;
import com.sena.adso.teamnoche.repository.MateriaRepository;

@Service
public class MateriaService implements IMateriaService {
	@Autowired
	private MateriaRepository repository;
	
	@Override
	public Page<MateriaDatatableDto> getDatatable(Pageable pageable, String searchText){
		return repository.getDatatable(pageable, searchText);
	}


	@Override
	public List<Materia> getAll() {
		// TODO Auto-generated method stub
		return repository.findAllCustom();
	}

	@Override
	public Materia getById(Long id)throws Exception {
		// TODO Auto-generated method stub
		Optional<Materia>materiaO =repository.findByIdCustom(id);
		if(materiaO.isEmpty())throw new Exception("La materia no existe");
		return materiaO.get();
	}

	@Override
	public Materia save(Materia materia) {
		// TODO Auto-generated method stub
		materia.setCreatedAt(LocalDateTime.now());
		return repository.save(materia);
	}

	@Override
	public void update(Long id, Materia materia)throws Exception {
		// TODO Auto-generated method stub
		Optional<Materia>materiaO =repository.findById(id);
		if(materiaO.isEmpty())throw new Exception("La materia ha sido actualizada");
		Materia materiaDatabase =materiaO.get();
		materiaDatabase.setNombre(materia.getNombre());
		materiaDatabase.setUpdatedAt(LocalDateTime.now());
		repository.save(materiaDatabase);
		
	}

	@Override
	public void delete(Long id)throws Exception {
		// TODO Auto-generated method stub
		Optional<Materia>materiaO=repository.findById(id);
		if(materiaO.isEmpty())throw new Exception("La materia ha sido eliminada");
		Materia materiaDatabase =materiaO.get();
		materiaDatabase.setDeletedAt(LocalDateTime.now());
		repository.save(materiaDatabase);
		
		
		
	}
	
	
	
}
