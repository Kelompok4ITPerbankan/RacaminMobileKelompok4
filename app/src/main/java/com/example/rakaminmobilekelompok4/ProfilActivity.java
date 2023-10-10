package com.example.rakaminmobilekelompok4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private TextView namaTextView;
    private TextView statusTextView;
    private TextView alamatTextView;
    private TextView nomorTextView;
    private static final int EDIT_PROFILE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampil_profil);

        dbHelper = new DatabaseHelper(this);
        namaTextView = findViewById(R.id.namaTextView);
        statusTextView = findViewById(R.id.statusTextView);
        alamatTextView = findViewById(R.id.alamatTextView);
        nomorTextView = findViewById(R.id.nomorTextView);

        // Ambil data profil dari database dan tampilkan
        displayProfileData();
    }

    public void editProfil(View view) {
        // Redirect ke activity EditProfileActivity untuk mengedit profil
        Intent intent = new Intent(this, EditProfilActivity.class);
        startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE);
    }

    private void displayProfileData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_NAMA,
                DatabaseHelper.COLUMN_STATUS,
                DatabaseHelper.COLUMN_ALAMAT,
                DatabaseHelper.COLUMN_NOMOR_TELEPON
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_PROFILE,
                projection,
                null, null, null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String nama = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA));
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS));
            @SuppressLint("Range") String alamat = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ALAMAT));
            @SuppressLint("Range") String nomorTelepon = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMOR_TELEPON));

            namaTextView.setText("Nama: " + nama);
            statusTextView.setText("Status: " + status);
            alamatTextView.setText("Alamat: " + alamat);
            nomorTextView.setText("Nomor Telepon: " + nomorTelepon);

            cursor.close();
        }
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Mendapatkan data yang diubah dari EditProfileActivity
            String nama = data.getStringExtra("nama");
            String status = data.getStringExtra("status");
            String alamat = data.getStringExtra("alamat");
            String nomorTelepon = data.getStringExtra("nomorTelepon");

            // Menampilkan data yang diubah secara langsung
            namaTextView.setText("Nama: " + nama);
            statusTextView.setText("Status: " + status);
            alamatTextView.setText("Alamat: " + alamat);
            nomorTextView.setText("Nomor Telepon: " + nomorTelepon);
        }
    }

    public void kembali(View view) {
        // Redirect kembali ke DashboardActivity
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }


}
