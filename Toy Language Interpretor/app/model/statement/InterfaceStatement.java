package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.programstate.ProgramState;

public interface InterfaceStatement {
    ProgramState execute(ProgramState state) throws MyInterpreterException;
    //which is the execution method for a statement

}
