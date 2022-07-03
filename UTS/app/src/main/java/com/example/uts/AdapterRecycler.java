package com.example.uts;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    ArrayList<ItemModel> dataItem;
    OnNoteListener mOnNoteListener;
    Context mContext;
    @NonNull
    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu,parent,false);
        return new ViewHolder(view,mOnNoteListener);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolder holder, int position) {
        TextView text_nama = holder.textNama;
        TextView text_harga = holder.textHarga;
        ImageView i_gambar = holder.gambar;
        text_nama.setText(dataItem.get(position).getNama());
        text_harga.setText("RP."+dataItem.get(position).getHarga());
        i_gambar.setImageResource(dataItem.get(position).getGambar());

        holder.textNama.setOnClickListener(v -> {
            if (dataItem.get(position).getNama().equals("Bandeng")){
                Intent intent = new Intent(mContext, Detail1.class);
                intent.putExtra("TEKS_DEFAULT", "Bandeng Spesial Pilihan Khas Semarang");
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.bandeng1);
                mContext.startActivity(intent);
            }   if (dataItem.get(position).getNama().equals("Lumpia")){
                Intent intent = new Intent(mContext, Detail1.class);
                intent.putExtra("TEKS_DEFAULT", "Lumpia Dengan Rasa Berkualitas Khas Semarang");
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.lumpia);
                mContext.startActivity(intent);
            }   if (dataItem.get(position).getNama().equals("Tumpi")){
                Intent intent = new Intent(mContext, Detail1.class);
                intent.putExtra("TEKS_DEFAULT", "Tumpi Gurih Khas Semarang");
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.tumpi);
                mContext.startActivity(intent);
            }   if (dataItem.get(position).getNama().equals("Wingko")){
                Intent intent = new Intent(mContext, Detail1.class);
                intent.putExtra("TEKS_DEFAULT", "Wingko Babat Khas Semarang");
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.wingko);
                mContext.startActivity(intent);
            }   if (dataItem.get(position).getNama().equals("Tahu Bakso")){
                Intent intent = new Intent(mContext, Detail1.class);
                intent.putExtra("TEKS_DEFAULT", "Tahu Bakso Khas Semarang");
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.tahubakso);
                mContext.startActivity(intent);
            }   if (dataItem.get(position).getNama().equals("Ayam Bakar")){
                Intent intent = new Intent(mContext, Detail1.class);
                intent.putExtra("TEKS_DEFAULT", "Ayam Bakar Manis");
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.ayam2);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataItem.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textNama,textHarga;
        ImageView gambar;
        OnNoteListener onNoteListener;
        private OnNoteListener mOnNoteListener;
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {super(itemView);
            gambar = itemView.findViewById(R.id.gambarAyam);
            textNama = itemView.findViewById(R.id.noAyam);
            textHarga = itemView.findViewById(R.id.hargaAyam);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    AdapterRecycler(ArrayList<ItemModel> data, Context mContext, OnNoteListener onNoteListener){
        this.dataItem =data;
        this.mOnNoteListener = onNoteListener;
        this.mContext = mContext;
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}

