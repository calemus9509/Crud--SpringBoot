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
import com.sena.adso.teamnoche.dtos.MateriaDatatableDto;
import com.sena.adso.teamnoche.entity.Grado;
import com.sena.adso.teamnoche.entity.Materia;
import com.sena.adso.teamnoche.interfaces.IMateriaService;


@RestController
@RequestMapping("materias")
public class MateriaController {
	@Autowired
	private IMateriaService service;
	

	@GetMapping("/datatable")
	public ResponseEntity<?>datable(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")
	Integer size,@RequestParam(name = "column_order")String columnOrder,
	@RequestParam(name = "column_direction")String columnDirection,
	@RequestParam(name = "search",required = false)String search){
		try {
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(columnDirection.equals("asc")? Direction.ASC : Direction.DESC, columnOrder));
			Page<MateriaDatatableDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search ==null? "" : search);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping 
	public ResponseEntity<ApiResponseDto<List<Materia>>> getAll(){
		
		try {
			List<Materia> materias =service.getAll();
			return ResponseEntity.ok(new ApiResponseDto<List<Materia>>("Ok", true, materias));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Materia>>(e.getMessage(), false, null));
		}
		
	}
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<Materia>> getById(@PathVariable Long id){
		try {
		Materia materia =service.getById(id);
			return ResponseEntity.ok(new ApiResponseDto<Materia>("Ok", true, materia));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Materia>(e.getMessage(), false, null));
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<Materia>> save(@RequestBody Materia materia){
			try {
			Materia materiaDatabase = service.save(materia);
			return ResponseEntity.ok(new ApiResponseDto<Materia>("Ok", true, materiaDatabase));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Materia>(e.getMessage(), false, null));
		}
		
	
	}
	
	@PutMapping("{id}") 
	public ResponseEntity<ApiResponseDto<Materia>> update(@PathVariable Long id,@RequestBody Materia materia){
		try {
			service.update(id, materia);
			return ResponseEntity.ok(new ApiResponseDto<Materia>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Materia>(e.getMessage(), false, null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponseDto<Materia>> delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(new ApiResponseDto<Materia>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Materia>(e.getMessage(), false, null));
		}
	}
}
