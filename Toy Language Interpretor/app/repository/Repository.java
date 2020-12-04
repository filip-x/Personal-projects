package app.repository;

import app.exception.MyInterpreterException;
import app.model.list.InterfaceMyList;
import app.model.list.MyList;
import app.model.programstate.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Repository implements  InterfaceRepository {

    List<ProgramState> listOfProgramStates = new LinkedList<>();
    String logFilePath = "";


    public Repository(ProgramState programState, String filePath){
        this.listOfProgramStates.add(programState);
        this.logFilePath = filePath;
    }
    //public ProgramState getCurrentProgram() {return this.listOfProgramStates.getElementFromPosition(0);}

    public void logProgramStateExecution(ProgramState state) throws MyInterpreterException {
        try{

            PrintWriter openFile;
            openFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath,true)));
            openFile.println(("ID: "));
            openFile.println(state.getId());
            openFile.println("Execution Stack: ");
            openFile.println(state.getExecutionStack().printIterationOfTheStack());
            openFile.println("Symbolic Table: ");
            openFile.println(state.getSymbolTable().toString());
            openFile.println("Out: ");
            openFile.println(state.getOut().toString());
            openFile.println("File Table: ");
            openFile.println(state.getFile().toString());
            openFile.println("Heap Table: ");
            openFile.println(state.getHeap().toString());
            openFile.close();

        }
        catch (Exception error)
        {
            throw new MyInterpreterException("An error has occured: "+error.getMessage());
        }

    }

    public List<ProgramState> getProgramStateList() {
        return this.listOfProgramStates;
    }

    public void setProgramStateList (List<ProgramState> listPrograms) {
        this.listOfProgramStates = listPrograms;
    }
}
