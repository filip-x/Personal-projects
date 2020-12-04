package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.expresion.InterfaceExpression;
import app.model.list.InterfaceMyList;
import app.model.programstate.ProgramState;

public class PrintStatement implements  InterfaceStatement {

    InterfaceExpression expression;

    public PrintStatement(InterfaceExpression expression){
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        state.getOut().addElement(expression.evaluate(state.getSymbolTable(),state.getHeap()));
        return null;
    }

    public String toString()
    {
        return "print("+expression.toString()+")";
    }
}
