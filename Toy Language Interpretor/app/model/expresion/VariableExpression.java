package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.value.InterfaceValue;
// reads the value from a variable
public class VariableExpression implements  InterfaceExpression {
    String key;
    public VariableExpression(String key){
        this.key = key;
    }

    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException {
        return variableDictionary.getValueDictionary(key);
    }

    public String toString(){
        return "Key: "+this.key;
    }
}
