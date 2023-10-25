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
import com.sena.adso.teamnoche.dtos.AprendizDatatableDto;
import com.sena.adso.teamnoche.entity.Aprendiz;
import com.sena.adso.teamnoche.interfaces.IAprendizService;

@RestController
@RequestMapping("aprendices")
public class AprendizController {
	
	@Autowired
	private IAprendizService service;
	
	@GetMapping("/datatable") 
	public ResponseEntity<?>datable(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")
	Integer size,@RequestParam(name = "column_order")String columnOrder,
	@RequestParam(name = "column_direction")String columnDirection,
	@RequestParam(name = "search",required = false)String search){
		try {
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(columnDirection.equals("asc")? Direction.ASC : Direction.DESC, columnOrder));
			Page<AprendizDatatableDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search ==null? "" : search);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping 
	public ResponseEntity<ApiResponseDto<List<Aprendiz>>> getAll(){
		
		try {
			List<Aprendiz> aprendiz =service.getAll();
			return ResponseEntity.ok(new ApiResponseDto<List<Aprendiz>>("Ok", true, aprendiz));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Aprendiz>>(e.getMessage(), false, null));
		}
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<Aprendiz>> getById(@PathVariable Long id){
		try {
			Aprendiz aprendiz =service.getById(id);
			return ResponseEntity.ok(new ApiResponseDto<Aprendiz>("Ok", true, aprendiz));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Aprendiz>(e.getMessage(), false, null));
		}
		
		
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<Aprendiz>>save(@RequestBody Aprendiz aprendiz){
		try {
			Aprendiz aprendizDatabase = service.save(aprendiz);
			return ResponseEntity.ok(new ApiResponseDto<Aprendiz>("Ok", true, aprendizDatabase));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Aprendiz>(e.getMessage(), false, null));
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ApiResponseDto<Aprendiz>>update(@PathVariable Long id, @RequestBody Aprendiz aprendiz){
		try {
			service.update(id, aprendiz);
			return ResponseEntity.ok(new ApiResponseDto<Aprendiz>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Aprendiz>(e.getMessage(), false, null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponseDto<Aprendiz>>delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(new ApiResponseDto<Aprendiz>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Aprendiz>(e.getMessage(), false, null));
		}
	}
}
