package application;

import util.FileUtils;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        fileUtils.readFile();

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            input = sc.next();
        }


    }
}
