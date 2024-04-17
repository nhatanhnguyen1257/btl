package vn.dev.na.btl.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.Subject;

@Dao
public interface IMajorDAO {
    @PrimaryKey(autoGenerate = true)
    @Insert
    void insert(Major majors);

    @Query("SELECT count(1) FROM major ")
    int checkExitMajor();

    @Query("SELECT * FROM major where code =:code ")
    Major findByCode(String code);

    @Query("SELECT * FROM major where code =:id ")
    Major findByCode(Integer id);

    @Query("SELECT count(1) FROM major where code =:code ")
    int checkExitMajorByCode(String code);

    @Query("SELECT count(1) FROM major where name =:name ")
    int checkExitMajorByName(String name);

    @Query("SELECT count(1) FROM major where code =:code and id <> :id ")
    int checkExitMajorByCode(String code, Integer id);

    @Query("SELECT count(1) FROM major where name =:name and id <> :id ")
    int checkExitMajorByName(String name, Integer id);

    @Query("SELECT * FROM major ")
    List<Major> all();

    @Query("SELECT name FROM major group by name ")
    List<String> getAllName();

    @Query("SELECT id FROM major where name = :name ")
    Integer findIdByName(String name);

    @Query("DELETE FROM major WHERE ID = :id")
    void deleteByMajorId(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Major major);

    @Query("SELECT * FROM major where  name like '%' || :name || '%' or code like '%' || :name || '%' ")
    List<Major> findByName(String name);

    @Query("SELECT m.* FROM major m" +
            " INNER JOIN major_subject mb ON mb.major_id = m.id " +
            "where mb.subject_id = :subjectId ")
    List<Major> findAllBySubjectId(Integer subjectId);



}
