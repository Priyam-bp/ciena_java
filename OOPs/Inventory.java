package OOPs;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<ItemStuff> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(ItemStuff item) {
        items.add(item);
    }

    // Function overloading
    public void addItem(String name, int quantity, String type) {
        items.add(new Fruit(name, quantity, type));
    }

    public void displayInventory() {
        for (ItemStuff item : items) {
            System.out.println(item.toString());
        }
    }
}
