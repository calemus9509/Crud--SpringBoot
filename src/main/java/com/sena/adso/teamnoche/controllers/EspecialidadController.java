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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sena.adso.teamnoche.dtos.EspecialidadDatatableDto;
import com.sena.adso.teamnoche.entity.Especialidad;
import com.sena.adso.teamnoche.interfaces.IEspecialidadService;

@RestController
@RequestMapping("especialidades")
@CrossOrigin("http://localhost:4200")
public class EspecialidadController {
	@Autowired
	private IEspecialidadService service;
	/*get -obtener
	 * post -enviar a guardar
	 * put- enviar a actualizar
	 * delete - eliminar
	 */
	
	@GetMapping("/datatable")
	public ResponseEntity<?>datable(@RequestParam(name = "page")Integer page,@RequestParam(name = "size")
	Integer size,@RequestParam(name = "column_order")String columnOrder,
	@RequestParam(name = "column_direction")String columnDirection,
	@RequestParam(name = "search",required = false)String search){
		try {
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(columnDirection.equals("asc")? Direction.ASC : Direction.DESC, columnOrder));
			Page<EspecialidadDatatableDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search ==null? "" : search);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping 
	public ResponseEntity<ApiResponseDto<List<Especialidad>>> getAll(){
		
		try {
			List<Especialidad> especialidades =service.getAll();
			return ResponseEntity.ok(new ApiResponseDto<List<Especialidad>>("Ok", true, especialidades));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Especialidad>>(e.getMessage(), false, null));
		}
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<Optional<Especialidad>>> getById(@PathVariable Long id){
		try {
			Optional<Especialidad> especialidad =service.getByID(id);
			return ResponseEntity.ok(new ApiResponseDto<Optional<Especialidad>>("Ok", true, especialidad));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Optional<Especialidad>>(e.getMessage(), false, null));
		}
		
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDto<Especialidad>>save(@RequestBody Especialidad especialidad){
		try {
			Especialidad especialidadDatabase = service.save(especialidad);
			return ResponseEntity.ok(new ApiResponseDto<Especialidad>("Ok", true, especialidadDatabase));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Especialidad>(e.getMessage(), false, null));
		}
		
		/*
		 * actualizar - put enviar id por url y el
		 *  parametro por el cuerpo de la peticion, como post mapping
		 * eliminar - delete
		 */
	}
	
	@PutMapping("{id}") 
	public ResponseEntity<ApiResponseDto<Especialidad>> update(@PathVariable Long id,@RequestBody Especialidad especialidad){
		try {
			service.update(id, especialidad);
			return ResponseEntity.ok(new ApiResponseDto<Especialidad>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Especialidad>(e.getMessage(), false, null));
		}
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponseDto<Especialidad>> delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(new ApiResponseDto<Especialidad>("Ok", true, null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Especialidad>(e.getMessage(), false, null));
		}
	}
	
	
	}
