package com.sena.adso.teamnoche.dtos;

public class ApiResponseDto<T> {

	private String message;
	
	private Boolean status;
	
	private T data;
	
	

	public ApiResponseDto() {

	}
	
	

	public ApiResponseDto(String message, Boolean status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
