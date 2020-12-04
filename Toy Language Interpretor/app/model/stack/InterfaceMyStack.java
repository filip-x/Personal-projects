package app.model.stack;

import app.exception.MyInterpreterException;

public interface InterfaceMyStack <dataType>{
    dataType pop() throws MyInterpreterException;
    void push(dataType value);
    boolean isEmpty();
    String printIterationOfTheStack();
    dataType peek();// varf


}
