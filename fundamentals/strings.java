package fundamentals;
// Strings are immutable 


public class strings {
    public static void main(String[] args) {
        String str = "Balle balle shawa shawa";

        System.out.println(str.length());
        System.out.println(str.charAt(0));
        System.out.println(str.substring(0, 5));
        System.out.println(str.substring(6));
        System.out.println(str.indexOf("shawa"));
        System.out.println(str.indexOf("shawa", 7));
        System.out.println(str.lastIndexOf("shawa"));
        System.out.println(str.lastIndexOf("shawa", 7));
        System.out.println(str.equals("Balle balle shawa shawa"));
        System.out.println(str.equalsIgnoreCase("balle balle shawa shawa"));
        System.out.println(str.startsWith("Balle"));
        System.out.println(str.endsWith("shawa"));
        System.out.println(str.contains("shawa"));
        System.out.println(str.replace("shawa", "shava"));
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println(str.trim());
        System.out.println(str.concat(" balle"));
        System.out.println(str + " balle");
        System.out.println(str.compareTo("Balle balle shawa shawa"));
        System.out.println(str.compareToIgnoreCase("balle balle shawa shawa"));
        System.out.println(str.toCharArray());
        
    }
}
