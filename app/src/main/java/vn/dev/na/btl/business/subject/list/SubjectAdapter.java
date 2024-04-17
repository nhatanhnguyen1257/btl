package vn.dev.na.btl.business.subject.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.subject.SubjectContract;
import vn.dev.na.btl.data.entity.Subject;


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{

    private List<Subject> mLstSubject;
    private SubjectContract.ViewList mViewList;

    public SubjectAdapter(List<Subject> lstSubject, SubjectContract.ViewList view){
        mLstSubject = lstSubject;
        mViewList = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.item_subject, parent, false);
        return new SubjectAdapter.ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject subject = mLstSubject.get(position);
        holder.mTxtId.setText(String.valueOf(subject.getId()));
        holder.mTxtName.setText(subject.getName());
        holder.mTxtNumber.setText(String.valueOf(subject.getNumber()));
        if (position%2 == 0) {
            holder.itemView.setBackgroundResource(R.color._EBCCCCCC);
        } else {
            holder.itemView.setBackgroundResource(R.color._E2E8E4E4);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewList.showDetail(mLstSubject.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLstSubject == null ? 0 : mLstSubject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtId;
        private TextView mTxtName;
        private TextView mTxtNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtId = itemView.findViewById(R.id.txt_noSubject);
            mTxtName = itemView.findViewById(R.id.txt_nameSubject);
            mTxtNumber = itemView.findViewById(R.id.text_numberSubject);
        }
    }
}
