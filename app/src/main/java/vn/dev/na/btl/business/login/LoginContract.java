package vn.dev.na.btl.business.login;

import android.content.Context;

import vn.dev.na.btl.data.entity.Account;

public interface LoginContract {

    public interface View{
        void showError(String mesage);
        void showHome(Account account);
    }

    public interface Presenter{

        void setView(LoginContract.View view );
        void setAccountRepository(Context context);
        void createAccount();
        void login(String username, String password);

    }
}
