package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.CollaboratorEntity;
import com.example.demo.entity.SongBankEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

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
        private String artist;
        private String cover_artist;
        private String title;
        private String url;
        private String genre;
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tokyo")
        private Date post_time;
        private String event_id;
        private List<String>vocaloid;
        private List<String>event;
        private List<CollaboratorEntity> collaborator;
        
    	private static final String NICONICO_URL = "https://ext.nicovideo.jp/thumb/";
    	private static final String YOUTUBE_URL = "https://www.youtube.com/embed/";

        public SongItem(SongBankEntity entity,List<String> vcList, List<String> esList,List<CollaboratorEntity> cbList) {
            this.id = entity.getId();
            this.artist = entity.getArtist();
            this.cover_artist = entity.getCover_artist();
            this.title = entity.getTitle();
            this.url = urlJoin(entity.getUrl());
            this.genre = entity.getGenre();
            this.post_time = entity.getPost_time();
            this.event_id = entity.getEvent_id();
            this.vocaloid = vcList;
            this.event = esList;
            this.collaborator = cbList;
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