package vn.dev.na.btl.business.major.list;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.common.FragmentBase;
import vn.dev.na.btl.business.major.MajorContract;
import vn.dev.na.btl.business.major.MajorPresenter;
import vn.dev.na.btl.business.major.add.AddMajorFragment;
import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.databinding.FragmentMajorBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MajorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MajorFragment extends FragmentBase implements View.OnClickListener, MajorContract.ViewList, TextWatcher {

    private FragmentMajorBinding binding;
    private MajorContract.Presenter mPresenter;

    public MajorFragment() {
        // Required empty public constructor
    }


    public static MajorFragment newInstance(String param1, String param2) {
        MajorFragment fragment = new MajorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMajorBinding.inflate(inflater, container, false);
        binding.btnAdd.setOnClickListener(this);
        mPresenter = new MajorPresenter();
        mPresenter.setRepository(getContext());
        mPresenter.setViewLst(this);
        binding.editInputName.setThreshold(4);
        binding.editInputName.addTextChangedListener(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.showAll();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnAdd.getId()) {
            addFragment(new AddMajorFragment(mPresenter));
        }
    }

    @Override
    public void showAll(List<Major> lst) {
        MajorContract.ViewList view = this;
        getView().post(new Runnable() {
            @Override
            public void run() {
                binding.recyMajor.setAdapter(new MajorAdapter(lst, view));
            }
        });
    }

    @Override
    public void showDetail(Major major) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext(),
                R.style.myFullscreenAlertDialogStyle);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.item_major_update, null);
        dialogBuilder.setView(dialogView);

        TextView txtId = (TextView) dialogView.findViewById(R.id.text_id);
        txtId.setText(String.valueOf(major.getId()));

        EditText editName = (EditText) dialogView.findViewById(R.id.edit_name);
        editName.setText(String.valueOf(major.getName()));

        EditText editCode = (EditText) dialogView.findViewById(R.id.edit_code);
        editCode.setText(String.valueOf(major.getCode()));

        EditText editNote = (EditText) dialogView.findViewById(R.id.edit_note);
        editNote.setText(String.valueOf(major.getCode()));
        AlertDialog alertDialog = dialogBuilder.create();

        dialogView.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.deleteMajorById(major.getId());
                    }
                });
                Toast.makeText(getContext(), "Xóa ngành học thành công", Toast.LENGTH_LONG).show();
                alertDialog.cancel();
            }
        });

        dialogView.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.update(major.getId(),
                                editName.getText().toString(),
                                editCode.getText().toString(),
                                editNote.getText().toString());
                    }
                });
                Toast.makeText(getContext(), "Cập nhật ngành học thành công", Toast.LENGTH_LONG).show();
                alertDialog.cancel();
            }
        });


        alertDialog.show();
    }

    @Override
    public void showMessageError(String message) {
        getView().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPresenter.searchByName(s.toString());
            }
        });
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}