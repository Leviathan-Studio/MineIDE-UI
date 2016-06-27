package com.leviathanstudio.mineide.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

import com.leviathanstudio.mineide.util.Util;

import javafx.scene.control.TextArea;

public class Console extends PrintStream
{
    private TextArea output;
    private File     logFile;

    public Console(TextArea out) throws FileNotFoundException
    {
        super(getLogFile());
        this.logFile = getLogFile();
        this.output = out;
        this.output.setWrapText(true);
    }

    @Override
    public void write(int b)
    {
        this.addToConsole(new String(new char[] { (char) b }));
    }

    @Override
    public void write(byte[] buf, int off, int len)
    {
        this.addToConsole(new String(buf, off, len));
    }

    @Override
    public void println(String x)
    {
        this.addToConsole(x);
    }

    private void addToConsole(String text)
    {
        final String logContent = this.output.getText() + text + "\n";
        this.output.setText(logContent);
        this.writeLog(logContent);
    }

    public static File getLogFile()
    {
        String res = "";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        res = String.format("%1$04d%2$02d%3$02d%4$02d%5$02d%6$02d", date.getYear(), date.getMonth().getValue(),
                date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());

        return new File(Util.LOG_DIR, res + ".log");
    }

    private void writeLog(String logContent)
    {
        Util.writeFile(this.logFile, logContent);
    }
}