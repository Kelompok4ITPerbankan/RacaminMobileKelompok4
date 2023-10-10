package com.example.rakaminmobilekelompok4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;

public class EditProfilActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText editNama;
    private EditText editStatus;
    private EditText editAlamat;
    private EditText editNomor;
    private Button simpanButton;
    private Button kembaliButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        dbHelper = new DatabaseHelper(this);
        editNama = findViewById(R.id.editNama);
        editStatus = findViewById(R.id.editStatus);
        editAlamat = findViewById(R.id.editAlamat);
        editNomor = findViewById(R.id.editNomor);
        simpanButton = findViewById(R.id.simpanButton);
        kembaliButton = findViewById(R.id.kembali);

        // Ambil data profil dari database dan tampilkan dalam EditText
        displayProfileData();
    }

    public void simpanProfil(View view) {
        // Simpan perubahan profil ke database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, editNama.getText().toString());
        values.put(DatabaseHelper.COLUMN_STATUS, editStatus.getText().toString());
        values.put(DatabaseHelper.COLUMN_ALAMAT, editAlamat.getText().toString());
        values.put(DatabaseHelper.COLUMN_NOMOR_TELEPON, editNomor.getText().toString());

        db.update(DatabaseHelper.TABLE_PROFILE, values, null, null);
        db.close();

        // Mengirim data yang diubah kembali ke ProfilActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("nama", editNama.getText().toString());
        resultIntent.putExtra("status", editStatus.getText().toString());
        resultIntent.putExtra("alamat", editAlamat.getText().toString());
        resultIntent.putExtra("nomorTelepon", editNomor.getText().toString());
        setResult(RESULT_OK, resultIntent);

        // Kembali ke ProfilActivity setelah menyimpan perubahan
        finish();
    }

    public void kembali(View view) {
        // Kembali ke ProfilActivity
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
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

            editNama.setText(nama);
            editStatus.setText(status);
            editAlamat.setText(alamat);
            editNomor.setText(nomorTelepon);

            cursor.close();
        }
        db.close();
    }
}
