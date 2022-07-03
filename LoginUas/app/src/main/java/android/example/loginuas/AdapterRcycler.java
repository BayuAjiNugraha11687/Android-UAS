package android.example.loginuas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterRcycler extends
        RecyclerView.Adapter<AdapterRcycler.ViewHolder> {
    ArrayList<ItemModel> dataItem;
    OnNoteListener mOnNoteListener;
    Context mContext;
    @NonNull
    @Override
    public AdapterRcycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu,parent,false);
        return new ViewHolder(view,mOnNoteListener);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterRcycler.ViewHolder holder, int position) {
        TextView text_nama = holder.textNama;
        TextView text_harga = holder.textHarga;
        ImageView i_gambar = holder.gambar;
        text_nama.setText(dataItem.get(position).getNama());
        text_harga.setText("RP."+dataItem.get(position).getHarga());
        i_gambar.setImageResource(dataItem.get(position).getGambar());
        holder.textNama.setOnClickListener(view ->
        {
            if(dataItem.get(position).getNama().equals("ayam")){
                Intent intent = new Intent(mContext,DetailProduct.class);
                intent.putExtra("text","Ayam bakar manis");
                intent.putExtra("Image",R.drawable.ayam2);
                mContext.startActivity(intent);
            }
            if(dataItem.get(position).getNama().equals("bandeng")){
                Intent intent = new Intent(mContext,DetailProduct.class);
                intent.putExtra("text","Bandeng tanpa duri");
                intent.putExtra("Image",R.drawable.bandeng1);
                mContext.startActivity(intent);
            }
            if(dataItem.get(position).getNama().equals("lumpia")){
                Intent intent = new Intent(mContext,DetailProduct.class);
                intent.putExtra("text","Lumpia khas semarang");
                intent.putExtra("Image",R.drawable.lumpia);
                mContext.startActivity(intent);
            }
            if(dataItem.get(position).getNama().equals("moaci")){
                Intent intent = new Intent(mContext,DetailProduct.class);
                intent.putExtra("text","Moaci khas Semarang");
                intent.putExtra("Image",R.drawable.rafting);
                mContext.startActivity(intent);
            }
            if(dataItem.get(position).getNama().equals("resort")){
                Intent intent = new Intent(mContext,DetailProduct.class);
                intent.putExtra("text","resort indah di hutan");
                intent.putExtra("Image",R.drawable.resort);
                mContext.startActivity(intent);
            }
            if(dataItem.get(position).getNama().equals("villa")){
                Intent intent = new Intent(mContext,DetailProduct.class);
                intent.putExtra("text","villa untuk 5 orang di hutan");
                intent.putExtra("Image",R.drawable.villa);
                mContext.startActivity(intent);
            }




        });
    }
    @Override
    public int getItemCount() {
        return dataItem.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView textNama,textHarga;
        ImageView gambar;
        OnNoteListener onNoteListener;
        private OnNoteListener mOnNoteListener;
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambarAyam);
            textNama = itemView.findViewById(R.id.noAyam);
            textHarga = itemView.findViewById(R.id.hargaAyam);
            this.onNoteListener = onNoteListener;
            gambar.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    AdapterRcycler(Context context,ArrayList<ItemModel> data, OnNoteListener
            onNoteListener){
        this.dataItem =data;
        this.mOnNoteListener = onNoteListener;
        this.mContext= context;
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

