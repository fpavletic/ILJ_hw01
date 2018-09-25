package hr.tel.fer.dz1.htmlregex.commands;

import hr.tel.fer.dz1.htmlregex.BasicHTMLParserState;
import hr.tel.fer.dz1.htmlregex.Command;
import hr.tel.fer.dz1.htmlregex.RegexUtil;

import java.util.Optional;
import java.util.regex.Pattern;

public class RegexIdCommand extends Command {

    private static final String[] ID_TO_NAME = new String[]{
            "all",
            "tag",
            "email",
            "ipv4",
            "tel"
    };


    public RegexIdCommand(int id){
        super(id, "regex", "tmp");
    }

    @Override
    public BasicHTMLParserState run(String html, String... args){
        if ( args.length != 2 ) {
            System.out.println("Invalid argument count, use \"help regex\" for more information");
            return BasicHTMLParserState.CONTINUE;
        }

        System.out.println(RegexUtil.getPatternAsString(ID_TO_NAME[Integer.parseInt(args[1])]));
        return BasicHTMLParserState.CONTINUE;
    }

    @Override
    public Optional<Pattern> getRegex(){
        return null;
    }
}
