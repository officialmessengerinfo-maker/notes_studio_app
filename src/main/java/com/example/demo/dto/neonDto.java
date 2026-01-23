package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.playingWithNeonEntity;

import lombok.Data;

@Data
public class neonDto {
	private List<playingWithNeonEntity> data;

	public neonDto(List<playingWithNeonEntity> data) {
		this.data = data;
	}
}