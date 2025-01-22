package OOPs;

public abstract class Item {
    private String name;
    private int quantity;

    // Constructor to initialize the object 
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Getter Functions
    public int getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }

    // Setter Functions
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setName(String name) {
        this.name = name;
    }

    public abstract String toString();
}
