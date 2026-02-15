package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SongBankEntity;

@Repository
public interface SongBankRepository extends JpaRepository<SongBankEntity, Integer> {
	@Query(value = "SELECT s.id , s.artist , s.cover_artist , s.title , s.url , s.genre , s.post_time, s.event_id "
			+ "FROM songbank s ORDER BY s.artist", nativeQuery = true)
	List<SongBankEntity> getSongBankData();

	@Query(value = "SELECT s.id , s.artist , s.cover_artist,s.title,s.url,s.genre,s.post_time,s.event_id, event.event_name "
			+ "FROM songbank s inner join event on s.event_id = event.id "
			+ "where s.artist LIKE CONCAT('%', :keyword , '%') or "
			+ "s.cover_artist LIKE CONCAT('%', :keyword , '%') or "
			+ "s.title LIKE CONCAT('%', :keyword , '%') or "
			+ "s.genre LIKE CONCAT('%', :keyword , '%') or "
			+ "event.event_name LIKE CONCAT('%', :keyword , '%')"
			+ "ORDER BY s.artist", nativeQuery = true)
	List<SongBankEntity> getSongBankSearchData(@Param("keyword") String keyword);

}
