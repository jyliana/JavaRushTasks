package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws Exception {
        System.out.println("Please enter full path to an archive file.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String archive = reader.readLine();

        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archive));

        System.out.println("Please enter full path to an file which will be archived.");
        String newFile = reader.readLine();
        zipFileManager.createZip(Paths.get(newFile));

        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();
    }
}
