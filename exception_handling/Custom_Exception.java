package exception_handling;

class PriyamException extends Exception{
    public PriyamException(String s){
        // System.out.println(s);
        super(s);
    }
}

public class Custom_Exception {
    public static void main(String[] args) {
        int i = 0;
        try {
            if(i == 0){
                throw new PriyamException("This is a custom exception.");
            }
        } catch(PriyamException e){
            System.out.println("Custom Exception");
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
}
