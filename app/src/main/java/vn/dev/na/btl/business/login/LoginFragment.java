package vn.dev.na.btl.business.login;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.home.HomeFragment;
import vn.dev.na.btl.data.entity.Account;
import vn.dev.na.btl.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment implements  LoginContract.View {

    private FragmentLoginBinding binding;
    private LoginContract.Presenter loginPresenter;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                loginPresenter.setAccountRepository(getContext());
                loginPresenter.createAccount();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        loginPresenter.login(
                                binding.editUsename.getText().toString(),
                                binding.editPassword.getText().toString()
                        );
                    }
                });

            }
        });
    }

    @Override
    public void showError(String mesage) {
        binding.txtError.setText(mesage);
    }

    @Override
    public void showHome(Account account) {
        getView().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Xin chào " + account.getFullName() + " quay lại ...", Toast.LENGTH_LONG).show();
            }
        });
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, HomeFragment.newInstance(new Gson().toJson(account)), null);
        fragmentTransaction.commit();
    }
}