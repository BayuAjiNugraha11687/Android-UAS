package android.example.loginuas;

import androidx.appcompat.app.AppCompatActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class checkout extends AppCompatActivity {

    ImageView gambar_upload;
    TextView rekening_txt, no_nota_txt, user_txt, total_biaya_txt;
    Button btn_cetak;

    private String strJson, apiUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Please Wait.....");
//        progressDialog.setCanceledOnTouchOutside(false);

        gambar_upload = findViewById(R.id.gambar_upload);
        rekening_txt = findViewById(R.id.rekening_id);
        no_nota_txt = findViewById(R.id.no_nota_id);
        user_txt = findViewById(R.id.user_id);
        total_biaya_txt = findViewById(R.id.total_biaya_id);
        btn_cetak = findViewById(R.id.btn_cetak);
//
//       progressDialog.show();
//       client = new OkHttpClient();
//       new GetUserDataRequest().execute();

        btn_cetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rekening_pdf = rekening_txt.getText().toString();
                String total_biaya_pdf = total_biaya_txt.getText().toString();
                String user_pdf = user_txt.getText().toString();

                try {
                    createPdf(rekening_pdf, total_biaya_pdf, user_pdf);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void createPdf(String rekening_pdf, String total_biaya_pdf, String user_pdf) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "PDF_PPB_PERTEMUAN_14");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A6);
        document.setMargins(0,0,0,0);

        // INSERT GAMBAR + CONVERT BITMAP
        Drawable d = getDrawable(R.drawable.logo_huruf_a);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);

        // BUAT DESKRIPSI
        Paragraph billing = new Paragraph("Your Billing").setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER);
        Paragraph details = new Paragraph("Your Data").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);

        // BUAT TABEL
        float[] width = {100f, 100f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // BUAT BARIS TABEL
        table.addCell(new Cell().add(new Paragraph("NIM")));
        table.addCell(new Cell().add(new Paragraph(rekening_pdf)));

        table.addCell(new Cell().add(new Paragraph("Email")));
        table.addCell(new Cell().add(new Paragraph(total_biaya_pdf)));

        table.addCell(new Cell().add(new Paragraph("Name")));
        table.addCell(new Cell().add(new Paragraph(user_pdf)));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        table.addCell(new Cell().add(new Paragraph("Date")));
        table.addCell(new Cell().add(new Paragraph(LocalDate.now().format(dateFormatter).toString())));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        table.addCell(new Cell().add(new Paragraph("Time")));
        table.addCell(new Cell().add(new Paragraph(LocalTime.now().format(timeFormatter).toString())));

        // BUAT BARCODE
        BarcodeQRCode qrCode = new BarcodeQRCode
                (rekening_pdf + "\n" +
                        total_biaya_pdf + "\n" +
                        user_pdf + "\n" +
                        LocalDate.now().format(dateFormatter) + "\n" +
                        LocalTime.now().format(timeFormatter));
        PdfFormXObject qrCodeObject = qrCode.createFormXObject(ColorConstants.BLACK, pdfDocument);
        Image qrCodeImage = new Image(qrCodeObject).setWidth(80).setHorizontalAlignment(HorizontalAlignment.CENTER);

        // JADIKAN SATU
        document.add(image);
        document.add(billing);
        document.add(details);
        document.add(table);
        document.add(qrCodeImage);
        // END PROCCES
        document.close();
        Toast.makeText(this, "PDF CREATED", Toast.LENGTH_SHORT).show();
    }

//    public class GetUserDataRequest extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... voids) {
//            request = new Request.Builder().url(apiUrl).build();
//            try {
//                response = client.newCall(request).execute();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
//
//            try {
//                strJson = response.body().string();
//                updateUserData(strJson);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    private void updateUserData(String strJson) {
//        try{
//            JSONArray parent = new JSONArray(strJson);
//            JSONObject child = parent.getJSONObject(0);
//            String imgUrl = child.getString("imageLink");
//            String rekening = child.getString("rekening");
//            String no_nota = child.getString("no_nota");
//            String user = child.getString("user");
//            String total_biaya = child.getString("total_biaya");
//            Glide.with(this).load(imgUrl).into(gambar_upload);
//
//            rekening_txt.setText(rekening);
//            no_nota_txt.setText(no_nota);
//            user_txt.setText(user);
//            total_biaya_txt.setText(total_biaya);
//            progressDialog.hide();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}