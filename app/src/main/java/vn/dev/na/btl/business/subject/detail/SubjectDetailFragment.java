package vn.dev.na.btl.business.subject.detail;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.common.FragmentBase;
import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.Subject;
import vn.dev.na.btl.databinding.FragmentSubjectBinding;
import vn.dev.na.btl.databinding.FragmentSubjectDetailBinding;


public class SubjectDetailFragment extends FragmentBase implements SubjectContract.ViewDetail {

    private FragmentSubjectDetailBinding binding;
    private SubjectContract.Presenter mPresenter;

    private static final String ARG_ID = "id";

    private Integer mIdSubject;

    public SubjectDetailFragment(SubjectContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static SubjectDetailFragment newInstance(Integer mIdSubject, SubjectContract.Presenter presenter) {
        SubjectDetailFragment fragment = new SubjectDetailFragment(presenter);
        Bundle args = new Bundle();
        args.putInt(ARG_ID, mIdSubject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIdSubject = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubjectDetailBinding.inflate(inflater, container, false);
        mPresenter.setViewDetail(this);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.findSubjectById(mIdSubject);
            }
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.findMajorBySubjectId(mIdSubject);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFragment();
            }
        });

        binding.editNameSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editNameSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editCodeSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.radioRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.radioNoRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void showDetail(Subject subject) {
        getView().post(new Runnable() {
            @Override
            public void run() {
                binding.textId.setText(String.valueOf(subject.getId()));
                binding.editNameSubject.setText(subject.getName());
                binding.editCodeSubject.setText(subject.getCode());
                binding.editNumber.setText(String.valueOf(subject.getNumber()));
                binding.editNote.setText(subject.getNote());

                if (subject.getRequired()) {
                    binding.radioRequired.setChecked(true);
                    binding.radioNoRequired.setChecked(false);
                }
                else {
                    binding.radioRequired.setChecked(false);
                    binding.radioNoRequired.setChecked(true);
                }
            }
        });
    }

    @Override
    public void showListMajor(List<Major> lstMajor) {
        getView().post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}