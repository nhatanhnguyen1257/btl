package vn.dev.na.btl.data.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.Subject;

@Dao
public interface ISubjectDAO {

    @PrimaryKey(autoGenerate = true)
    @Insert
    void insert(Subject subject);
    @Query("SELECT count(1) FROM subject ")
    int checkExitSubject();

    @Query("SELECT* FROM subject where code =:code ")
    Subject findByCode(String code);

    @Query("SELECT count(1) FROM subject where code =:code ")
    int checkExitSubjectByCode(String code);

    @Query("SELECT count(1) FROM subject where name =:name ")
    int checkExitSubjectByName(String name);

    @Query("SELECT * FROM subject ")
    List<Subject> getAll();

    @Query("SELECT number FROM subject group by number")
    List<Integer> getAllNumber();

    @Query("SELECT * FROM subject where name like '%' ||:name||'%'")
    List<Subject> findByName(String name);

    @Query("SELECT * FROM subject where number = :number")
    List<Subject> findByNumber(Integer number);

    @Query("SELECT * FROM subject where id = :id")
    Subject findById(Integer id);

    @Query("SELECT * FROM subject where number = :number and name like '%' ||:name||'%'")
    List<Subject> findByNumberAndName(Integer number, String name);

    @Query("SELECT s.* FROM major m" +
            " INNER JOIN major_subject mb ON mb.major_id = m.id " +
            " INNER JOIN subject s ON s.id = mb.subject_id " +
            "where s.name like '%'|| :nameObject || '%' and s.number = :numberObj and m.name like '%'|| :nameMajor ||'%' ")
    List<Subject> findByNameObjAndNumberObjAndNameMajor(Integer numberObj, String nameMajor, String nameObject);

    @Query("SELECT s.* FROM major m" +
            " INNER JOIN major_subject mb ON mb.major_id = m.id " +
            " INNER JOIN subject s ON s.id = mb.subject_id " +
            "where s.name like '%'|| :nameObject || '%'  and m.name like '%'|| :nameMajor ||'%'")
    List<Subject> findByNameObjAndNameMajor( String nameMajor, String nameObject);

    @Query("update subject set name = :name where id =:subjectId ")
    void updateName(String name, Integer subjectId);
    @Query("update subject set note = :note where id =:subjectId ")
    void updateNote(String note, Integer subjectId);
    @Query("update subject set number =:number where id =:subjectId ")
    void updateNumber(Integer number, Integer subjectId);
    @Query("update subject set required = :isRequired where id =:subjectId ")
    void updateRequired(boolean isRequired, Integer subjectId);

    @Query("update subject set code = :code where id =:subjectId ")
    void updateCode(String code, Integer subjectId);
}
