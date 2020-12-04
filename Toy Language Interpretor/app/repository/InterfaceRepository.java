package app.repository;

import app.exception.MyInterpreterException;
import app.model.list.InterfaceMyList;
import app.model.programstate.ProgramState;

import java.util.List;

public interface InterfaceRepository  {

    //ProgramState getCurrentProgram();
    void logProgramStateExecution(ProgramState state) throws MyInterpreterException;
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> listPrograms);

}

