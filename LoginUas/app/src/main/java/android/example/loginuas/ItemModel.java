package android.example.loginuas;

public class ItemModel {
    String nama,harga;
    int gambar;
    public ItemModel(String nama, String harga, int gambar) {
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }
    public String getNama() {
        return nama;
    }
    public String getHarga() {
        return harga;
    }
    public int getGambar() {
        return gambar;
    }
}