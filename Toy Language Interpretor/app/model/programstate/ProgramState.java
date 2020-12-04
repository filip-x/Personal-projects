package app.model.programstate;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.dictionary.MyDictionary;
import app.model.dictionary.MyHeap;
import app.model.list.InterfaceMyList;
import app.model.list.MyList;
import app.model.stack.InterfaceMyStack;
import app.model.stack.MyStack;
import app.model.statement.InterfaceStatement;
import app.model.value.InterfaceValue;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.Dictionary;

public class ProgramState {
    InterfaceMyStack <InterfaceStatement> executionStack;
    InterfaceMyDictionary <String, InterfaceValue> symbolTable;
    InterfaceMyList<InterfaceValue> out;
    InterfaceStatement originalProgram;//optional field, but good to have
    InterfaceMyDictionary <String,BufferedReader> fileTable;
    InterfaceMyHeap<Integer, InterfaceValue> heapTable;
    static int nextId = 1;
    int id;

    public ProgramState()
    {
        this.executionStack = new MyStack<InterfaceStatement>();
        this.symbolTable = new MyDictionary<String, InterfaceValue>();
        this.out = new MyList <InterfaceValue>();
        this.fileTable = new MyDictionary<String,BufferedReader>();
        this.heapTable = new MyHeap<Integer,InterfaceValue>();
        this.id = getNextId();



    }
    public ProgramState(InterfaceMyStack <InterfaceStatement> executionStack, InterfaceMyDictionary <String, InterfaceValue> symbolTable, InterfaceMyList<InterfaceValue> out,InterfaceStatement originalProgram,InterfaceMyDictionary<String,BufferedReader> fileTable,InterfaceMyHeap<Integer,InterfaceValue> heapTable){
      this.executionStack = executionStack;
      this.symbolTable = symbolTable;
      this.out = out;
      this.originalProgram = originalProgram;
      this.fileTable = fileTable;
      this.heapTable  = heapTable;
      if (originalProgram != null)
        executionStack.push(originalProgram);
      this.id = getNextId();
    }
    //getter
    public InterfaceMyStack<InterfaceStatement> getExecutionStack(){
        return this.executionStack;
    }
    public InterfaceMyDictionary <String, InterfaceValue> getSymbolTable(){
        return this.symbolTable;
    }
    public InterfaceMyList<InterfaceValue> getOut(){
        return this.out;
    }
    public InterfaceStatement getOriginalProgram(){
        return this.originalProgram;
    }
    //setter
    public void setExecutionStack(InterfaceMyStack<InterfaceStatement> setExecutionStack)
    {
        this.executionStack = setExecutionStack;
    }
    public void setSymbolTable( InterfaceMyDictionary <String, InterfaceValue>setSymbolTable){
        this.symbolTable = setSymbolTable;
    }
    public void setOut(  InterfaceMyList<InterfaceValue>setOut){
        this.out = setOut;
    }
    public void setOriginalProgram(InterfaceStatement setOriginalProgram){
        this.originalProgram = setOriginalProgram;
    }

    //file
    public InterfaceMyDictionary<String, BufferedReader> getFile()
    {
        return this.fileTable;
    }
    public void setFile(InterfaceMyDictionary<String, BufferedReader> file)
    {
        this.fileTable = file;
    }

    //heap
    public InterfaceMyHeap<Integer, InterfaceValue> getHeap()
    {
        return this.heapTable;
    }
    public void setHeap(InterfaceMyHeap<Integer, InterfaceValue> heap)
    {
        this.heapTable =heap;
    }
    public String toString(){
        return "ID: "+this.id+" Stack: "+this.executionStack+" SymbolTable: "+this.symbolTable+" Output: "+this.out+" Original Program: "+this.originalProgram;
    }

    //v4
    public Boolean isNotCompleted()
    {
        if(this.executionStack.isEmpty())
        {
            return false;
        }
        else
        {
            return true;

        }
    }

    public ProgramState oneStep() throws MyInterpreterException
    {
        if(this.executionStack.isEmpty())
            throw new MyInterpreterException("program state stack is empty");
        InterfaceStatement currentStatement = this.executionStack.pop();
        return currentStatement.execute(this);
    }

    static int getNextId()
    {
        return nextId++;
    }
    public int getId()
    {
        return this.id;
    }

}
