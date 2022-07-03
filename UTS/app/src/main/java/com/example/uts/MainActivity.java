package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements AdapterRecycler.OnNoteListener {
    RecyclerView recyclerView;
    AdapterRecycler adapterRcyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemModel> data;
    Button reset;
    public TextView harga;
    public TextView total_harga;
    public int total_harga_Integer;
    public String total2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        harga = findViewById(R.id.hargaAyam);
        reset = findViewById(R.id.reset);
        total_harga = findViewById(R.id.TotalHarga);
        total_harga_Integer = 0;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        data = new ArrayList<>();
        for (int i = 0; i< MenuAyam.nama.length; i++){
            data.add(new ItemModel(
                    MenuAyam.nama[i],
                    MenuAyam.harga[i],
                    MenuAyam.gambar[i]
            ));
        }
        adapterRcyclerView = new AdapterRecycler(data , this, this);
        recyclerView.setAdapter(adapterRcyclerView);

        total_harga.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                total2 = total_harga.getText().toString();
                Intent intent = new Intent(MainActivity.this, Transaksi.class);
                intent.putExtra("TOTALTAGIHAN", total2);
                startActivity(intent); } });
    }
    @Override
    public void onNoteClick(int position) {
        int i = Integer.parseInt(MenuAyam.harga[position]);
        total_harga_Integer = total_harga_Integer+i;
        total_harga.setText("" + total_harga_Integer);
    }
    public void reset_harga(View view){
        total_harga_Integer = 0;
        total_harga.setText(" ");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.callcenter){
            startActivity(new Intent(this, CallCenter.class));
        } else if(item.getItemId()==R.id.sms){
            startActivity(new Intent(this, SMS.class));
        } else if(item.getItemId()==R.id.maps){
            startActivity(new Intent(this, Maps.class));
        } else if(item.getItemId()==R.id.update){
            startActivity(new Intent(this, Update.class));
        }
        return true;
    }

}
