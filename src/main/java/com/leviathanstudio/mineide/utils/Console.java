package com.leviathanstudio.mineide.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.control.TextArea;

public class Console extends PrintStream
{
    private TextArea output;

    public Console(TextArea out) throws FileNotFoundException
    {
        super(new File(Util.LOG_DIR, getLogFileName()));
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
        this.output.setText(this.output.getText() + text + "\n");
    }

    public static String getLogFileName()
    {
        String res = "";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        res += String.format("%1$04d%2$02d%3$02d%4$02d%5$02d%6$02d", date.getYear(), date.getMonth().getValue(),
                date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());

        return res + ".log";
    }
}