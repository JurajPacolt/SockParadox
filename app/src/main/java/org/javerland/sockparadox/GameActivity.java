package org.javerland.sockparadox;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        Button actionsButton = findViewById(R.id.btn_actions);
        actionsButton.setOnClickListener((v) -> {
            var sheet = new BottomSheetDialog(this);
            var view = getLayoutInflater().inflate(R.layout.dialog_actions, null);
            sheet.setContentView(view);
            sheet.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            sheet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            sheet.show();
        });
    }
}