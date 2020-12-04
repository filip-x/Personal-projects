package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.stack.InterfaceMyStack;
import app.model.type.InterfaceType;
import app.model.value.InterfaceValue;
// id is the name of the var
// expression is the value of the var
// we assign for the var id the evaluated value of the expression -eval upon expressions
public class AssignmentStatement  implements InterfaceStatement {
    String id;
    InterfaceExpression expression;
    public AssignmentStatement(String id,InterfaceExpression expression){
        this.id = id;
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        InterfaceMyDictionary<String,InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceMyHeap<Integer,InterfaceValue> heaptable = state.getHeap();
        if(symbolTable.keyExists(this.id)){
            InterfaceValue value =this.expression.evaluate(symbolTable,heaptable);
            InterfaceType typeId = (symbolTable.getValueDictionary(this.id).getType());
            if(value.getType().equals(typeId))
            {
                symbolTable.setValueDictionary(this.id,value);
            }
            else
                throw new MyInterpreterException("declared type of variable "+id+" and type of the assigned expression do not match ");
        }else
            throw new MyInterpreterException("the used variable " +id + " was not declared before");
        return null;
    }

    public String toString()
    {
        return this.id+"="+expression.toString();
    }
}
