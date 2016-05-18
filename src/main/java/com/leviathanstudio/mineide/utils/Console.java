package com.leviathanstudio.mineide.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javafx.scene.control.TextArea;

public class Console extends PrintStream
{
    private TextArea output;

    public Console(TextArea out) throws FileNotFoundException
    {
        super(new File(Util.LOG_DIR, "test.log"));
        this.output = out;
    }

    @Override
    public void write(int b)
    {
        addToConsole(new String(new char[] { (char) b }));
    }

    @Override
    public void write(byte[] buf, int off, int len)
    {
        addToConsole(new String(buf, off, len));
    }

    @Override
    public void println(String x)
    {
        addToConsole(x);
    }

    private void addToConsole(String text)
    {
        this.output.setText(this.output.getText() + "\n" + text);
    }
}