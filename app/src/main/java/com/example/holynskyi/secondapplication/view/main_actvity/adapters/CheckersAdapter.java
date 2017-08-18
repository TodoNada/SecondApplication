package com.example.holynskyi.secondapplication.view.main_actvity.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.daimajia.swipe.SwipeLayout;
import com.example.holynskyi.secondapplication.models.Checker;
import com.example.holynskyi.secondapplication.R;
import com.example.holynskyi.secondapplication.view.main_actvity.OnCheckerItemSelectedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by holynskyi on 17.08.17.
 */

public class CheckersAdapter extends RecyclerView.Adapter<CheckersAdapter.CheckerViewHolder> {


    protected List<Checker> checkersList;       // The list of checkers that will be displayed

    private OnCheckerItemSelectedListener onCheckerItemSelectedListener;


    public void setOnItemSelectedListener(OnCheckerItemSelectedListener onCheckerItemSelectedListener) {
        this.onCheckerItemSelectedListener = onCheckerItemSelectedListener;
    }

    public void setList(List<Checker> list) {
        this.checkersList = list;
    }

    public static class CheckerViewHolder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        ImageView ivDeleteItem;
        CardView cv;
        CheckBox cbField1;
        CheckBox cbField2;

        CheckerViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            ivDeleteItem = (ImageView) itemView.findViewById(R.id.imageViewDeleteItem);
            cv = (CardView) itemView.findViewById(R.id.cv);
            cbField1 = (CheckBox) itemView.findViewById(R.id.checkBoxFirstField);
            cbField2 = (CheckBox) itemView.findViewById(R.id.checkBoxSecondField);
        }
    }

    @Override
    public void onBindViewHolder(CheckerViewHolder holder, final int position) {

        holder.cbField1.setChecked(checkersList.get(position).getField1() == 1);
        holder.cbField2.setChecked(checkersList.get(position).getField2() == 1);
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        holder.ivDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // listener to delete
                onCheckerItemSelectedListener.itemCheckerSelected(position);

            }
        });
    }

    @Override
    public CheckerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkboxes, parent, false);
        return new CheckerViewHolder(v);
    }


    @Override
    public int getItemCount() {
        if (checkersList == null) return 0;
        return checkersList.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
