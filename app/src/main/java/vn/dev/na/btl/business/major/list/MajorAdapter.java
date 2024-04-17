package vn.dev.na.btl.business.major.list;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.dev.na.btl.R;
import vn.dev.na.btl.business.major.MajorContract;
import vn.dev.na.btl.data.entity.Major;

public class MajorAdapter extends RecyclerView.Adapter<MajorAdapter.ViewHolder>{
    private Context mContext;
    private List<Major> mLstMajor;
    private MajorContract.ViewList viewList;

    public MajorAdapter(List<Major> list, MajorContract.ViewList viewLst) {
        mLstMajor = list;
        viewList = viewLst;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.item_major, parent, false);
        return new ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Major major = mLstMajor.get(position);
        holder.mTxtId.setText(String.valueOf(major.getId()));
        holder.mTxtName.setText(major.getName());
        holder.mTxtCode.setText(major.getCode());
        if (position%2 == 0) {
            holder.itemView.setBackgroundResource(R.color._EBCCCCCC);
        } else {
            holder.itemView.setBackgroundResource(R.color._E2E8E4E4);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList.showDetail(major);
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
        private TextView mTxtCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtId = itemView.findViewById(R.id.txt_id);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mTxtCode = itemView.findViewById(R.id.txt_code);
        }
    }
}
