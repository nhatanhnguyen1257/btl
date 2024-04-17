package vn.dev.na.btl.business.subject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import vn.dev.na.btl.business.exception.MajorException;
import vn.dev.na.btl.business.exception.SubjectException;
import vn.dev.na.btl.data.entity.Subject;
import vn.dev.na.btl.data.repository.MajorRepository;
import vn.dev.na.btl.data.repository.MajorSubjectRepository;
import vn.dev.na.btl.data.repository.SubjectRepository;

public class PresenterSubject implements SubjectContract.Presenter{

    private final static String NAME_DEFAULT = "Tất cả";
    private String searchNameMajor;
    private String searchNumberObject;
    private String searchNameObject;
    private List<String> lstNameMajor = new ArrayList<>();
    private List<String> lstNumberObj = new ArrayList<>();

    private SubjectContract.ViewAdd mViewAdd;
    private SubjectContract.ViewList mViewLst;
    private SubjectContract.ViewDetail mViewDetail;

    private SubjectRepository mSubjectRepository;
    private MajorRepository mMajorRepository;
    private MajorSubjectRepository mMajorSubjectRepository;

    @Override
    public void setViewAdd(SubjectContract.ViewAdd view) {
        mViewAdd = view;
    }

    @Override
    public void setViewLst(SubjectContract.ViewList view) {
        mViewLst = view;
    }

    @Override
    public void setViewDetail(SubjectContract.ViewDetail view) {
        mViewDetail = view;
    }

    @Override
    public void init() {
        mViewAdd.showSpinnerMajor(mMajorRepository.getAllName());
    }

    @Override
    public void setRepository(Context context) {
        mSubjectRepository = new SubjectRepository(context);
        mMajorRepository = new MajorRepository(context);
        mMajorSubjectRepository = new MajorSubjectRepository(context);
    }

    @Override
    public void save(String name, String code, String quantity, String note, boolean isRequired) {
        if (name.isEmpty()) {
            mViewAdd.showMessageError("Môn học không được để trống");
            return;
        }
        if (code.isEmpty()) {
            mViewAdd.showMessageError("Mã môn học không được để trống");
            return;
        }

        if (quantity.isEmpty() || quantity.equals("0")) {
            mViewAdd.showMessageError("Số tin chỉ phải lớn hơn 0");
            return;
        }

        try {
            mSubjectRepository.add(name, code, note, true, Integer.valueOf(quantity));
            mViewAdd.showMessageOk();
            mViewAdd.backLayoutLst();
            mViewLst.showAll(mSubjectRepository.getAll());
        } catch (SubjectException ex) {
            mViewAdd.showMessageError(ex.getMessage());
        }
    }

    @Override
    public void showAllSubject() {
        mViewLst.showAll(mSubjectRepository.getAll());
    }

    @Override
    public void getAllNumber() {
        lstNumberObj.clear();
        lstNumberObj = new ArrayList<>();
        lstNumberObj.add(NAME_DEFAULT);
        lstNumberObj.addAll(mSubjectRepository.getAllNumber().stream().map(Objects::toString).collect(Collectors.toList()));
        mViewLst.showNumber(lstNumberObj);

    }

    @Override
    public void getAllNameMajor() {
        lstNameMajor.clear();
        lstNameMajor= new ArrayList<>();
        lstNameMajor.add(NAME_DEFAULT);
        lstNameMajor.addAll( mMajorRepository.getAllName());
        mViewLst.showListMajor(lstNameMajor);
    }

    @Override
    public void setSearchNameMajor(Integer position) {
        searchNameMajor = lstNameMajor.get(position);
        mViewLst.showAll(searchObject(searchNumberObject, searchNameObject, searchNameMajor));
    }

    @Override
    public void setSearchNumberObject(Integer position) {
        searchNumberObject = lstNumberObj.get(position);
        mViewLst.showAll(searchObject(searchNumberObject, searchNameObject, searchNameMajor));
    }

    @Override
    public void setSearchNameObject(String nameObject) {
        searchNameObject = nameObject;
        mViewLst.showAll(searchObject(searchNumberObject, searchNameObject, searchNameMajor));
    }

    @Override
    public void findSubjectById(Integer subjectId) {
        mViewDetail.showDetail(mSubjectRepository.findById(subjectId));
    }

    @Override
    public void findMajorBySubjectId(Integer subjectId) {
        mViewDetail.showListMajor(mMajorRepository.findAllBySubjectId(subjectId));
    }

    private List<Subject> searchObject(String number, String nameObj, String nameMajor) {
        number = number == null ? NAME_DEFAULT : number;
        nameMajor = nameMajor == null ? NAME_DEFAULT : nameMajor;
        nameObj = nameObj == null ? "" : nameObj;

        if (nameMajor.equals(NAME_DEFAULT)) {
            if (number.equals(NAME_DEFAULT)) {
                return mSubjectRepository.findByName(nameObj);
            } else
            {
                return mSubjectRepository.findByNumberAndNameSubject(Integer.valueOf(number), nameObj);
            }
        }
        else {
            if (number.equals(NAME_DEFAULT)) {
                return mSubjectRepository.findByNameObjAndNameMajor(nameObj, nameMajor);
            } else {
                return mSubjectRepository.findByNameObjAndNumberObjAndNameMajor(
                        Integer.valueOf(number), nameObj,  nameMajor);

            }
        }
    }




}
