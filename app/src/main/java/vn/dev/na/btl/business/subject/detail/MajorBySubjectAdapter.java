package vn.dev.na.btl.business.subject.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.Subject;


public class MajorBySubjectAdapter extends RecyclerView.Adapter<MajorBySubjectAdapter.ViewHolder>{

    private List<MajorBySubject> mLstMajor = new ArrayList<>();
    private SubjectContract.ViewDetail mViewDetal;

    public MajorBySubjectAdapter(List<Major> lstCheck, List<Major> lstNoCheck, SubjectContract.ViewDetail view){
        if (lstCheck != null){
            for (Major item: lstCheck) {
                MajorBySubject majorBySubject = new MajorBySubject();
                majorBySubject.setName(item.getName());
                majorBySubject.setId(item.getId());
                majorBySubject.setCheck(true);
                mLstMajor.add(majorBySubject);
            }
        }

        if (lstNoCheck != null) {
            for (Major item: lstNoCheck) {
                MajorBySubject majorBySubject = new MajorBySubject();
                majorBySubject.setName(item.getName());
                majorBySubject.setId(item.getId());
                majorBySubject.setCheck(false);
                mLstMajor.add(majorBySubject);
            }
        }

        mViewDetal = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.item_major_by_subject, parent, false);
        return new MajorBySubjectAdapter.ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MajorBySubject subject = mLstMajor.get(position);
        holder.mTxtId.setText(String.valueOf(subject.getId()));
        holder.mTxtName.setText(subject.getName());
        holder.mCheckbox.setChecked(subject.isCheck());
        if (position%2 == 0) {
            holder.itemView.setBackgroundResource(R.color._EBCCCCCC);
        } else {
            holder.itemView.setBackgroundResource(R.color._E2E8E4E4);
        }

        holder.mCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewDetal.addMajorToSubjet(subject.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLstMajor == null ? 0 : mLstMajor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtId;
        private TextView mTxtName;
        private CheckBox mCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtId = itemView.findViewById(R.id.text_id);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mCheckbox = itemView.findViewById(R.id.checkBox);
        }
    }
}
