package moe.windleaf.WLKits;

public class SLogger {
    protected static String NAME;

    protected SLogger(String name) { NAME = name; }

    public void log(String s) { System.out.println(s); }
}
