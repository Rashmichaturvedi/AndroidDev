package com.dits.dailyreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomerRecyclerviewAdapter extends RecyclerView.Adapter<CustomerRecyclerviewAdapter.ViewHolder> {

    Context context;
    List<CustomerReportDetails> MainImageUploadInfoList;


    public CustomerRecyclerviewAdapter(Context context, List<CustomerReportDetails> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customerreports, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CustomerReportDetails CRD = MainImageUploadInfoList.get(position);

        holder.shopnameTextView.setText(CRD.getShopname());

        holder.addressTextView.setText(CRD.getAddress());
        holder.conversationdetailsTextView.setText(CRD.getConversationdetails());
        holder.feedback.setText(CRD.getFeedback());
        holder.personname.setText(CRD.getPersonname());
        holder.contactdetails.setText(CRD.getContactdetails());
        holder.date.setText(CRD.getDate());
        holder.time.setText(CRD.getTime());
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView shopnameTextView;
        public TextView addressTextView;
        public TextView conversationdetailsTextView;
        public TextView feedback;
        public TextView personname;
        public TextView contactdetails;
        public TextView date;
        public TextView time;

        public ViewHolder(View itemView) {

            super(itemView);

            shopnameTextView = (TextView)itemView.findViewById(R.id.ShowShopName);
            addressTextView = (TextView)itemView.findViewById(R.id.ShowAddress);
            conversationdetailsTextView = (TextView)itemView.findViewById(R.id.ShowConversationDetails);
            feedback = (TextView)itemView.findViewById(R.id.Showfeedback);
            personname = (TextView)itemView.findViewById(R.id.Showpersonname);
            contactdetails = (TextView)itemView.findViewById(R.id.ShowContackDetails);
            date =(TextView)itemView.findViewById(R.id.date);
            time=(TextView)itemView.findViewById(R.id.timee);
        }
    }
}

