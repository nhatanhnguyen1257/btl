package vn.dev.na.btl.business.home;

import android.content.Context;

import vn.dev.na.btl.business.login.LoginContract;
import vn.dev.na.btl.data.entity.Account;

public interface HomeContract {

    public interface View{
        void showExitDialogSubject();
        void showExitDialogMajor();
    }

    public interface Presenter{

        void setView(HomeContract.View view );
        void setRepository(Context context);
        void checkExistSubjectOrMajor();

    }
}
