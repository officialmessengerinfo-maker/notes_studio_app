package com.example.demo.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class SongBankSearchKeywordDto {	
	private List<SearchItem> data;

    public SongBankSearchKeywordDto(List<SearchItem> item) {
        this.data = item;
    }

    @Data
    public static class SearchItem {
		//ボーカロイド検索用キーワードを取得
		private List<Map<String,String>>vocaloidSearchList;

		//ジャンル用キーワードを取得
		private List<Map<String,String>>genreSearchList;
		
		//イベント用キーワードを取得
		private List<Map<String,String>>eventSearchList;

        public SearchItem(List<String> vsList, List<String> gsList , List<String> esList) {
        	this.vocaloidSearchList = changeMap(vsList);
        	this.genreSearchList = changeMap(gsList);
        	this.eventSearchList = changeMap(esList);
        }     
        
        public List<Map<String,String>>  changeMap(List<String> list) {
        	List<Map<String,String>> result = new ArrayList<>(); 
        	for(String li : list) {
        		Map<String , String> keywordMap = new HashMap<>();
        		keywordMap.put("name", li);
        		result.add(keywordMap);
        	}
        	return result;
        }
    }
    

    


}