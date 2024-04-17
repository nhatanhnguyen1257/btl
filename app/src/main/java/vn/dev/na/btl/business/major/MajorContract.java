package vn.dev.na.btl.business.major;

import android.content.Context;

import java.util.List;

import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.data.entity.Major;

public interface MajorContract {

    public interface ViewAdd {
        void showMessageError(String message);
        void showMessageOk();
    }


    public interface ViewList {
        void showAll(List<Major> lst);
        void showDetail(Major major);
        void showMessageError(String message);
    }

    public interface Presenter{
        default void setViewAdd(MajorContract.ViewAdd view) {

        }

        default void setViewLst(MajorContract.ViewList view) {

        }

        void setRepository(Context context);
        void saveMajor(String name, String code, String note);
        void showAll();
        void deleteMajorById(Integer id);
        void update(Integer id, String name, String code, String note);
        void searchByName(String name);
    }
}
