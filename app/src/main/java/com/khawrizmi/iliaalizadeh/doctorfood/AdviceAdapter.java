package com.khawrizmi.iliaalizadeh.doctorfood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.PhoneHolder>{

    List<FetchAdvice> mDataset;
    Context context;

    public AdviceAdapter(Context context, List<FetchAdvice> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @Override
    public PhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advicelist, parent, false);
        PhoneHolder dataObjectHolder = new PhoneHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(PhoneHolder holder, int position) {

        holder.title.setText(mDataset.get(position).title);
        holder.target.setText(mDataset.get(position).target);
        holder.date.setText(mDataset.get(position).date);

    }

    public void update(List<FetchAdvice> list) {
        mDataset = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class PhoneHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView target;

        public PhoneHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.adv_title);
            date = (TextView) itemView.findViewById(R.id.datetxt);
            target = (TextView) itemView.findViewById(R.id.target);

        }
    }
}
