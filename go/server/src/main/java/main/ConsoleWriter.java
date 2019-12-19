package main;

import java.io.PrintStream;

public class ConsoleWriter {

    private static PrintStream printStream = System.out;

    public static synchronized void setPrintStream(PrintStream printStream) {
        ConsoleWriter.printStream = printStream;
    }

    public static synchronized void println(String string) {
        printStream.println(string);
    }
}
