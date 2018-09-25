package hr.tel.fer.dz1.htmlregex.commands;

import hr.tel.fer.dz1.htmlregex.BasicHTMLParserState;
import hr.tel.fer.dz1.htmlregex.Command;
import hr.tel.fer.dz1.htmlregex.RegexUtil;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllCommand extends Command {


    public AllCommand(int id){
        super(id, "all", "tmp");
    }

    @Override
    public BasicHTMLParserState run(String html, String... args){

        if ( args.length > 2 ) {
            System.out.println("Invalid argument count, use \"help all\" for more information");
            return BasicHTMLParserState.CONTINUE;
        }

        Matcher m = (RegexUtil.hasRegexInfo(args.length == 0 ? "all" : args[0]) ?
                RegexUtil.getPattern(args.length == 0 ? "all" : args[0]).get() :
                Pattern.compile(RegexUtil.getPatternAsString("tag").replace("tag", args[0]))
        ).matcher(html);

        RegexUtil.find(m, -1).forEach(System.out::println);
        return BasicHTMLParserState.CONTINUE;
    }

    @Override
    public Optional<Pattern> getRegex(){
        return null;
    }
}
