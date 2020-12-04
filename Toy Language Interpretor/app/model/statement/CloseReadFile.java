package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.type.StringType;
import app.model.value.InterfaceValue;
import app.model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class CloseReadFile implements  InterfaceStatement {

    InterfaceExpression expression;

    public CloseReadFile(InterfaceExpression expression) {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        //pop will be done in controller
        InterfaceValue value;

        value = this.expression.evaluate(state.getSymbolTable(),state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue strValue = (StringValue) value;
            InterfaceMyDictionary<String, BufferedReader> filetable = state.getFile();
            if (filetable.keyExists(strValue.getValue()) == false) {
                throw new MyInterpreterException("Not open");
            }
            else
                {
                try {
                    BufferedReader openFile = filetable.getValueDictionary(strValue.getValue());
                    filetable.setValueDictionary(strValue.getValue(), openFile);
                    openFile.close();
                    filetable.removeKey(strValue.getValue());
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
