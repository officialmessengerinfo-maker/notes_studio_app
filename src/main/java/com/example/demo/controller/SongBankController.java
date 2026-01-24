package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SongBankDto;
import com.example.demo.entity.CollaboratorEntity;
import com.example.demo.entity.SongBankEntity;
import com.example.demo.repository.CollaboratorRepository;
import com.example.demo.repository.SongBankRepository;
import com.example.demo.repository.VocaloidRepository;
/**
 * ソングバンク機能
 * イベント等で投稿された曲をまとめる機能
 * 
 */

@RestController
public class SongBankController{

	@Autowired
	public SongBankRepository songbankrepository;
	
	@Autowired
	public CollaboratorRepository collaboratorrepository;
	
	@Autowired
	public VocaloidRepository vocaloidrepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/songbank")
	public SongBankDto songbank() {

	    List<SongBankEntity> songBankData = songbankrepository.getSongBankData();
	    
	    List<SongBankDto.SongItem> itemList = new ArrayList<>();

	    for (SongBankEntity sb : songBankData) {
	        Integer song_id = sb.getId();
	        
	        // コラボレータ取得
	        List<CollaboratorEntity> collaboratorList = collaboratorrepository.getCollaborator(song_id);
	        
	        // ボーカロイド
	        List<String> vocaloidList = vocaloidrepository.getVocaloids(song_id);
	        
	        SongBankDto.SongItem item = new SongBankDto.SongItem(sb, collaboratorList, vocaloidList);
	        itemList.add(item);
	    }
	    
	    return new SongBankDto(itemList);
	}

}
