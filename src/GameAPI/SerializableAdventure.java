package GameAPI;

public interface SerializableAdventure {
    AdventureSnapshot save();
    void load(AdventureSnapshot snapshot);
}