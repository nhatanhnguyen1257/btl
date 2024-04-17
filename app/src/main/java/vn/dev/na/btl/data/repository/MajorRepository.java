package vn.dev.na.btl.data.repository;

import android.content.Context;

import androidx.room.Query;

import java.util.List;

import vn.dev.na.btl.business.exception.MajorException;
import vn.dev.na.btl.data.QLHPDatabase;
import vn.dev.na.btl.data.entity.Major;

public class MajorRepository {

    private QLHPDatabase database;

    public MajorRepository(Context context) {
        database = QLHPDatabase.getDatabase(context);
    }

    public int checkExitMajor(){
        return database.majorDAO().checkExitMajor();
    }

    public Major add(String name, String code, String note) throws MajorException {
        check(name, code, null);

        Major major = new Major();
        major.setName(name);
        major.setCode(code);
        major.setNote(note);

        database.majorDAO().insert(major);

        return database.majorDAO().findByCode(code);
    }

    public List<Major> lstMajor() {
        return database.majorDAO().all();
    }

    public Major findById(Integer id) {
        return database.majorDAO().findByCode(id);
    }

    public void deleteByMajorId(int id) {
        database.majorDAO().deleteByMajorId(id);
    }

    public void update(Integer id, String name, String code, String note) throws MajorException {
        check(name, code, id);
        Major major = new Major();
        major.setId(id);
        major.setName(name);
        major.setCode(code);
        major.setNote(note);
        database.majorDAO().update(major);
    }

    private void check(String name, String code, Integer id) throws MajorException {
        if (id == null) {
            if (database.majorDAO().checkExitMajorByCode(code) > 0) {
                throw new MajorException("Mã ngành học đã tồn tại, vui lòng nhập mã khác");
            }
            if (database.majorDAO().checkExitMajorByName(name) > 0) {
                throw new MajorException("Tên môn học đã tồn tại");
            }
        }
        else {
            if (database.majorDAO().checkExitMajorByCode(code, id) > 0) {
                throw new MajorException("Mã ngành học đã tồn tại, vui lòng nhập mã khác");
            }
            if (database.majorDAO().checkExitMajorByName(name, id) > 0) {
                throw new MajorException("Tên môn học đã tồn tại");
            }
        }

    }

    public List<Major> findByName(String name) {
        return database.majorDAO().findByName(name);
    }


    public Integer findIdByName(String name) {
        return database.majorDAO().findIdByName(name);
    }

    public List<String> getAllName() {
        return database.majorDAO().getAllName();
    }

    public List<Major> findAllBySubjectId(Integer subjectId) {
        return database.majorDAO().findAllBySubjectId(subjectId);
    }
}
