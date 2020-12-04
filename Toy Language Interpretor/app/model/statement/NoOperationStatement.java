package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.programstate.ProgramState;

public class NoOperationStatement implements InterfaceStatement{

    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        return null;
    }
}
