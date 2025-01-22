package OOPs;

public class Fruit implements ItemStuff {
    private String type;
    private String name;
    private int quantity;

    public Fruit(String name, int quantity, String type){
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + " Quantity: " + getQuantity() + " Type: " + type;
    }
}
