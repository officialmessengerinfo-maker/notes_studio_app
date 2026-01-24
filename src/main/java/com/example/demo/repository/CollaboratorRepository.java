package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CollaboratorEntity;

@Repository
public interface CollaboratorRepository extends JpaRepository<CollaboratorEntity, Integer> {
	@Query(value = "SELECT * FROM SONGBANK_COLLABORATOR WHERE SONG_ID = :id ORDER BY SONG_ID", nativeQuery = true)
	List<CollaboratorEntity> getCollaborator(@Param("id") Integer id);
}
