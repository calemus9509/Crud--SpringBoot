package com.sena.adso.teamnoche.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.adso.teamnoche.dtos.AprendizDatatableDto;
import com.sena.adso.teamnoche.entity.Aprendiz;
import com.sena.adso.teamnoche.interfaces.IAprendizService;
import com.sena.adso.teamnoche.repository.AprendizRepository;
@Service
public class AprendizService implements IAprendizService {
	@Autowired
	private AprendizRepository repository;
	
	@Override
	public Page<AprendizDatatableDto> getDatatable(Pageable pageable, String searchText){
		return repository.getDatatable(pageable, searchText);
	}

	@Override
	public List<Aprendiz> getAll() {
		// TODO Auto-generated method stub
		return repository.findAllCustom();
	}

	@Override
	public Aprendiz getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Aprendiz>aprendizO = repository.findByIdCustom(id);
		if(aprendizO.isEmpty()) throw new Exception("el aprendiz no existe");
		return aprendizO.get();
	}

	@Override
	public Aprendiz save(Aprendiz aprendiz) {
		// TODO Auto-generated method stub
		aprendiz.setCreatedAt(LocalDateTime.now());
		return repository.save(aprendiz);
	}

	@Override
	public void update(Long id, Aprendiz aprendiz) throws Exception {
		// TODO Auto-generated method stub
		Optional<Aprendiz>aprendizO =repository.findByIdCustom(id);
		if(aprendizO.isEmpty()) throw new Exception("el aprendiz ha sido actualizado");
		Aprendiz aprendizDatabase = aprendizO.get();
		aprendizDatabase.setNombres(aprendiz.getNombres());
		aprendizDatabase.setApellidos(aprendiz.getApellidos());
		aprendizDatabase.setGrado(aprendiz.getGrado());
		aprendizDatabase.setCreatedAt(LocalDateTime.now());
		repository.save(aprendizDatabase);
		
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Aprendiz>aprendizO = repository.findById(id);
		if(aprendizO.isEmpty()) throw new Exception("El aprendiz ha sido eliminado");
		Aprendiz aprendizDatabase = aprendizO.get();
		aprendizDatabase.setDeletedAt(LocalDateTime.now());
		repository.save(aprendizDatabase);
		
	}

	
}
