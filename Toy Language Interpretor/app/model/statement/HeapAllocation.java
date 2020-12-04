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

public class HeapAllocation implements InterfaceStatement {

    String varName;
    InterfaceExpression expression1;

    public HeapAllocation(String var_name,InterfaceExpression expression1)
    {
        this.varName = var_name;
        this.expression1 = expression1;
    }

    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceMyHeap<Integer,InterfaceValue> heaptable = state.getHeap();

        if(symbolTable.keyExists(this.varName)){
            InterfaceType varNameType = (symbolTable.getValueDictionary(this.varName).getType());
            if(varNameType instanceof ReferenceType)// here we check the type of varName
            {
                InterfaceValue valueExpression1= this.expression1.evaluate(symbolTable,heaptable);
                if(valueExpression1.getType().equals(((ReferenceType)varNameType).getInnerType()))
                {
                    InterfaceMyHeap<Integer,InterfaceValue> heap = state.getHeap();
                    int addressFromHeap=heap.giveAddress();
                   heap.setValueDictionary(addressFromHeap,valueExpression1);
                  // ReferenceValue referenceValueExpression = (ReferenceValue) valueExpression1;
                   symbolTable.setValueDictionary(varName,new ReferenceValue(addressFromHeap,valueExpression1.getType()));
                }
                else
                {
                    throw  new MyInterpreterException("type of:"+this.expression1+" is not of the right type");
                }
            }
            else
                throw new MyInterpreterException("declared type of variable "+varName+"is not the same as it should be");
        }else
            throw new MyInterpreterException("the used variable " +varName + " was not declared before");
        return null;
    }
    public String toString()
    {
        return varName+" =new "+expression1;
    }
}
