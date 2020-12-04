package app.model.expresion;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.dictionary.InterfaceMyHeap;
import app.model.type.IntType;
import app.model.value.IntValue;
import app.model.value.InterfaceValue;

// computes the value of an expression
public class ArithmeticExpression implements InterfaceExpression {

    InterfaceExpression expression1;
    InterfaceExpression expression2;
    int operand;//1-plus,2-minus,3-star,4-divide

    public ArithmeticExpression(InterfaceExpression expression1,InterfaceExpression expression2,int operand){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operand = operand;
    }
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> variableDictionary, InterfaceMyHeap<Integer,InterfaceValue> variableHeap) throws MyInterpreterException {// exception
        InterfaceValue value1,value2;
        value1=expression1.evaluate(variableDictionary,variableHeap);//we make sure that we can convert into int
        if(value1.getType().equals(new IntType()))
        {
            value2 = expression2.evaluate(variableDictionary,variableHeap);// we make sure that we can convert into int
            if(value2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)value1;
                IntValue i2 = (IntValue)value2;
                int number1,number2;
                number1 = i1.getValue();
                number2 = i2.getValue();
                if(operand == 1)
                    return new IntValue(number1+number2);
                if(operand == 2)
                    return new IntValue(number1-number2);
                if(operand == 3)
                    return new IntValue(number1*number2);
                if(operand == 4)
                    if(number2 == 0)
                        throw new MyInterpreterException("division by zero");
                    else
                        return new IntValue(number1/number2);
            }else
                throw new MyInterpreterException("second operand is not a integer");
        }else
          throw new MyInterpreterException("first operand is not an integer");

        return null;
    }
    public String toString() {


        switch (this.operand) {
            case 1:
                return this.expression1 + " + " + this.expression2;
            case 2:
                return this.expression1 + " - " + this.expression2;
            case 3:
                return this.expression1 + " * " + this.expression2;
            case 4:
                return this.expression1 + " / " + this.expression2;
        }
        return "";
    }
}
