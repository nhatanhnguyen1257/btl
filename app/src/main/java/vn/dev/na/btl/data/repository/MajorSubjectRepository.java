package vn.dev.na.btl.data.repository;

import android.content.Context;

import vn.dev.na.btl.business.exception.MajorException;
import vn.dev.na.btl.business.exception.SubjectException;
import vn.dev.na.btl.data.QLHPDatabase;
import vn.dev.na.btl.data.entity.MajorSubject;
import vn.dev.na.btl.data.entity.Subject;

public class MajorSubjectRepository {

    private QLHPDatabase database;

    public MajorSubjectRepository(Context context) {
        database = QLHPDatabase.getDatabase(context);
    }

    public void add(Integer subjectId, Integer majorId) throws SubjectException {
        MajorSubject majorSubject = new MajorSubject();
        majorSubject.setSubjectId(subjectId);
        majorSubject.setMajorId(majorId);
        database.majorSubjectDAO().insert(majorSubject);
    }

    public void deleteByMajorId(int id) {
        database.majorSubjectDAO().deleteByMajorId(id);
    }
}
