package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.expresion.InterfaceExpression;
import app.model.list.InterfaceMyList;
import app.model.programstate.ProgramState;
import app.model.stack.InterfaceMyStack;
import app.model.type.BoolType;
import app.model.type.InterfaceType;
import app.model.value.BoolValue;
import app.model.value.InterfaceValue;

import java.io.BufferedReader;

public class WhileStatement implements InterfaceStatement{

    InterfaceExpression expression1;
    InterfaceStatement statement1;
    public WhileStatement(InterfaceExpression expression1,InterfaceStatement statement1){
        this.expression1 = expression1;
        this.statement1 = statement1;

    }
    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceMyHeap<Integer,InterfaceValue>heap = state.getHeap();
        InterfaceMyDictionary<String, BufferedReader> fileTable = state.getFile();
        InterfaceMyList<InterfaceValue> out = state.getOut();
        InterfaceValue valueExpression;

        valueExpression = expression1.evaluate(symbolTable,heap);
        if (valueExpression.getType().equals(new BoolType()))
        {
            BoolValue bool = (BoolValue) valueExpression;// from interfaceValue to BoolValue
            if (bool.getValue() == true)
            {
                stack.push(this);// we push while again
                stack.push(this.statement1);
            }

        }
        else
        {
            throw new MyInterpreterException("it is not boolean");
        }
        return null;
    }
    public String toString()
    {
        return "while("+expression1+"){"+statement1+"}";
    }
}
