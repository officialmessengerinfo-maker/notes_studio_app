package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.PlayingWithNeonEntity;

import lombok.Data;

@Data
public class NeonDto {
	private List<PlayingWithNeonEntity> data;

	public NeonDto(List<PlayingWithNeonEntity> data) {
		this.data = data;
	}
}