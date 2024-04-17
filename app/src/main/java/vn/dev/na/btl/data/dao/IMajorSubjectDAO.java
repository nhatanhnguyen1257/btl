package vn.dev.na.btl.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.MajorSubject;

@Dao
public interface IMajorSubjectDAO {
    @PrimaryKey(autoGenerate = true)
    @Insert
    void insert(MajorSubject majorSubject);

    @Query("DELETE FROM major_subject WHERE major_id = :major_id")
    void deleteByMajorId(int major_id);
    @Query("DELETE FROM major_subject WHERE id = :id")
    void deleteById(Integer id);

    @Query("SELECT * FROM major_subject WHERE major_id = :majorId and subject_id = :subjectId ")
    MajorSubject findBySubjectAndMajorId(Integer subjectId, Integer majorId);
}
