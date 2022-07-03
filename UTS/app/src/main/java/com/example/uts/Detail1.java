package com.example.uts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Detail1  extends AppCompatActivity {
    TextView textDefault;
    ImageView imageDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        textDefault = findViewById(R.id.judul_default);
        imageDefault = findViewById(R.id.gambar_default);

        Intent intent = getIntent();
        String namaAplikasi = intent.getStringExtra("TEKS_DEFAULT");
        int logoAplikasi = intent.getIntExtra("GAMBAR_DEFAULT", 0);

        textDefault.setText(namaAplikasi);
        imageDefault.setImageResource(logoAplikasi);
    }
}
