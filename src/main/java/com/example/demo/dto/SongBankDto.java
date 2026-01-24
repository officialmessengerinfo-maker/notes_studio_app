package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.CollaboratorEntity;
import com.example.demo.entity.SongBankEntity;

import lombok.Data;

@Data
public class SongBankDto {	
	private List<SongItem> data;

    public SongBankDto(List<SongItem> data) {
        this.data = data;
    }

    @Data
    public static class SongItem {
        private Integer id;
        private String title;
        private String artist;
        private String url;
        private List<CollaboratorEntity> collaborators;
        private List<String> vocaloids;        
        
    	private static final String NICONICO_URL = "https://ext.nicovideo.jp/thumb";
    	private static final String YOUTUBE_URL = "https://www.youtube.com/embed/";

        // EntityからDtoへ詰め替えるコンストラクタ
        public SongItem(SongBankEntity entity, List<CollaboratorEntity> colabs , List<String> vocalos) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.artist = entity.getArtist();
            // ここでURL加工！
            this.url = urlJoin(entity.getUrl());
            this.collaborators = colabs;
            this.vocaloids = vocalos;
        }
        
    	public String urlJoin(String url) {
    		if(url.startsWith("sm")) {
    			//ニコニコ動画のURL
    			url = NICONICO_URL + url ;
    		}else {
    			//YouTubeのURL
    			url = YOUTUBE_URL + url;
    		}
    		return url;
    	}
    }
    

}