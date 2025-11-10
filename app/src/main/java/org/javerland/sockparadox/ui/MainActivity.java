package org.javerland.sockparadox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.javerland.sockparadox.R;
import org.javerland.sockparadox.engine.GameStateManager;

public class MainActivity extends AppCompatActivity {

    private GameStateManager stateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        stateManager = new GameStateManager(this);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("loadSavedState", false);
                startActivity(intent);
            }
        });

        ImageButton walkthroughButton = findViewById(R.id.walkthroughButton);
        walkthroughButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WalkthroughActivity.class);
                startActivity(intent);
            }
        });

        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingsDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSettingsButtonVisibility();
    }

    private void updateSettingsButtonVisibility() {
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        if (stateManager.hasSavedState()) {
            settingsButton.setVisibility(View.VISIBLE);
        } else {
            settingsButton.setVisibility(View.GONE);
        }
    }

    private void showSettingsDialog() {
        if (!stateManager.hasSavedState()) {
            return;
        }

        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setTitle(R.string.settings_title)
                .setMessage(R.string.settings_message)
                .setPositiveButton(R.string.continue_game, (dialog, which) -> {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("loadSavedState", true);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.reset_game, (dialog, which) -> {
                    showResetConfirmDialog();
                })
                .setNeutralButton(R.string.cancel, null)
                .show();
    }

    private void showResetConfirmDialog() {
        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setTitle(R.string.reset_game_title)
                .setMessage(R.string.reset_game_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    stateManager.clearGameState();
                    updateSettingsButtonVisibility();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}