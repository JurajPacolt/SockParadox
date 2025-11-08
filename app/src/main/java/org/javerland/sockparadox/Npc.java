package org.javerland.sockparadox;

import java.util.List;

public class Npc {
    private String code;
    private String nameKey;
    private String descriptionKey;
    private List<Dialogue> dialogues;
    private List<NpcAction> actions;

    public String getCode() {
        return code;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getDescriptionKey() {
        return descriptionKey;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public List<NpcAction> getActions() {
        return actions;
    }

    public static class Dialogue {
        private String trigger;
        private String textKey;

        public String getTrigger() {
            return trigger;
        }

        public String getTextKey() {
            return textKey;
        }
    }

    public static class NpcAction {
        private String commandKey;
        private List<Room.Condition> conditions;
        private String resultKey;
        private List<Room.Effect> effects;

        public String getCommandKey() {
            return commandKey;
        }

        public List<Room.Condition> getConditions() {
            return conditions;
        }

        public String getResultKey() {
            return resultKey;
        }

        public List<Room.Effect> getEffects() {
            return effects;
        }
    }
}
