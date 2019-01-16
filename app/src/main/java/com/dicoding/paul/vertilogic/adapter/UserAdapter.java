package com.dicoding.paul.vertilogic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.paul.vertilogic.DetailActivity;
import com.dicoding.paul.vertilogic.R;
import com.dicoding.paul.vertilogic.model.UserModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dicoding.paul.vertilogic.DetailActivity.EXTRA_USER;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    private Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<UserModel> getUserModelArrayList() {
        return userModelArrayList;
    }

    public void setUserModelArrayList(ArrayList<UserModel> userModelArrayList) {
        this.userModelArrayList = userModelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_contents,
                viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        UserModel model = userModelArrayList.get(position);

        userViewHolder.name.setText(model.getName());
        userViewHolder.userName.setText(model.getUserName());
        userViewHolder.email.setText(model.getEmail());
        userViewHolder.phone.setText(model.getPhone());
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_users) CardView cvUsers;
        @BindView(R.id.tv_name) TextView name;
        @BindView(R.id.tv_username) TextView userName;
        @BindView(R.id.tv_email) TextView email;
        @BindView(R.id.tv_phone) TextView phone;

        //Set onClickListener to start detail activity
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserModel userModel = userModelArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailActivity.class);

                    //Put parcelable object into intent
                    intent.putExtra(EXTRA_USER, userModel);
                    context.startActivity(intent);
                }
            });
        }
    }
}
