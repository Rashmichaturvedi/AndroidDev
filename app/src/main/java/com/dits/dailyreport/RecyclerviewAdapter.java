package com.dits.dailyreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    Context context;
    List<DailyReportDetails> MainImageUploadInfoList;


    public RecyclerviewAdapter(Context context, List<DailyReportDetails> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_dailyreports, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DailyReportDetails DRD = MainImageUploadInfoList.get(position);

        holder.LunchExpenseTextView.setText(DRD.getLunch());

        holder.DinnerExpenserTextView.setText(DRD.getDinner());
        holder.TravellingExpenseTextView.setText(DRD.getTravelling());
        holder.ExtraExpenseTextView.setText(DRD.getExtraexpense());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView LunchExpenseTextView;
        public TextView DinnerExpenserTextView;
        public TextView TravellingExpenseTextView;
        public TextView ExtraExpenseTextView;
        public ViewHolder(View itemView) {

            super(itemView);

            LunchExpenseTextView = (TextView)itemView.findViewById(R.id.ShowLunchExpense);
            DinnerExpenserTextView = (TextView)itemView.findViewById(R.id.ShowDinnerExpense);
            TravellingExpenseTextView = (TextView)itemView.findViewById(R.id.ShowTravellingExpense);
            ExtraExpenseTextView = (TextView)itemView.findViewById(R.id.ShowExtraExpense);
        }
    }
}

