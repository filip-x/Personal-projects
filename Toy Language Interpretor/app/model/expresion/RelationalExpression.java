package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.type.IntType;
import app.model.value.BoolValue;
import app.model.value.IntValue;
import app.model.value.InterfaceValue;
import com.sun.jdi.BooleanValue;
import jdk.jfr.Relational;

public class RelationalExpression  implements  InterfaceExpression{

    String operation;
    InterfaceExpression expression1;
    InterfaceExpression expression2;

    public RelationalExpression(InterfaceExpression exp1, InterfaceExpression exp2,String operation)
    {
        this.expression1 = exp1;
        this.expression2 = exp2;
        this.operation = operation;
    }

    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException {

        InterfaceValue value1, value2;
        value1 = expression1.evaluate(variableDictionary,variableHeap);//we make sure that we can convert into int
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(variableDictionary,variableHeap);// we make sure that we can convert into int
            if (value2.getType().equals(new IntType())) {

                IntValue i1 = (IntValue) value1;
                IntValue i2 = (IntValue) value2;
                int intValue1 = i1.getValue();
                int intValue2 = i2.getValue();
                return switch (operation) {
                    case "<" -> new BoolValue(intValue1 < intValue2);
                    case "<=" -> new BoolValue(intValue1 <= intValue2);
                    case "==" -> new BoolValue(intValue1 == intValue2);
                    case "!=" -> new BoolValue(intValue1 != intValue2);
                    case ">" -> new BoolValue(intValue1 > intValue2);
                    case ">=" -> new BoolValue(intValue1 >= intValue2);
                    default -> throw new MyInterpreterException("Invalid operation");
                };
            } else {throw new MyInterpreterException("expression 2 not of int type"); }
        }else{throw  new MyInterpreterException("expression 1 not of integer type"); }

        }

        public String toString(){
            return expression1.toString()+ " "+ this.operation+ " "+this.expression2.toString();
        }
    }



