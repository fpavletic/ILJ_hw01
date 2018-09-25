package hr.tel.fer.dz1.htmlregex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {

    private int id;
    private String name;
    private String help;

    protected Command(int id, String name, String help){
        this.id = id;
        this.name = name;
        this.help = help;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getHelp(){
        return help;
    }

    @Override
    public boolean equals(Object o){
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Command command = (Command) o;

        return name.equals(command.name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    protected static List<String> find(Matcher m, int n){

        List<String> matches = new ArrayList<>();
        while ( m.find() && n-- != 0 ) {
            matches.add(m.group());
        }
        return matches;
    }

    protected static int getCount(String s){
        Optional<Integer> tmp = (s == null ? Optional.empty() : parseInt(s));
        return tmp.isPresent() ? tmp.get() : -1;
    }

    protected static Optional<Integer> parseInt(String s){
        return !s.matches("\\d+") ? Optional.empty() : Optional.of(Integer.parseInt(s));
    }

    public abstract BasicHTMLParserState run(String html, String... args);

    public abstract Optional<Pattern> getRegex();
}
