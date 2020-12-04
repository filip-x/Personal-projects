package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.stack.InterfaceMyStack;
import app.model.type.InterfaceType;
import app.model.type.ReferenceType;
import app.model.value.InterfaceValue;
import app.model.value.ReferenceValue;

import javax.swing.plaf.nimbus.State;

public class HeapWriting implements InterfaceStatement {

    String varName;// heap address
    InterfaceExpression expression1; // new value that is going to be stored into the heap

    public HeapWriting(String varName, InterfaceExpression expression1) {
        this.varName = varName;
        this.expression1 = expression1;
    }

    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        InterfaceMyHeap<Integer, InterfaceValue> heaptable = state.getHeap();
        if (symbolTable.keyExists(this.varName)) {
            InterfaceValue varNameValue = symbolTable.getValueDictionary(varName);
            if (varNameValue instanceof ReferenceValue) {
                ReferenceValue refVarName = (ReferenceValue) varNameValue;

                if (heaptable.keyExists(refVarName.getAddress())) {
                    InterfaceValue valueExpression = this.expression1.evaluate(symbolTable, heaptable);
                    if (valueExpression.getType().equals(refVarName.getLocationType())) {
                        heaptable.setValueDictionary(refVarName.getAddress(), valueExpression);
                    } else {
                        throw new MyInterpreterException("Expression not same type as varName");
                    }
                } else {
                    throw new MyInterpreterException("Not the good type" + this.varName);
                }
            } else {
                throw new MyInterpreterException("Not in  the dictionary " + this.varName);
            }
        }
        return null;
    }
    public String toString()
    {
        return "*"+varName+" = "+this.expression1;
    }

}
