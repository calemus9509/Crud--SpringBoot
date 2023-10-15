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
import com.sena.adso.teamnoche.dtos.GradoDatatableDto;
import com.sena.adso.teamnoche.entity.Aprendiz;
import com.sena.adso.teamnoche.entity.Grado;
import com.sena.adso.teamnoche.interfaces.IGradoService;

@RestController
@RequestMapping("grados")
public class GradoController {
	@Autowired
	private IGradoService service;
	
	@GetMapping("/datatable")
	public ResponseEntity<?>datable(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")
	Integer size,@RequestParam(name = "column_order")String columnOrder,
	@RequestParam(name = "column_direction")String columnDirection,
	@RequestParam(name = "search",required = false)String search){
		try {
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(columnDirection.equals("asc")? Direction.ASC : Direction.DESC, columnOrder));
			Page<GradoDatatableDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search ==null? "" : search);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping 
	public ResponseEntity<ApiResponseDto<List<Grado>>> getAll(){
		
		try {
			List<Grado> grados =service.getAll();
			return ResponseEntity.ok(new ApiResponseDto<List<Grado>>("Ok", true, grados));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Grado>>(e.getMessage(), false, null));
		}
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<Optional<Grado>>>getById(@PathVariable Long id){
		try {
			Optional<Grado> grado =service.getById(id);
			return ResponseEntity.ok(new ApiResponseDto<Optional<Grado>>("Ok", true, grado));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Optional<Grado>>(e.getMessage(), false, null));
		}
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<Grado>>save(@RequestBody Grado grado){
		try {
			Grado gradoDatabase = service.save(grado);
			return ResponseEntity.ok(new ApiResponseDto<Grado>("Ok", true, gradoDatabase));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Grado>(e.getMessage(), false, null));
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ApiResponseDto<Grado>>update(@PathVariable Long id, @RequestBody Grado grado ){
		try {
			service.update(id, grado);
			return ResponseEntity.ok(new ApiResponseDto<Grado>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Grado>(e.getMessage(), false, null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponseDto<Grado>>delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(new ApiResponseDto<Grado>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Grado>(e.getMessage(), false, null));
		}
	}
}
