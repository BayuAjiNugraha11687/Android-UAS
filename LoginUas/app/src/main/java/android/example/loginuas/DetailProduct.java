package android.example.loginuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailProduct extends AppCompatActivity {
    TextView TextDefault;
    ImageView ImageDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        TextDefault = findViewById(R.id.desc_default);
        ImageDefault = findViewById(R.id.pict_default);

        Intent intent =getIntent();
        String namaAplikasi = intent.getStringExtra("text");
        int gambarAplikasi = intent.getIntExtra("Image",0);

        TextDefault.setText(namaAplikasi);
        ImageDefault.setImageResource(gambarAplikasi);
    }
}