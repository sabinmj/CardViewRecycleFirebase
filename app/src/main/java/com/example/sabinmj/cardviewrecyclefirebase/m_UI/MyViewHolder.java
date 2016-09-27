package com.example.sabinmj.cardviewrecyclefirebase.m_UI;

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabinmj.cardviewrecyclefirebase.Image_discription;
import com.example.sabinmj.cardviewrecyclefirebase.R;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt;
    TextView imageurllink;


    public String NEWS_TYPE=null;

    ImageView imageurl;

       public MyViewHolder(View itemView) {
        super(itemView);
           itemView.setOnClickListener(this);

        nameTxt=(TextView) itemView.findViewById(R.id.tvname);
        imageurl=(ImageView) itemView.findViewById(R.id.imgImage);
         //  imageurllink=(TextView) itemView.findViewById(R.id.tvimageurl);

       }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();

      Intent intentfuncrionA= v.getContext().startActivity(new Intent(v.getContext(), Image_discription.class));
        intentfuncrionA.putExtra(NEWS_TYPE,"ThisisfuctionA");
       // startActivity(intentfuncrionA);
    }
}
