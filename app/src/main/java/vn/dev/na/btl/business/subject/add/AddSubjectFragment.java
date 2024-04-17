package vn.dev.na.btl.business.subject.add;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import vn.dev.na.btl.business.common.FragmentBase;
import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.databinding.FragmentAddMajorBinding;
import vn.dev.na.btl.databinding.FragmentAddSubjectBinding;
import vn.dev.na.btl.databinding.FragmentSubjectBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddSubjectFragment extends FragmentBase implements View.OnClickListener, SubjectContract.ViewAdd {

    private FragmentAddSubjectBinding binding;
    private SubjectContract.Presenter mPresenter;
    private String nameMajor = "";
    private List<String> mLstNameMajor = new ArrayList<>(Arrays.asList(""));
    public AddSubjectFragment(SubjectContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSubjectBinding.inflate(inflater, container, false);
        binding.btnSave.setOnClickListener(this);
        binding.btnClose.setOnClickListener(this);
        mPresenter.setViewAdd(this);
        mPresenter.setRepository(getContext());
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.init();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnClose.getId()) {
            backFragment();
        } if (v.getId() == binding.btnSave.getId()) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    mPresenter.save(
                            binding.editNameSubject.getText().toString(),
                            binding.editCodeSubject.getText().toString(),
                            binding.editNumber.getText().toString(),
                            binding.editNote.getText().toString(),
                            binding.radioRequired.isChecked());
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
                Toast.makeText(getContext(), "Thêm mới môn học thành công", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showSpinnerMajor(List<String> lstName) {
//        mLstNameMajor = new ArrayList<>(Collections.singletonList("Không"));
//        mLstNameMajor.addAll(lstName);
//        getView().post(new Runnable() {
//            @Override
//            public void run() {
//                if (getContext() != null) {
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
//                    adapter.addAll(mLstNameMajor);
//
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    binding.spinnerMajor.setAdapter(adapter);
//                }
//            }
//        });
    }

    @Override
    public void backLayoutLst() {
        backFragment();
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        nameMajor = mLstNameMajor.get(position);
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}