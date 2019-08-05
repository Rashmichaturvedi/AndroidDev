package com.dits.dailyreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class GetAddressMapActivityAdapter extends RecyclerView.Adapter<GetAddressMapActivityAdapter.ViewHolder> {

    Context context;
    List<GetAddressMapDetails> MainImageUploadInfoList;


    public GetAddressMapActivityAdapter(Context context, List<GetAddressMapDetails> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_locations, parent, false);

        GetAddressMapActivityAdapter.ViewHolder viewHolder = new GetAddressMapActivityAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GetAddressMapDetails GAMD = MainImageUploadInfoList.get(position);

        holder.location.setText(GAMD.getLocation());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView location;


        public ViewHolder(View itemView) {

            super(itemView);

            location= (TextView)itemView.findViewById(R.id.location);

        }
    }
}

