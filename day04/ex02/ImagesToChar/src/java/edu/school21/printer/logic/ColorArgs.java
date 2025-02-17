package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ColorArgs {

    @Parameter(names = "--black", required = true)
    private String black;
    @Parameter(names = "--white", required = true)
    private String white;

    public String getBlack() {
        return black;
    }

    public String getWhite() {
        return white;
    }
}
