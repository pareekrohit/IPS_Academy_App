package com.ipsacademypune.user.ipsacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ipsacademypune.user.ipsacademy.Adapters.ImageAdapter;
import com.ipsacademypune.user.ipsacademy.Adapters.SaveImagePosition_pref;

public class Picture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Picture.this, ImageFullScreenView.class);
                i.putExtra("id", position);

                SaveImagePosition_pref savepref = new SaveImagePosition_pref(Picture.this);
                savepref.setInt("position",position);

                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
