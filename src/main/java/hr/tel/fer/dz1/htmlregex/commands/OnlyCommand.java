package hr.tel.fer.dz1.htmlregex.commands;

import hr.tel.fer.dz1.htmlregex.BasicHTMLParserState;
import hr.tel.fer.dz1.htmlregex.Command;
import hr.tel.fer.dz1.htmlregex.RegexUtil;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlyCommand extends Command {


    public OnlyCommand(int id){
        super(id, "only", "tmp");
    }

    @Override
    public BasicHTMLParserState run(String html, String... args){

        if ( args.length != 2 ) {
            System.out.println("Invalid argument count, use \"help only\" for more information");
            return BasicHTMLParserState.CONTINUE;
        }

        if ( !args[1].matches("\\d+") ) {
            System.out.println("Second argument must be a number");
            return BasicHTMLParserState.CONTINUE;
        }

        int count = Integer.parseInt(args[1]);
        Matcher m = (RegexUtil.hasRegexInfo(args[0]) ?
                RegexUtil.getPattern(args[0]).get() :
                Pattern.compile(RegexUtil.getPatternAsString("tag").replace("tag", args[0]))
        ).matcher(html);

        RegexUtil.find(m, count).forEach(System.out::println);
        return BasicHTMLParserState.CONTINUE;

    }

    @Override
    public Optional<Pattern> getRegex(){
        return null;
    }
}
