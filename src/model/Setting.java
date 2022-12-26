package model;

import util.IntReaderUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author SwordFlame
 */
public class Setting {
    public static Scanner getScanner() throws FileNotFoundException {
        return new Scanner(new File(".///settings//configuration.txt"));
    }
}
