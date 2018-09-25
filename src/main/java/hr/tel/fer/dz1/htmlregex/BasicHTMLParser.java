package hr.tel.fer.dz1.htmlregex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class BasicHTMLParser {

    String html;

    public BasicHTMLParser(String sPath) throws IOException{

        if ( !sPath.endsWith(".html") ) {
            throw new IllegalArgumentException("Command line argument must be the path to an html file");
        }

        Path pPath = Paths.get(sPath);

        if ( !Files.exists(pPath) ) {
            throw new IllegalArgumentException("Command line argument must be the path to an existing html file");
        }

        StringBuilder tmp = new StringBuilder();
        Files.readAllLines(pPath).forEach(l -> tmp.append(l + System.lineSeparator()));
        html = tmp.toString();

    }

    public void run(){
        System.out.println("Welcome to BasicHTMLParser, a very poor HTML parsing tool.");
        BasicHTMLParserState state = BasicHTMLParserState.CONTINUE;
        try ( Scanner scanner = new Scanner(System.in) ) {
            while ( state == BasicHTMLParserState.CONTINUE ) {
                System.out.print(">");
                String[] inputSplit = scanner.nextLine().split(" ");
                Command command = CommandUtil.getCommand(inputSplit[0]);
                if ( command == null ) {
                    System.out.println("Unknown command, use \"help\" for a list of available commands");
                    continue;
                }
                state = command.run(html, Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
            }
        }
    }

    public static void main(String[] args){
        try {
            new BasicHTMLParser(args[0]).run();
        } catch ( IOException | IllegalArgumentException e ) {
            System.out.println(e.getMessage());
        }
    }
}
