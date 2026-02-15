package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SongBankDto;
import com.example.demo.dto.SongBankSearchKeywordDto;
import com.example.demo.dto.VcDto;
import com.example.demo.entity.CollaboratorEntity;
import com.example.demo.entity.SongBankEntity;
import com.example.demo.repository.CollaboratorRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.SongBankRepository;
import com.example.demo.repository.VocaloidRepository;

/**
 * ソングバンク機能
 * イベント等で投稿された曲をまとめる機能
 * 
 */

@RestController
public class SongBankController {

	@Autowired
	public SongBankRepository songbankrepository;

	@Autowired
	public CollaboratorRepository collaboratorrepository;

	@Autowired
	public VocaloidRepository vocaloidrepository;

	@Autowired
	public EventRepository eventrepository;

	@Autowired
	public GenreRepository genrerepository;

	@CrossOrigin(origins = "*")
	@GetMapping("/songbank")
	public SongBankDto songbank() {
	    // 1. メインの曲データを取得
	    List<SongBankEntity> songBankData = songbankrepository.getSongBankData();
	    List<SongBankDto.SongItem> itemList = new ArrayList<>();

	    for (SongBankEntity sb : songBankData) {
	        List<String> eventSearchList = new ArrayList<>();
	        
	        //ボーカロイドリストを取得
	        List<String> vocaloidList = vocaloidrepository.getVocaloids(sb.getId());
	        
	        //コラボレータリストを取得
	        List<CollaboratorEntity> collaboratorList = collaboratorrepository.getCollaborator(sb.getId());
	        
	        // 3. イベントリストを取得
	        String rawEventId = sb.getEvent_id();
	        if (rawEventId != null && !rawEventId.isEmpty()) {

	            String[] eventIdList = rawEventId.split(",");
	            for (String id : eventIdList) {
	                // 空文字（",,"のようなデータ）対策
	                if (!id.trim().isEmpty()) {
	                	Integer intId = Integer.parseInt(id);
	                    String eventName = eventrepository.getEvents(intId);
	                    if (eventName != null) {
	                        eventSearchList.add(eventName);
	                    }
	                }
	            }
	        }

	        SongBankDto.SongItem item = new SongBankDto.SongItem(sb, vocaloidList, eventSearchList,collaboratorList);
	        itemList.add(item); 
	    }
	    
	    return new SongBankDto(itemList);
	}


	@CrossOrigin(origins = "*")
	@GetMapping("/songbankSearchKeyword")
	public SongBankSearchKeywordDto songbankSearchKeyword() {

		List<SongBankSearchKeywordDto.SearchItem> SearchList = new ArrayList<>();

		//ボーカロイド検索用キーワードを取得
		List<String> vocaloidSearchList = vocaloidrepository.getVocaloidGroup();

		//ジャンル用キーワードを取得
		List<String> genreSearchList = genrerepository.getGenreGroup();

		//イベント用キーワードを取得
		List<String> eventSearchList = eventrepository.getEventGroup();

		SongBankSearchKeywordDto.SearchItem item = new SongBankSearchKeywordDto.SearchItem(vocaloidSearchList,
				genreSearchList, eventSearchList);
		SearchList.add(item);

		return new SongBankSearchKeywordDto(SearchList);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/getVCList/{songId}")
	public VcDto getVCList(@PathVariable Integer songId) {
		List<VcDto.SearchItem> VcList = new ArrayList<>();

		// コラボレータ取得
		List<CollaboratorEntity> collaboratorList = collaboratorrepository.getCollaborator(songId);

		// ボーカロイド取得
		List<String> vocaloidList = vocaloidrepository.getVocaloids(songId);

		VcDto.SearchItem item = new VcDto.SearchItem(collaboratorList, vocaloidList);
		VcList.add(item);

		return new VcDto(VcList);
	}

}
