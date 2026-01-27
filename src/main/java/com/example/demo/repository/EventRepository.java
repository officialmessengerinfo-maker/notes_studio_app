package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VocaloidEntity;

@Repository
public interface EventRepository extends JpaRepository<VocaloidEntity, Integer> {

	//イベント用キーワードを取得
	@Query(value = "SELECT DISTINCT e.event_name FROM songbank sb " +
			"INNER JOIN event e ON sb.event_id = e.id ORDER BY e.event_name", nativeQuery = true)
	List<String> getEventGroup();
}
