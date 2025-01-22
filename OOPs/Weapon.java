package OOPs;

public class Weapon implements ItemStuff{
    private String name;
    private int quantity;
    private int damage;
    private String type;
    

    public Weapon(String name, int quantity, int damage, String type){
        this.name  = name;
        this.quantity = quantity;
        this.damage = damage;
        this.type = type;
    }

    public int getDamage(){
        return damage;
    }

    public String getType(){
        return type;
    }

    @Override
    public int getQuantity(){
        return quantity;
    }

    @Override
    public String getName(){
        return name;
    }

    //function overriding : Run time polymorphism
    @Override
    public String toString(){
        return "Name: " + getName() + " Quantity: " + getQuantity() + " Damage: " + getDamage() + " Type: " + getType();
    }
}
