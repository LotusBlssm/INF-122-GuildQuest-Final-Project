package GameAPI;

import java.util.UUID;

public class EntitySnapshot {
    private UUID entityId;
    private String entityType;
    private int x;
    private int y;
    private int health;

    public EntitySnapshot(UUID entityId, String entityType, int x, int y, int health) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }
}