package ru.netology.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.entity.FileEntity;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE FileEntity f set f.filename = :newFilename WHERE f.filename = :filename")
    int editFileName(@Param("filename") String fileName, @Param("newFilename") String newFilename);

    @Query(value = "SELECT * FROM files LIMIT :limit", nativeQuery = true)
    List<FileEntity> getFiles(int limit);

}
