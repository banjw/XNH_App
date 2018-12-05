package com.fhxk.cooperative.xnh_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xk on 2018/12/5.
 */

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder>{
    private List<Family> familyList;

    public FamilyAdapter(List<Family> familyList) {
        this.familyList = familyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Family family = familyList.get(position);
        holder.familyName.setText(family.getName());
        holder.heyiNum.setText(family.getHeyiNum());
    }

    @Override
    public int getItemCount() {
        return familyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView familyName;
        TextView heyiNum;

        public ViewHolder(View view){
            super(view);
            familyName = (TextView) view.findViewById(R.id.family_name);
            heyiNum = (TextView) view.findViewById(R.id.heyi_num);
        }
    }

}
