package OOPs;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        Fruit fruit = new Fruit("Banana", 20, "Tropical");
        Weapon weapon = new Weapon("AR", 1, 35, "Gun");

        inventory.addItem(fruit);
        inventory.addItem(weapon);
        inventory.addItem("Orange", 15, "Citrus");

        inventory.displayInventory();
    }
    
}