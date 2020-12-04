package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.type.StringType;
import app.model.value.InterfaceValue;
import app.model.value.StringValue;

import java.io.*;

public class OpenReadFile implements InterfaceStatement {

    InterfaceExpression expression;

    public OpenReadFile(InterfaceExpression expression) {
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        //pop will be done in controller
        InterfaceValue value;
        value = this.expression.evaluate(state.getSymbolTable(),state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue strValue = (StringValue) value;
            InterfaceMyDictionary<String, BufferedReader> filetable = state.getFile();
            if (filetable.keyExists(strValue.getValue()) == true) {
                throw new MyInterpreterException("Key is allready in use");
            } else {
                try {
                    BufferedReader openFile = new BufferedReader(new FileReader(strValue.getValue()));
                    filetable.setValueDictionary(strValue.getValue(), openFile);
                } catch (Exception error) {
                    throw new MyInterpreterException(error.getMessage());
                }
            }
        } else {
            throw new MyInterpreterException("It's not a string ");
        }
        return null;
    }
}

