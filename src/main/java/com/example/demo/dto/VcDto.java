package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.CollaboratorEntity;

import lombok.Data;

@Data
public class VcDto {	
	private List<SearchItem> data;

    public VcDto(List<SearchItem> item) {
        this.data = item;
    }

    @Data
    public static class SearchItem {
        private List<CollaboratorEntity> collaborators;
        private List<String> vocaloids;        

        public SearchItem(List<CollaboratorEntity> colabs , List<String> vocalos) {
        	this.collaborators = colabs;
        	this.vocaloids = vocalos;
        }     
        
    }
    

    


}