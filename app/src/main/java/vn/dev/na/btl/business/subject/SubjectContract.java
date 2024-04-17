package vn.dev.na.btl.business.subject;

import android.content.Context;

import java.util.List;

import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.Subject;

public interface SubjectContract {

    public interface ViewAdd {
        void showMessageError(String message);
        void showMessageOk();
        void showSpinnerMajor(List<String> lstName);
        void backLayoutLst();
    }

    public interface ViewList {
        void showNumber(List<String> lstNumber);
        void showListMajor(List<String> lstName);
        void showAll(List<Subject> lstSubject);
        void showDetail(Integer subjectId);

    }

    public interface ViewDetail {
        void showDetail(Subject subject);
        void showListMajor(List<Major> lstMajor);
    }

    public interface Presenter{
        default void setViewAdd(ViewAdd view) {

        }

        default void setViewLst(ViewList view) {

        }

        default void setViewDetail(ViewDetail view) {

        }
        void init();
        void setRepository(Context context);
//        void saveSubject(String name, String code, String note, String required, Integer quantity, Integer majorId);
        void save(String name, String code, String number, String note, boolean isRequired);
        void showAllSubject();
        void getAllNumber();
        void getAllNameMajor();
        void setSearchNameMajor(Integer position);
        void setSearchNumberObject(Integer position);
        void setSearchNameObject(String nameObject);
        void findSubjectById(Integer subjectId);
        void findMajorBySubjectId(Integer subjectId);
    }
}
