package com.example.rakaminmobilekelompok4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        ImageButton viewProfileButton = findViewById(R.id.viewProfileButton);

        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika ImageButton ditekan, arahkan ke ProfilActivity
                Intent intent = new Intent(DashboardActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logout(View view) {
        // Ketika tombol "Logout" ditekan, arahkan kembali ke MainActivity
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


