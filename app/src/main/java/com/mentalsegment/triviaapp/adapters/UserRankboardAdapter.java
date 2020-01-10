package com.mentalsegment.triviaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mentalsegment.triviaapp.R;
import com.mentalsegment.triviaapp.UserScoreBoardModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserRankboardAdapter extends RecyclerView.Adapter<UserRankboardAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UserScoreBoardModel> mList;

    public UserRankboardAdapter(Context context, ArrayList<UserScoreBoardModel> list) {
        mContext = context;
        mList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.user_item_layout,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserScoreBoardModel Imodel=mList.get(position);
        CircleImageView image=holder.itemImage;
        TextView name=holder.userName;
        TextView score=holder.userScore;

        image.setImageResource(Imodel.getImage());
        name.setText(Imodel.getName());
        score.setText(Imodel.getScore());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView itemImage;
        TextView userName;
        TextView userScore;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.imv_user_image);
            userName = itemView.findViewById(R.id.tv_username_rankboard);
            userScore = itemView.findViewById(R.id.tv_highscore_rankboard);


        }
    }
}
