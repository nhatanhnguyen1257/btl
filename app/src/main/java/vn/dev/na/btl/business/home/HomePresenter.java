package vn.dev.na.btl.business.home;

import android.content.Context;

import vn.dev.na.btl.business.login.LoginContract;
import vn.dev.na.btl.data.QLHPDatabase;
import vn.dev.na.btl.data.repository.MajorRepository;
import vn.dev.na.btl.data.repository.SubjectRepository;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private SubjectRepository mSubjectRepository;
    private MajorRepository mMajorRepository;

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void setRepository(Context context) {
        mMajorRepository = new MajorRepository(context);
        mSubjectRepository = new SubjectRepository(context);

    }

    @Override
    public void checkExistSubjectOrMajor() {
        if (mMajorRepository.checkExitMajor() == 0) {
            mView.showExitDialogMajor();
        }
        if (mSubjectRepository.checkExitSubject() == 0) {
            mView.showExitDialogSubject();
        }
    }
}
