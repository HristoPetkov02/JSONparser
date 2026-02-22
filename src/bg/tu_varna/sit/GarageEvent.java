package bg.tu_varna.sit;

public class GarageEvent {
    public enum Type { ADDED, UPDATED, REMOVED }

    private final Type type;
    private final String key;

    public GarageEvent(Type type, String key) {
        this.type = type;
        this.key = key;
    }

    public Type getType() { return type; }
    public String getKey() { return key; }
}