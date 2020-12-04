package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.type.BoolType;
import app.model.value.BoolValue;
import app.model.value.InterfaceValue;
// we eval the expression if it's true then we execute then otherwise elsestatement
public class IfStatement implements  InterfaceStatement{
    InterfaceExpression expression;
    InterfaceStatement thenStatement;
    InterfaceStatement elseStatement;
    public IfStatement(InterfaceExpression expression, InterfaceStatement thenStatement, InterfaceStatement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceValue value;
        value = expression.evaluate(state.getSymbolTable(),state.getHeap());
        if (value.getType().equals(new BoolType()))
        {
            BoolValue bool = (BoolValue) value;// from interfaceValue to BoolValue
            if (bool.getValue() == true)
            {
                thenStatement.execute(state);
            }
            else
            {
                elseStatement.execute(state);
            }
        }
        else
        {
            throw new MyInterpreterException("it is not boolean");
        }
        return null;
    }

    public String toString(){
        return  "(IF("+ expression.toString()+") THEN(" +thenStatement.toString() +")ELSE("+elseStatement.toString()+"))";
    }
}


