package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class UserInfoActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    TextView mNeighbourName;
    TextView mNeighbourName2;
    TextView mNeighbourAdress;
    TextView mNeighbourPhone;
    TextView mNeighbourSocial;
    TextView mAboutMe;
    ImageView mAvatar;
    FloatingActionButton mFav;
    FloatingActionButton mBack;

       @SuppressLint("SetTextI18n")
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        Neighbour nb = (Neighbour) intent.getSerializableExtra("message_key");


        mApiService = DI.getNeighbourApiService();
        mNeighbourName = findViewById(R.id.user_info_activity_textview_name);
        mNeighbourName2 = findViewById(R.id.user_info_activity_textview_name2);
        mNeighbourAdress = findViewById(R.id.user_info_activity_textview_address);
        mNeighbourPhone = findViewById(R.id.user_info_activity_textview_phone);
        mNeighbourSocial = findViewById(R.id.user_info_activity_textview_social);
        mAboutMe = findViewById(R.id.user_info_activity_textview_aboutme);
        mAvatar = findViewById(R.id.user_info_activity_avatar);
        mFav = findViewById(R.id.user_info_activity_favorite);
        mBack = findViewById(R.id.user_info_activity_back);

        mNeighbourName.setText(nb.getName());
        mNeighbourName2.setText(nb.getName());
        mNeighbourAdress.setText(nb.getAddress().replace(";","à"));
        mNeighbourPhone.setText(nb.getPhoneNumber());
        mNeighbourSocial.setText("www.facebook.fr/" + nb.getName().toLowerCase());
        mAboutMe.setText(nb.getAboutMe());
        //Set the Fav button state
        if(nb.getFav()==true){
            mFav.setImageResource(R.drawable.ic_star_white_24dp);
           }else{
               mFav.setImageResource(R.drawable.ic_star_border_white_24dp);
           }


           Glide.with(this)
                   .load(nb.getAvatarUrl()) // image url
                   .into(mAvatar);

           mFav.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   if (nb.getFav()==true){//unfav
                       nb.setFav(false);
                       mApiService.editFavNeighbour(nb);
                       mFav.setImageResource(R.drawable.ic_star_border_white_24dp);

                   }else{//fav
                    nb.setFav(true);
                    mApiService.editFavNeighbour(nb);
                    mFav.setImageResource(R.drawable.ic_star_white_24dp);
                   }

               }
           });

           mBack.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View view) {
                  //TODO: Back Button pressed
                   onBackPressed();

               }
           });

    }
}