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
import com.sena.adso.teamnoche.dtos.ProfesorMateriaDatatableDto;
import com.sena.adso.teamnoche.entity.Profesor;
import com.sena.adso.teamnoche.entity.ProfesorMateria;
import com.sena.adso.teamnoche.interfaces.IProfesorMateriaService;

@RestController
@RequestMapping("profesoresmaterias")
public class ProfesorMateriaController {

	@Autowired
	private IProfesorMateriaService service;
	
	
	@GetMapping("/datatable")
	public ResponseEntity<?>datable(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")
	Integer size,@RequestParam(name = "column_order")String columnOrder,
	@RequestParam(name = "column_direction")String columnDirection,
	@RequestParam(name = "search",required = false)String search){
		try {
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(columnDirection.equals("asc")? Direction.ASC : Direction.DESC, columnOrder));
			Page<ProfesorMateriaDatatableDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search ==null? "" : search);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping 
	public ResponseEntity<ApiResponseDto<List<ProfesorMateria>>> getAll(){
		
		try {
			List<ProfesorMateria> profesormateria =service.getAll();
			return ResponseEntity.ok(new ApiResponseDto<List<ProfesorMateria>>("Ok", true, profesormateria));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<ProfesorMateria>>(e.getMessage(), false, null));
		}
		
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<Optional<ProfesorMateria>>>getById(@PathVariable Long id){
		try {
			Optional<ProfesorMateria> profesormateria =service.getById(id);
			return ResponseEntity.ok(new ApiResponseDto<Optional<ProfesorMateria>>("Ok", true, profesormateria));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Optional<ProfesorMateria>>(e.getMessage(), false, null));
		}
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<ProfesorMateria>>save(@RequestBody ProfesorMateria profesormateria){
		try {
			ProfesorMateria profesormateriaDatabase = service.save(profesormateria);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateria>("Ok", true, profesormateriaDatabase));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateria>(e.getMessage(), false, null));
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ApiResponseDto<ProfesorMateria>>update(@PathVariable Long id, @RequestBody ProfesorMateria profesormateria){
		try {
			service.update(id, profesormateria);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateria>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateria>(e.getMessage(), false, null));
		}
	}
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponseDto<ProfesorMateria>>delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(new ApiResponseDto<ProfesorMateria>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ProfesorMateria>(e.getMessage(), false, null));
		}
	}
	
}
