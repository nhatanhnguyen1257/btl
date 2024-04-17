package vn.dev.na.btl.business.major;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import vn.dev.na.btl.business.exception.MajorException;
import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.repository.MajorRepository;
import vn.dev.na.btl.data.repository.MajorSubjectRepository;
import vn.dev.na.btl.data.repository.SubjectRepository;

public class MajorPresenter implements MajorContract.Presenter{

    private MajorContract.ViewAdd mViewAdd;
    private MajorContract.ViewList mViewLst;
    private MajorRepository mMajorRepository;
    private SubjectRepository mSubjectRepository;
    private MajorSubjectRepository mMajorSubjectRepository;

    @Override
    public void setViewAdd(MajorContract.ViewAdd view) {
        mViewAdd = view;
    }


    @Override
    public void setViewLst(MajorContract.ViewList view) {
        mViewLst = view;
    }

    @Override
    public void setRepository(Context context) {
        mMajorRepository = new MajorRepository(context);
        mSubjectRepository = new SubjectRepository(context);
        mMajorSubjectRepository = new MajorSubjectRepository(context);
    }

    @Override
    public void saveMajor(String name, String code, String note) {
        if (name.isEmpty()) {
            mViewAdd.showMessageError("Tên chuyên ngành không được để trống");
        }
        if (code.isEmpty()) {
            mViewAdd.showMessageError("Mã chuyên ngánh không được để trống");
        }
        try {
            mMajorRepository.add(name, code, note);
            mViewAdd.showMessageOk();
            showAll();
        } catch (MajorException ex) {
            mViewAdd.showMessageError(ex.getMessage());
        }
    }

    @Override
    public void showAll() {
        mViewLst.showAll(mMajorRepository.lstMajor());
    }

    @Override
    public void deleteMajorById(Integer id) {
        mMajorRepository.deleteByMajorId(id);
        mMajorSubjectRepository.deleteByMajorId(id);
        showAll();
    }

    @Override
    public void update(Integer id, String name, String code, String note) {
        try {
            mMajorRepository.update(id, name, code, note);
            showAll();
        } catch (MajorException ex) {
            mViewLst.showMessageError(ex.getMessage());
        }
    }

    @Override
    public void searchByName(String name) {
        List<Major> lst = mMajorRepository.findByName(name);
        mViewLst.showAll(lst);
    }

}
