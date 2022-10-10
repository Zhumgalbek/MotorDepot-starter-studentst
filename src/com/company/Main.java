package com.company;

import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    final static Scanner scannerA = new Scanner(System.in);
    final static Scanner scannerB = new Scanner(System.in);
    final static Scanner scannerC = new Scanner(System.in);

    public static final GsonBuilder BUILDER = new GsonBuilder();

    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {
        ServiceImpl service = new ServiceImpl();
        String foo = " ";

    while (!foo.equals("x")){
        try {
            service.truk();
        buttons();
        System.out.print("choose a number from 1 to 4 : ");
        foo = scannerB.nextLine();
        System.out.println("\n");
        switch (foo){
            case "1" -> service.changeDriver();
            case "2" -> service.startDriving();
            case "3" -> service.startRepair();
            default -> System.out.println("choose a number from 1 to 4 !!!");
        }
    }catch (Exception exception){
            System.out.println("choose a number from 1 to 4 !!!");
        }
}

    }

    public static void buttons(){
        System.out.println("\nPress 1 to change Driver\n" +
        "Press 2 to send to the Route\n" +
                "Press 3 to REPAIR \n");
    }


   public static String readTtuck() {
       return getString(WRITE_PATH);
   }

   public static String readDriver() {
       return getString(WRITE_PATH1);
   }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))){
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}