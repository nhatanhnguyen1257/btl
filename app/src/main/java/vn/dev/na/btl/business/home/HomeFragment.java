package vn.dev.na.btl.business.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.common.FragmentBase;
import vn.dev.na.btl.business.major.list.MajorFragment;
import vn.dev.na.btl.business.subject.list.SubjectFragment;
import vn.dev.na.btl.data.entity.Account;
import vn.dev.na.btl.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends FragmentBase implements HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private FragmentHomeBinding binding;

    private static final String ARG_ACCOUNT = "account";

    private Account mAccount;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String account) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ACCOUNT, account);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAccount = new Gson().fromJson(getArguments().getString(ARG_ACCOUNT), Account.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.navigation.setSelectedItemId(R.id.navigation_subject);
        mPresenter = new HomePresenter();
        mPresenter.setView(this);
        mPresenter.setRepository(getContext());
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.txtName.setText(mAccount.getFullName());
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.checkExistSubjectOrMajor();
            }
        });
    }

    private NavigationBarView.OnItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnItemSelectedListener() {
        Fragment fragment;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return switchFragment(item);
        }
    };


    private boolean switchFragment(MenuItem item){
        Fragment fragment = null;
        switch(item.getItemId()){
            case R.id.navigation_subject:
                fragment = new SubjectFragment();
                break;
            case R.id.navigation_major:
                fragment = new MajorFragment();
                break;
        }

        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }


    @Override
    public void showExitDialogSubject() {
        getView().post(new Runnable() {
            @Override
            public void run() {
                dialogCheck("Chưa có môn học nào trong hệ thống. Hãy thêm môn học để sử dụng hệ thống");
            }
        });
    }

    @Override
    public void showExitDialogMajor() {
        getView().post(new Runnable() {
            @Override
            public void run() {
                dialogCheck("Chưa có ngành học nào. Hãy thêm ngành học để dử dụng hệ thống.");
            }
        });

    }

    private void dialogCheck(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(message);
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}