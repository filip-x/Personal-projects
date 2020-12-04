package app.view;

import app.exception.MyInterpreterException;

public abstract class Command {
    private String key,description;
    public Command(String key,String description)
    {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws MyInterpreterException;

    public String getKey(){
        return  this.key;
    }

    public String getDescription(){
        return  this.description;
    }
}

