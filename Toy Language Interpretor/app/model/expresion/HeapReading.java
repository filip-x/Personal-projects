package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.programstate.ProgramState;
import app.model.type.ReferenceType;
import app.model.value.InterfaceValue;
import app.model.value.ReferenceValue;

public class HeapReading implements InterfaceExpression {

    InterfaceExpression expression1;
    public HeapReading(InterfaceExpression expression1)
    {
        this.expression1 = expression1;
    }
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException {
        InterfaceValue value = this.expression1.evaluate(variableDictionary,variableHeap);
        if(value instanceof ReferenceValue)
        {
            ReferenceValue refValue = (ReferenceValue) value;
            if(variableHeap.keyExists(refValue.getAddress()))
            {
                return variableHeap.getValueDictionary(refValue.getAddress());
            }
            else
            {
                throw new MyInterpreterException("Key does not match the address");
            }
        }
        else
        {
            throw  new MyInterpreterException("the expression"+this.expression1+" is not the requested type!!!");

        }
    }
    public String toString()
    {
        return "*"+this.expression1;
    }
}
