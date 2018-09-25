package hr.tel.fer.dz1.htmlregex.commands;

import hr.tel.fer.dz1.htmlregex.BasicHTMLParserState;
import hr.tel.fer.dz1.htmlregex.Command;
import hr.tel.fer.dz1.htmlregex.CommandUtil;

import java.util.Optional;
import java.util.regex.Pattern;

public class HelpCommand extends Command {

    public HelpCommand(int id){
        super(id, "help", "tmp");
    }

    @Override
    public BasicHTMLParserState run(String html, String... args){
        if ( args.length > 1 ) {
            System.out.println("Invalid argument count, use \"help help\" for more information");
            return BasicHTMLParserState.CONTINUE;
        }

        switch ( args.length ) {
            case 0:
                System.out.print(" | ");
                CommandUtil.getCommands().forEach(c -> System.out.format("%s[%d] | ", c.getName(), c.getId()));
                System.out.println();
                break;
            default:
                Command command = args[0].matches("\\d+") ?
                        CommandUtil.getCommand(Integer.parseInt(args[0])) : CommandUtil.getCommand(args[0]);
                if ( command == null ) {
                    System.out.println("Unknown command, use \"help\" for a list of available commands");
                    return BasicHTMLParserState.CONTINUE;
                }
                System.out.format("%s[%d]:%4$s\t%s %4$s", command.getName(), command.getId(), command.getHelp(), System.lineSeparator());
        }

        return BasicHTMLParserState.CONTINUE;
    }

    @Override
    public Optional<Pattern> getRegex(){
        return Optional.empty();
    }
}
