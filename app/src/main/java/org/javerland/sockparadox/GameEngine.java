package org.javerland.sockparadox;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameEngine {
    private Context context;
    private Map<String, Room> rooms;
    private Map<String, ObjectDefinition> objects;
    private Map<String, Npc> npcs;
    private Set<String> inventory;
    private Set<String> unlockedRooms;
    private String currentRoomCode;
    private boolean gameEnded;

    public GameEngine(Context context) {
        this.context = context;
        this.inventory = new HashSet<>();
        this.unlockedRooms = new HashSet<>();
        this.rooms = new HashMap<>();
        this.objects = new HashMap<>();
        this.npcs = new HashMap<>();
        this.gameEnded = false;
        loadGameData();
        initializeGame();
    }

    private void loadGameData() {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.rooms);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            Type type = new TypeToken<GameData>() {}.getType();
            GameData gameData = gson.fromJson(json.toString(), type);

            for (ObjectDefinition obj : gameData.objectDefinitions) {
                objects.put(obj.getCode(), obj);
            }

            for (Npc npc : gameData.npcs) {
                npcs.put(npc.getCode(), npc);
            }

            for (Room room : gameData.rooms) {
                rooms.put(room.getRoomCode(), room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeGame() {
        currentRoomCode = "BEDROOM";
        unlockedRooms.add("BEDROOM");
        unlockedRooms.add("HALLWAY");
        unlockedRooms.add("BATHROOM");
        unlockedRooms.add("KITCHEN");
        unlockedRooms.add("LIVING_ROOM");
        unlockedRooms.add("BALCONY");
        inventory.add("SOCK1");
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoomCode);
    }

    public boolean canMoveToRoom(String roomCode) {
        return unlockedRooms.contains(roomCode);
    }

    public void moveToRoom(String roomCode) {
        if (canMoveToRoom(roomCode)) {
            currentRoomCode = roomCode;
        }
    }

    public List<Room.Action> getAvailableActions() {
        List<Room.Action> actions = new ArrayList<>();
        Room currentRoom = getCurrentRoom();

        if (currentRoom.getActions() != null) {
            for (Room.Action action : currentRoom.getActions()) {
                if (areConditionsMet(action.getConditions())) {
                    actions.add(action);
                }
            }
        }

        if (currentRoom.getNpcs() != null) {
            for (String npcCode : currentRoom.getNpcs()) {
                Npc npc = npcs.get(npcCode);
                if (npc != null && npc.getActions() != null) {
                    for (Npc.NpcAction npcAction : npc.getActions()) {
                        if (areConditionsMet(npcAction.getConditions())) {
                            Room.Action action = new Room.Action();
                            actions.add(convertNpcAction(npcAction));
                        }
                    }
                }
            }
        }

        return actions;
    }

    private Room.Action convertNpcAction(Npc.NpcAction npcAction) {
        return new Room.Action() {
            @Override
            public String getCommandKey() {
                return npcAction.getCommandKey();
            }

            @Override
            public List<Room.Condition> getConditions() {
                return npcAction.getConditions();
            }

            @Override
            public String getResultKey() {
                return npcAction.getResultKey();
            }

            @Override
            public List<Room.Effect> getEffects() {
                return npcAction.getEffects();
            }
        };
    }

    private boolean areConditionsMet(List<Room.Condition> conditions) {
        if (conditions == null || conditions.isEmpty()) {
            return true;
        }

        for (Room.Condition condition : conditions) {
            if (condition.getHasObject() != null) {
                if (!inventory.contains(condition.getHasObject())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String executeAction(Room.Action action) {
        if (action.getEffects() != null) {
            for (Room.Effect effect : action.getEffects()) {
                if (effect.getUnlockRoom() != null) {
                    unlockedRooms.add(effect.getUnlockRoom());
                }
                if (effect.getAddToInventory() != null) {
                    inventory.add(effect.getAddToInventory());
                }
                if (effect.getEndGame() != null && effect.getEndGame()) {
                    gameEnded = true;
                }
            }
        }
        return action.getResultKey();
    }

    public Set<String> getInventory() {
        return new HashSet<>(inventory);
    }

    public ObjectDefinition getObject(String code) {
        return objects.get(code);
    }

    public Npc getNpc(String code) {
        return npcs.get(code);
    }

    public String getString(String key) {
        int resId = context.getResources().getIdentifier(key, "string", context.getPackageName());
        if (resId != 0) {
            return context.getString(resId);
        }
        return key;
    }

    public String getTriggerString(String trigger) {
        String key = "trigger_" + trigger.toLowerCase();
        int resId = context.getResources().getIdentifier(key, "string", context.getPackageName());
        if (resId != 0) {
            return context.getString(resId);
        }
        return trigger;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public List<Room.Compass> getAvailableDirections() {
        Room currentRoom = getCurrentRoom();
        List<Room.Compass> available = new ArrayList<>();
        
        if (currentRoom.getCompass() != null) {
            for (Room.Compass compass : currentRoom.getCompass()) {
                if (canMoveToRoom(compass.getWayTo())) {
                    available.add(compass);
                }
            }
        }
        
        return available;
    }

    private static class GameData {
        List<ObjectDefinition> objectDefinitions;
        List<Npc> npcs;
        List<Room> rooms;
    }
}
