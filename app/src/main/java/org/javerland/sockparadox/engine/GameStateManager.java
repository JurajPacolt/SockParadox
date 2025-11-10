package org.javerland.sockparadox.engine;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GameStateManager {
    private static final String PREFS_NAME = "SockParadoxGameState";
    private static final String KEY_CURRENT_ROOM = "currentRoom";
    private static final String KEY_INVENTORY = "inventory";
    private static final String KEY_UNLOCKED_ROOMS = "unlockedRooms";
    private static final String KEY_COMPLETED_ACTIONS = "completedActions";
    private static final String KEY_GAME_ENDED = "gameEnded";
    private static final String KEY_HAS_SAVED_STATE = "hasSavedState";

    private SharedPreferences prefs;
    private Gson gson;

    public GameStateManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveGameState(String currentRoom, Set<String> inventory, 
                             Set<String> unlockedRooms, Set<String> completedActions, 
                             boolean gameEnded) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_CURRENT_ROOM, currentRoom);
        editor.putString(KEY_INVENTORY, gson.toJson(inventory));
        editor.putString(KEY_UNLOCKED_ROOMS, gson.toJson(unlockedRooms));
        editor.putString(KEY_COMPLETED_ACTIONS, gson.toJson(completedActions));
        editor.putBoolean(KEY_GAME_ENDED, gameEnded);
        editor.putBoolean(KEY_HAS_SAVED_STATE, true);
        editor.apply();
    }

    public boolean hasSavedState() {
        return prefs.getBoolean(KEY_HAS_SAVED_STATE, false);
    }

    public GameState loadGameState() {
        if (!hasSavedState()) {
            return null;
        }

        GameState state = new GameState();
        state.currentRoom = prefs.getString(KEY_CURRENT_ROOM, "BEDROOM");
        
        Type setType = new TypeToken<HashSet<String>>(){}.getType();
        state.inventory = gson.fromJson(prefs.getString(KEY_INVENTORY, "[]"), setType);
        state.unlockedRooms = gson.fromJson(prefs.getString(KEY_UNLOCKED_ROOMS, "[]"), setType);
        state.completedActions = gson.fromJson(prefs.getString(KEY_COMPLETED_ACTIONS, "[]"), setType);
        state.gameEnded = prefs.getBoolean(KEY_GAME_ENDED, false);

        return state;
    }

    public void clearGameState() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static class GameState {
        public String currentRoom;
        public Set<String> inventory;
        public Set<String> unlockedRooms;
        public Set<String> completedActions;
        public boolean gameEnded;
    }
}
