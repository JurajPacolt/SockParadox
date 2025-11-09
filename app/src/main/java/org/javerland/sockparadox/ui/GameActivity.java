package org.javerland.sockparadox.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.javerland.sockparadox.R;
import org.javerland.sockparadox.engine.GameEngine;
import org.javerland.sockparadox.model.Npc;
import org.javerland.sockparadox.model.ObjectDefinition;
import org.javerland.sockparadox.model.Room;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private ImageView roomImage;
    private TextView roomTitle;
    private TextView roomDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        gameEngine = new GameEngine(this);
        
        roomImage = findViewById(R.id.room_image);
        roomTitle = findViewById(R.id.room_title);
        roomDescription = findViewById(R.id.room_description);

        updateUI();

        Button actionsButton = findViewById(R.id.btn_actions);
        actionsButton.setOnClickListener((v) -> showActionsDialog());

        Button inventoryButton = findViewById(R.id.btn_inventory);
        inventoryButton.setOnClickListener((v) -> showInventoryDialog());

        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener((v) -> showGameMenu(v));
        
        setupCompassButtons();
    }

    private void updateUI() {
        Room currentRoom = gameEngine.getCurrentRoom();
        
        roomTitle.setText(gameEngine.getString(currentRoom.getNameKey()));
        roomDescription.setText(gameEngine.getString(currentRoom.getDescriptionKey()));
        
        int imageResId = getResources().getIdentifier(currentRoom.getImageResource(), "drawable", getPackageName());
        if (imageResId != 0) {
            roomImage.setImageResource(imageResId);
        }
        
        updateCompassButtons();
        
        if (gameEngine.isGameEnded()) {
            showEndGameDialog();
        }
    }

    private void setupCompassButtons() {
        findViewById(R.id.btn_north).setOnClickListener(v -> moveDirection("NORTH"));
        findViewById(R.id.btn_south).setOnClickListener(v -> moveDirection("SOUTH"));
        findViewById(R.id.btn_east).setOnClickListener(v -> moveDirection("EAST"));
        findViewById(R.id.btn_west).setOnClickListener(v -> moveDirection("WEST"));
    }

    private void updateCompassButtons() {
        ImageButton northBtn = findViewById(R.id.btn_north);
        ImageButton southBtn = findViewById(R.id.btn_south);
        ImageButton eastBtn = findViewById(R.id.btn_east);
        ImageButton westBtn = findViewById(R.id.btn_west);

        northBtn.setVisibility(View.GONE);
        southBtn.setVisibility(View.GONE);
        eastBtn.setVisibility(View.GONE);
        westBtn.setVisibility(View.GONE);

        List<Room.Compass> directions = gameEngine.getAvailableDirections();
        for (Room.Compass compass : directions) {
            switch (compass.getDirection()) {
                case "NORTH":
                    northBtn.setVisibility(View.VISIBLE);
                    break;
                case "SOUTH":
                    southBtn.setVisibility(View.VISIBLE);
                    break;
                case "EAST":
                    eastBtn.setVisibility(View.VISIBLE);
                    break;
                case "WEST":
                    westBtn.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void moveDirection(String direction) {
        List<Room.Compass> directions = gameEngine.getAvailableDirections();
        for (Room.Compass compass : directions) {
            if (compass.getDirection().equals(direction)) {
                gameEngine.moveToRoom(compass.getWayTo());
                updateUI();
                return;
            }
        }
    }

    private void showActionsDialog() {
        BottomSheetDialog sheet = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_actions, null);
        
        ListView actionList = view.findViewById(R.id.action_list);
        List<Room.Action> actions = gameEngine.getAvailableActions();
        
        Room currentRoom = gameEngine.getCurrentRoom();
        List<String> npcCodes = currentRoom.getNpcs();
        
        List<String> actionNames = new ArrayList<>();
        for (Room.Action action : actions) {
            actionNames.add(gameEngine.getString(action.getCommandKey()));
        }
        
        if (npcCodes != null) {
            for (String npcCode : npcCodes) {
                Npc npc = gameEngine.getNpc(npcCode);
                if (npc != null && npc.getDialogues() != null) {
                    for (Npc.Dialogue dialogue : npc.getDialogues()) {
                        actionNames.add(gameEngine.getTriggerString(dialogue.getTrigger()) + " - " + gameEngine.getString(npc.getNameKey()));
                    }
                }
            }
        }
        
        if (actionNames.isEmpty()) {
            actionNames.add("Žiadne dostupné akcie");
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                R.layout.list_item_action, actionNames);
        actionList.setAdapter(adapter);
        
        actionList.setOnItemClickListener((parent, v, position, id) -> {
            if (position < actions.size()) {
                Room.Action selectedAction = actions.get(position);
                String resultKey = gameEngine.executeAction(selectedAction);
                
                showCustomToast(gameEngine.getString(resultKey));
                updateUI();
                sheet.dismiss();
            } else {
                int dialogueIndex = position - actions.size();
                int counter = 0;
                
                if (npcCodes != null) {
                    for (String npcCode : npcCodes) {
                        Npc npc = gameEngine.getNpc(npcCode);
                        if (npc != null && npc.getDialogues() != null) {
                            for (Npc.Dialogue dialogue : npc.getDialogues()) {
                                if (counter == dialogueIndex) {
                                    showCustomToast(gameEngine.getString(dialogue.getTextKey()));
                                    sheet.dismiss();
                                    return;
                                }
                                counter++;
                            }
                        }
                    }
                }
            }
        });
        
        sheet.setContentView(view);
        sheet.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        sheet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sheet.show();
    }

    private void showInventoryDialog() {
        List<String> inventoryItems = new ArrayList<>();
        for (String objCode : gameEngine.getInventory()) {
            ObjectDefinition obj = gameEngine.getObject(objCode);
            if (obj != null) {
                inventoryItems.add(gameEngine.getString(obj.getNameKey()));
            }
        }
        
        if (inventoryItems.isEmpty()) {
            showCustomToast("Inventár je prázdny");
            return;
        }
        
        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setTitle("Inventár")
                .setItems(inventoryItems.toArray(new String[0]), (dialog, which) -> {
                    String objCode = new ArrayList<>(gameEngine.getInventory()).get(which);
                    ObjectDefinition obj = gameEngine.getObject(objCode);
                    if (obj != null) {
                        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                                .setTitle(gameEngine.getString(obj.getNameKey()))
                                .setMessage(gameEngine.getString(obj.getDescriptionKey()))
                                .setPositiveButton("OK", null)
                                .show();
                    }
                })
                .setNegativeButton("Zavrieť", null)
                .show();
    }

    private void showEndGameDialog() {
        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setTitle("Gratulujem!")
                .setMessage(gameEngine.getString("ending_message"))
                .setPositiveButton("Koniec", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    private void showCustomToast(String message) {
        View layout = getLayoutInflater().inflate(R.layout.custom_toast, null);
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 150);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void showGameMenu(android.view.View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor, Gravity.END, 0, R.style.CustomPopupMenu);
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
        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setTitle(R.string.quit_game_title)
                .setMessage(R.string.quit_game_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    finish();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}