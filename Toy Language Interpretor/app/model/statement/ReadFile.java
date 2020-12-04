package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.type.IntType;
import app.model.type.StringType;
import app.model.value.IntValue;
import app.model.value.InterfaceValue;
import app.model.value.StringValue;

import java.io.BufferedReader;

public class ReadFile implements  InterfaceStatement {
    InterfaceExpression expression;
    String varName;


    public ReadFile(InterfaceExpression expression,String varName)
    {
        this.expression = expression;
        this.varName = varName;
    }

    public ProgramState execute(ProgramState state) throws MyInterpreterException {

        if (state.getSymbolTable().keyExists(varName))
        {
            if(state.getSymbolTable().getValueDictionary(varName).getType().equals(new IntType()))
            {
                InterfaceValue valueOfExpression =this.expression.evaluate(state.getSymbolTable(),state.getHeap());
                if( valueOfExpression.getType().equals(new StringType()))
                {
                    InterfaceMyDictionary fileTable = state.getFile();
                    StringValue stringValueOfExpression = (StringValue) valueOfExpression;
                    if(fileTable.keyExists(stringValueOfExpression.getValue()) == true)
                    {
                        BufferedReader file = (BufferedReader) fileTable.getValueDictionary(stringValueOfExpression.getValue());
                        try {
                            String valueFromFile = file.readLine();
                            int value  = Integer.parseInt(valueFromFile);

                            state.getSymbolTable().setValueDictionary(this.varName, new IntValue(value));
                        }
                        catch(Exception error)
                        {
                            throw new MyInterpreterException(error.getMessage());
                        }
                    }
                    else {
                        throw new MyInterpreterException("The file is not open");
                    }

                }
                else {
                    throw new MyInterpreterException("It' not string");
                }
            }
            else
            {
                throw new MyInterpreterException("It's not a int");
            }
        }
        else
        {
            throw new MyInterpreterException("The var does not exist");
        }
        return null;
    }
}
