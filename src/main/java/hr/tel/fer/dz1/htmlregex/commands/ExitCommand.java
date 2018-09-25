package hr.tel.fer.dz1.htmlregex.commands;

import hr.tel.fer.dz1.htmlregex.BasicHTMLParserState;
import hr.tel.fer.dz1.htmlregex.Command;

import java.util.Optional;
import java.util.regex.Pattern;

public class ExitCommand extends Command {

    public ExitCommand(int id){
        super(id, "exit", "tmp");
    }

    @Override
    public BasicHTMLParserState run(String html, String... args){
        System.out.println("Exiting");
        return BasicHTMLParserState.EXIT;
    }

    @Override
    public Optional<Pattern> getRegex(){
        return Optional.empty();
    }
}
