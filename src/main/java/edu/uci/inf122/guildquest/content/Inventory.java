package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Inventory {
    private List<Item> contents;

    public Inventory() {
        contents = new ArrayList<>();
    }

    public List<Item> getContents() {
        return contents;
    }

    public List<Item> getItems() {
        return contents;
    }

    public void addItem(Item item) {
        contents.add(item);
    }

    public boolean removeItem(Item item) {
        return contents.remove(item);
    }

    public boolean isEmpty(){
        return contents==null || contents.isEmpty();
    }

    public void forEach(Consumer<? super Item> action) {
    }
}
