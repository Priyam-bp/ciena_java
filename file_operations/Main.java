package file_operations;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a file
        File myFile = new File("file_operations/myFile.txt");
        try {
            myFile.createNewFile();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Write to a file
        try {
            FileWriter fileWriter = new FileWriter("file_operations/myFile.txt");
            fileWriter.write("This is a sample text in the file. \n same sample text but diff line");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Read from a file
        try {
            File myFile2 = new File("file_operations/myFile.txt");
            Scanner sc = new Scanner(myFile2);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Delete a file
        File myFile3 = new File("file_operations/myFile2.txt");
        try {
            myFile3.createNewFile();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if(myFile3.delete()){
            System.out.println("File deleted successfully." + myFile3.getName());
        }
        else{
            System.out.println("Failed to delete the file.");
        }
    }
}
