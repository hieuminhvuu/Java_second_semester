package com.lab5;
import java.io.IOException;

public class Main {
    static String file;
    public static void main(String[] args) throws IOException {
        System.out.println("\t* Welcome to my Lab. Have a good day !!!\n\t* Enter your command or write \"help\" to know all commands we have !!!");
        try{
            file = args[0];
            FileParserCSV read = new FileParserCSV(file);
        } catch (ArrayIndexOutOfBoundsException exception){
            System.exit(0);
        }
    }
}
