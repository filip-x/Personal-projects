package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.type.BoolType;
import app.model.type.IntType;
import app.model.value.BoolValue;
import app.model.value.IntValue;
import app.model.value.InterfaceValue;
// sees if it is a logical expression like && or ||
// gives a truth value if it is of  logical expression (wheter if the expression is tru or not)

public class LogicExpression implements InterfaceExpression {

    InterfaceExpression expression1;
    InterfaceExpression expression2;
    int operand;
    public LogicExpression(InterfaceExpression expression1,InterfaceExpression expression2,int operand){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operand = operand;
    }

    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException {
        InterfaceValue value1,value2;
        value1=expression1.evaluate(variableDictionary,variableHeap);//we make sure that we can convert into int
        if(value1.getType().equals(new BoolType()))
        {
            value2 = expression2.evaluate(variableDictionary,variableHeap);// we make sure that we can convert into int
            if(value2.getType().equals(new BoolType()))
            {
                BoolValue i1 = (BoolValue)value1;
                BoolValue i2 = (BoolValue)value2;
                boolean number1,number2;
                number1 = i1.getValue();
                number2 = i2.getValue();
                if(operand == 1)
                    return new BoolValue(number1 && number2);
                if(operand == 2)
                    return new BoolValue(number1 || number2);

            }else
                throw new MyInterpreterException("second operand is not boolean");
        }else
            throw new MyInterpreterException("first operand is not boolean");
        return null;
    }
    public String toString(){
        if (this.operand == 1)
            return this.expression1+ " && " +this.expression2;
        else
            return this.expression1+ " || "+ this.expression2;
    }
}
