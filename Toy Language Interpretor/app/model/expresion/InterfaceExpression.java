package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.value.InterfaceValue;


public interface InterfaceExpression   {

    InterfaceValue evaluate(InterfaceMyDictionary<String,InterfaceValue>variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException;// throw exception
}
