package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.IOException;

public class Archiver {
    public static void main(String[] args) throws Exception {
        /*System.out.println("Please enter full path to an archive file.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String archive = reader.readLine();

        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archive));

        System.out.println("Please enter full path to an file which will be archived.");
        String newFile = reader.readLine();
        zipFileManager.createZip(Paths.get(newFile));

        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();*/

        Operation operation;
        while (true) {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
                if (operation == Operation.EXIT)
                    break;
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }
    }

    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Выберите операцию:\n" +
                "0 - упаковать файлы в архив\n" +
                "1 - добавить файл в архив\n" +
                "2 - удалить файл из архива\n" +
                "3 - распаковать архив\n" +
                "4 - просмотреть содержимое архива\n" +
                "5 – выход");
        int choose = ConsoleHelper.readInt();
        return Operation.values()[choose];
    }
}
