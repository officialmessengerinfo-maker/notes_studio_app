package com.example.demo.dto;

import java.util.List;

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
		private List<String>vocaloidSearchList;

		//ジャンル用キーワードを取得
		private List<String>genreSearchList;
		
		//イベント用キーワードを取得
		private List<String>eventSearchList;

        public SearchItem(List<String> vsList, List<String> gsList , List<String> esList) {
        	this.vocaloidSearchList = vsList;
        	this.genreSearchList = gsList;
        	this.eventSearchList = esList;
        }
        

    }
    

}