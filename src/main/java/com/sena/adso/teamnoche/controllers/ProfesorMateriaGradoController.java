package com.sena.adso.teamnoche.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.adso.teamnoche.dtos.ApiResponseDto;
import com.sena.adso.teamnoche.dtos.ProfesorMateriaGradoDatatableDto;
import com.sena.adso.teamnoche.entity.ProfesorMateria;
import com.sena.adso.teamnoche.entity.ProfesorMateriaGrado;
import com.sena.adso.teamnoche.interfaces.IProfesorMateriaGradoService;
import com.sena.adso.teamnoche.services.ProfesorMateriaGradoService;

@RestController
@RequestMapping("profesoresmateriasgrados")
public class ProfesorMateriaGradoController {
	@Autowired
	private IProfesorMateriaGradoService service;
	
	@GetMapping("/datatable")
	public ResponseEntity<?>datable(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")
	Integer size,@RequestParam(name = "column_order")String columnOrder,
	@RequestParam(name = "column_direction")String columnDirection,
	@RequestParam(name = "search",required = false)String search){
		try {
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(columnDirection.equals("asc")? Direction.ASC : Direction.DESC, columnOrder));
			Page<ProfesorMateriaGradoDatatableDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search ==null? "" : search);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping 
	public ResponseEntity<ApiResponseDto<List<ProfesorMateriaGrado>>> getAll(){
		
		try {
			List<ProfesorMateriaGrado> Profesormateriagrado =service.getAll();
			return ResponseEntity.ok(new ApiResponseDto<List<ProfesorMateriaGrado>>("Ok", true, Profesormateriagrado));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<ProfesorMateriaGrado>>(e.getMessage(), false, null));
		}
		
	}
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<ProfesorMateriaGrado>>getById(@PathVariable Long id){
		try {
		ProfesorMateriaGrado profesormateriagrado =service.getById(id);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateriaGrado>("Ok", true, profesormateriagrado));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateriaGrado>(e.getMessage(), false, null));
		}
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<ProfesorMateriaGrado>>save(@RequestBody ProfesorMateriaGrado profesormateriagrado){
		try {
			ProfesorMateriaGrado profesormateriagradoDatabase = service.save(profesormateriagrado);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateriaGrado>("Ok", true, profesormateriagradoDatabase));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateriaGrado>(e.getMessage(), false, null));
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ApiResponseDto<ProfesorMateriaGrado>>update(@PathVariable Long id, @RequestBody ProfesorMateriaGrado profesormateriagrado){
		try {
			service.update(id, profesormateriagrado);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateriaGrado>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateriaGrado>(e.getMessage(), false, null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponseDto<ProfesorMateriaGrado>>delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateriaGrado>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateriaGrado>(e.getMessage(), false, null));
		}
	}
	

}
