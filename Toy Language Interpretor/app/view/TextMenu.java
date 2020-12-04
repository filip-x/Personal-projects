package app.view;

import app.exception.MyInterpreterException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String,Command> commands;
    public TextMenu()
    {
        this.commands = new HashMap<String,Command>();
    }

    public void add_command(Command c)
    {
        commands.put(c.getKey(),c);
    }
    private void printMenu(){
        for(Command com :commands.values())
        {
            String line = com.getKey()+com.getDescription();
            System.out.println(line);
        }
    }
    public void show() throws MyInterpreterException {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if(com == null)
            {
                System.out.println("Invalid input");
                continue;
            }
            com.execute();
        }
    }
}
