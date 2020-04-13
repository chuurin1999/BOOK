package com.example.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList book_id, date_title,money_title, caption_title,spinner1_title,spinner2_title,note_title;
    CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList date_title, ArrayList money_title, ArrayList caption_title, ArrayList spinner1_title,ArrayList spinner2_title,ArrayList note_title){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.date_title=date_title;
        this.money_title = money_title;
        this.caption_title = caption_title;
        this.spinner1_title=spinner1_title;
        this.spinner2_title=spinner2_title;
        this.note_title = note_title;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.date_title_txt.setText(String.valueOf(date_title.get(position)));
        holder.money_title_txt.setText(String.valueOf(money_title.get(position)));
        holder.caption_title_txt.setText(String.valueOf(caption_title.get(position)));
        holder.spinner1_title_txt.setText(String.valueOf(spinner1_title.get(position)));
        holder.spinner2_title_txt.setText(String.valueOf(spinner2_title.get(position)));
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("date", String.valueOf(date_title.get(position)));
                intent.putExtra("money", String.valueOf(money_title.get(position)));
                intent.putExtra("caption", String.valueOf(caption_title.get(position)));
                intent.putExtra("spinner1", String.valueOf(spinner1_title.get(position)));
                intent.putExtra("spinner2", String.valueOf(spinner2_title.get(position)));
                intent.putExtra("note", String.valueOf(note_title.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt,date_title_txt,money_title_txt, caption_title_txt,spinner1_title_txt,spinner2_title_txt,note_title_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            date_title_txt= itemView.findViewById(R.id.date_title_txt);
            money_title_txt = itemView.findViewById(R.id.money_title_txt);
            caption_title_txt = itemView.findViewById(R.id.caption_title_txt);
            spinner1_title_txt= itemView.findViewById(R.id.spinner1_title_txt);
            spinner2_title_txt= itemView.findViewById(R.id.spinner2_title_txt);
            note_title_txt = itemView.findViewById(R.id.note_title_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
