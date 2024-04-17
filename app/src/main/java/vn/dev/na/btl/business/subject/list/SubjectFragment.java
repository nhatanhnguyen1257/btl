package vn.dev.na.btl.business.subject.list;

import android.os.AsyncTask;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.List;

import vn.dev.na.btl.business.common.FragmentBase;
import vn.dev.na.btl.business.major.add.AddMajorFragment;
import vn.dev.na.btl.business.subject.PresenterSubject;
import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.business.subject.add.AddSubjectFragment;
import vn.dev.na.btl.business.subject.detail.SubjectDetailFragment;
import vn.dev.na.btl.data.entity.Subject;
import vn.dev.na.btl.databinding.FragmentSubjectBinding;

public class SubjectFragment extends FragmentBase implements View.OnClickListener, SubjectContract.ViewList, TextWatcher {

    private FragmentSubjectBinding binding;
    private SubjectContract.Presenter mPresenter;

    public SubjectFragment() {
        // Required empty public constructor
    }

    public static SubjectFragment newInstance(String param1, String param2) {
        SubjectFragment fragment = new SubjectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubjectBinding.inflate(inflater, container, false);
        binding.btnAddSubject.setOnClickListener(this);
        mPresenter = new PresenterSubject();
        mPresenter.setRepository(getContext());
        mPresenter.setViewLst(this);
        binding.editInputName.setThreshold(4);
        binding.editInputName.addTextChangedListener(this);
        binding.planetsMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.setSearchNameMajor(position);

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.slanetsNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.setSearchNumberObject(position);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.showAllSubject();
                mPresenter.getAllNumber();
                mPresenter.getAllNameMajor();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnAddSubject.getId()) {
            addFragment(new AddSubjectFragment(mPresenter));
        }
    }

    @Override
    public void showNumber(List<String> lstNumber) {
        getView().post(new Runnable() {
            @Override
            public void run() {
                binding.slanetsNumber.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, lstNumber.toArray()));
            }
        });
    }

    @Override
    public void showListMajor(List<String> lstName) {
        getView().post(new Runnable() {
            @Override
            public void run() {

                binding.planetsMajor.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, lstName.toArray()));
            }
        });

    }

    @Override
    public void showAll(List<Subject> lstSubject) {
        SubjectContract.ViewList view = this;
        getView().post(new Runnable() {
            @Override
            public void run() {
                binding.recySubject.setAdapter(new SubjectAdapter(lstSubject, view));
            }
        });
    }

    @Override
    public void showDetail(Integer subjectId) {
//        addFragment(new AddMajorFragment(mPresenter));
        addFragment( SubjectDetailFragment.newInstance(subjectId, mPresenter));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.setSearchNameObject(s.toString());
            }
        });
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}