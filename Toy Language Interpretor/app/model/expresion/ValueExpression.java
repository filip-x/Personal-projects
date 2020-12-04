package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.value.InterfaceValue;
//converts a value into a expression ( it will assign the value to the expression)

public class ValueExpression implements  InterfaceExpression {

    InterfaceValue value;

    public ValueExpression(InterfaceValue value)
    {
        this.value = value;
    }


    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException {
        return this.value;
    }

    public String toString(){
        return "Value: "+ this.value;
    }

}
