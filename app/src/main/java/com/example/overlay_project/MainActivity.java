package com.example.overlay_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startOverlayButton = findViewById(R.id.start_overlay_button);
        startOverlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra quyền và yêu cầu quyền nếu cần
                if (Settings.canDrawOverlays(MainActivity.this)) {
                    startOverlay();
                } else {
                    // Yêu cầu người dùng cấp quyền
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    startActivity(intent);
                }
            }
        });
    }

    private void startOverlay() {
        // Khởi động dịch vụ overlay
        Intent intent = new Intent(MainActivity.this, OverlayService.class);
        startService(intent);
    }
}
