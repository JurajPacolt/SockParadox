package org.javerland.sockparadox;

import java.util.List;

public class Room {
    private String roomCode;
    private String nameKey;
    private String descriptionKey;
    private String imageResource;
    private List<String> objects;
    private List<String> npcs;
    private List<Compass> compass;
    private List<Action> actions;

    public String getRoomCode() {
        return roomCode;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getDescriptionKey() {
        return descriptionKey;
    }

    public String getImageResource() {
        return imageResource;
    }

    public List<String> getObjects() {
        return objects;
    }

    public List<String> getNpcs() {
        return npcs;
    }

    public List<Compass> getCompass() {
        return compass;
    }

    public List<Action> getActions() {
        return actions;
    }

    public static class Compass {
        private String direction;
        private String wayTo;

        public String getDirection() {
            return direction;
        }

        public String getWayTo() {
            return wayTo;
        }
    }

    public static class Action {
        private String commandKey;
        private List<Condition> conditions;
        private String resultKey;
        private List<Effect> effects;

        public String getCommandKey() {
            return commandKey;
        }

        public List<Condition> getConditions() {
            return conditions;
        }

        public String getResultKey() {
            return resultKey;
        }

        public List<Effect> getEffects() {
            return effects;
        }
    }

    public static class Condition {
        private String hasObject;

        public String getHasObject() {
            return hasObject;
        }
    }

    public static class Effect {
        private String unlockRoom;
        private String addToInventory;
        private Boolean endGame;

        public String getUnlockRoom() {
            return unlockRoom;
        }

        public String getAddToInventory() {
            return addToInventory;
        }

        public Boolean getEndGame() {
            return endGame;
        }
    }
}
