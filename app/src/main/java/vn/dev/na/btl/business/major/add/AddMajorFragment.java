package vn.dev.na.btl.business.major.add;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.common.FragmentBase;
import vn.dev.na.btl.business.major.MajorContract;
import vn.dev.na.btl.business.major.MajorPresenter;
import vn.dev.na.btl.databinding.FragmentAddMajorBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddMajorFragment extends FragmentBase implements View.OnClickListener, MajorContract.ViewAdd {

    private FragmentAddMajorBinding binding;
    private MajorContract.Presenter mPresenter;

    public AddMajorFragment(MajorContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMajorBinding.inflate(inflater, container, false);
        mPresenter.setViewAdd(this);
        mPresenter.setRepository(getContext());
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.btnSave.setOnClickListener(this);
        binding.btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnClose.getId()) {
            backFragment();
        } else if (v.getId() == binding.btnSave.getId()) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    mPresenter.saveMajor(
                            binding.editName.getText().toString(),
                            binding.editCode.getText().toString(),
                            binding.editNote.getText().toString()
                    );
                }
            });

        }
    }

    @Override
    public void showMessageError(String message) {
        getView().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showMessageOk() {

        getView().post(new Runnable() {
            @Override
            public void run() {
                backFragment();
                Toast.makeText(getContext(), "Thêm mới ngành học thành công", Toast.LENGTH_LONG).show();
            }
        });
    }
}