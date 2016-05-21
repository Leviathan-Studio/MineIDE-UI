package com.leviathanstudio.mineide.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Command
{
    public ArrayList<String> commands = new ArrayList<>();

    public Command(String... command)
    {
        for (String task : command)
            this.addCommand(task);
    }

    public Command addCommand(String command)
    {
        this.commands.add(command);
        return this;
    }

    public Command addSuccessCommand(String command)
    {
        this.addCommand(" && " + command);
        return this;
    }

    public Command addFailledCommand(String command)
    {
        this.addCommand(" || " + command);
        return this;
    }

    public String build()
    {
        String res = "";

        for (String command : this.commands)
            res += command;

        res.substring(4);
        return res;
    }

    public ProcessBuilder execute(File directory) throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder();

        switch (OSHelper.getPlatform())
        {
            case WINDOWS:
                processBuilder.command("cmd", "/C", this.build());
                break;
            case LINUX:
                processBuilder.command("sh", "/bin/sh", this.build());
                break;
            case MACOS:
                processBuilder.command("sh", "/bin/sh", this.build());
                break;
            default:
                break;

        }
        processBuilder.directory(directory);

        processBuilder.inheritIO();
        processBuilder.start();
        return processBuilder;
    }
}
