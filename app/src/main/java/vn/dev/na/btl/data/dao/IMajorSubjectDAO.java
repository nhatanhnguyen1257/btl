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

    @Query("DELETE FROM major_subject WHERE major_id = :id")
    void deleteByMajorId(int id);
}
