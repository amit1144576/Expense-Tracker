package com.example.catchyourmoney;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class reportListAdapter extends RecyclerView.Adapter<reportListAdapter.reportViewHolder> {

    private ArrayList<reportItems> mReportList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){mListener = listener;}

    public static class reportViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView,mDeleteImage;
        public TextView mTextType, mTextCateg,mTextAmount,mTextMethod,mTextDate,mTextRecurr,mTextComment;

        public reportViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ImageView);
            mTextType = itemView.findViewById(R.id.textViewType);
            mTextCateg = itemView.findViewById(R.id.textViewCateg);
            mTextMethod = itemView.findViewById(R.id.textViewMethod);
            mTextAmount = itemView.findViewById(R.id.textViewAmount);
            mTextDate = itemView.findViewById(R.id.textViewDate);
            mTextRecurr = itemView.findViewById(R.id.textViewRecurr);
            mTextComment = itemView.findViewById(R.id.textViewComment);
            mDeleteImage = itemView.findViewById(R.id.Image_delete);

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public reportListAdapter (ArrayList<reportItems> reportList){
        mReportList = reportList;
    }

    @NonNull
    @Override
    public reportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_items,parent,false);
       reportViewHolder rvh = new reportViewHolder(v, mListener);
       return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull reportViewHolder holder, int i) {
            reportItems currentItem = mReportList.get(i);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextType.setText(currentItem.getTextType());
        holder.mTextCateg.setText(currentItem.getTextCateg());
        holder.mTextMethod.setText(currentItem.getTextMethod());
        holder.mTextAmount.setText(currentItem.getTextAmount());
        holder.mTextDate.setText(currentItem.getTextDate());
        holder.mTextRecurr.setText(currentItem.getTextRecurr());
        holder.mTextComment.setText(currentItem.getTextComment());
    }

    @Override
    public int getItemCount() {
        return mReportList.size();
    }
}
