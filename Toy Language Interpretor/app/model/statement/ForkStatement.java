package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.list.InterfaceMyList;
import app.model.programstate.ProgramState;
import app.model.stack.InterfaceMyStack;
import app.model.stack.MyStack;
import app.model.value.InterfaceValue;

import java.io.BufferedReader;

public class ForkStatement implements InterfaceStatement {

    InterfaceStatement statement;

    public ForkStatement(InterfaceStatement statement)
    {
        this.statement = statement;
    }


    public ProgramState execute(ProgramState state) throws MyInterpreterException {

        InterfaceMyStack<InterfaceStatement> stack = new MyStack<>();
        stack.push(statement);
        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable().clone();
        //clone
        InterfaceMyHeap<Integer,InterfaceValue> heap = state.getHeap();
        InterfaceMyDictionary<String, BufferedReader> fileTable = state.getFile();
        InterfaceMyList<InterfaceValue> out = state.getOut();

        return new ProgramState(stack,symbolTable,out,statement,fileTable,heap);
    }

    public String toString()
    {
        return "Fork Statement:"+this.statement;
    }
}
