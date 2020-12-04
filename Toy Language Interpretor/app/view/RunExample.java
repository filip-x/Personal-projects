package app.view;

import app.controller.Controller;
import app.exception.MyInterpreterException;

public class RunExample extends Command {

    private Controller ctr;

    public RunExample(String key, String description, Controller ctr) {
        super(key, description);
        this.ctr=ctr;
    }

    @Override
    public void execute() throws MyInterpreterException {
        try{
            ctr.allStep();
        }
        catch (Exception error)
        {
            throw new MyInterpreterException(error.getMessage());

        }
    }
}
