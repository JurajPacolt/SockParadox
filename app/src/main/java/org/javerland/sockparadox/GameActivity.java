package org.javerland.sockparadox;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

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

        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener((v) -> showGameMenu(v));
    }

    private void showGameMenu(android.view.View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.game_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_quit_game) {
                showQuitDialog();
                return true;
            }
            return false;
        });
        popup.show();
    }

    private void showQuitDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.quit_game_title)
                .setMessage(R.string.quit_game_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    finish();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}