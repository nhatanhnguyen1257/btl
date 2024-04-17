package vn.dev.na.btl.business.login;

import android.content.Context;

import vn.dev.na.btl.data.entity.Account;
import vn.dev.na.btl.data.repository.AccountRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View viewLogin;
    private AccountRepository accountRepository;

    @Override
    public void setView(LoginContract.View view) {
        viewLogin = view;
    }

    @Override
    public void setAccountRepository(Context context) {
        accountRepository = new AccountRepository(context);
    }

    @Override
    public void createAccount() {
        accountRepository.createAdmin();
    }

    @Override
    public void login(String username, String password) {
        Account account = accountRepository.login(username, password);
        if (account == null) {
            viewLogin.showError("Sai thông tin đăng nhập");
        } else {
            viewLogin.showHome(account);
        }
    }
}
