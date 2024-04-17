package vn.dev.na.btl.data.repository;

import android.content.Context;

import androidx.room.Query;

import java.util.List;

import vn.dev.na.btl.business.exception.MajorException;
import vn.dev.na.btl.business.exception.SubjectException;
import vn.dev.na.btl.data.QLHPDatabase;
import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.Subject;

public class SubjectRepository {

    private QLHPDatabase database;

    public SubjectRepository(Context context) {
        database = QLHPDatabase.getDatabase(context);
    }

    public int checkExitSubject(){
        return database.subjectDAO().checkExitSubject();
    }


    public Subject add(String name, String code, String note, Boolean required, Integer quantity) throws SubjectException {
        if (database.subjectDAO().checkExitSubjectByCode(code) > 0) {
            throw new SubjectException("Mã môn học đã tồn tại, hãy nhập mã khác");
        }

        if (database.subjectDAO().checkExitSubjectByName(name) > 0) {
            throw new SubjectException("Tên môn học đã tồn tại");
        }

        Subject subject = new Subject();
        subject.setName(name);
        subject.setCode(code);
        subject.setRequired(required);
        subject.setNumber(quantity);
        subject.setNote(note);
        database.subjectDAO().insert(subject);

        return database.subjectDAO().findByCode(code);
    }

    public List<Subject> getAll() {
        return database.subjectDAO().getAll();
    }

    public List<Integer> getAllNumber() {
        return database.subjectDAO().getAllNumber();
    }

    public List<Subject> findByName(String number){
        return database.subjectDAO().findByName(number);
    }

    public List<Subject> findByNameObjAndNumberObjAndNameMajor(Integer number, String nameMajor, String nameObject) {
        return database.subjectDAO().findByNameObjAndNumberObjAndNameMajor(number, nameMajor, nameObject);
    }

    public List<Subject> findByNameObjAndNameMajor( String nameMajor, String nameObject){
        return database.subjectDAO().findByNameObjAndNameMajor(nameMajor, nameObject);
    }

    public List<Subject> findByNumber(Integer number){
        return database.subjectDAO().findByNumber(number);
    }

    public List<Subject> findByNumberAndNameSubject(Integer number, String nameSubject){
        return database.subjectDAO().findByNumberAndName(number, nameSubject);
    }

    public Subject findById(Integer subjectId){
        return database.subjectDAO().findById(subjectId);
    }
}
