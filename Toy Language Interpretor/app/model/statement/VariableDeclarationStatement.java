package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyDictionary;
import app.model.programstate.ProgramState;
import app.model.type.*;
import app.model.value.BoolValue;
import app.model.value.IntValue;
import app.model.value.InterfaceValue;
import app.model.value.ReferenceValue;

public class VariableDeclarationStatement implements InterfaceStatement {

    InterfaceType type;
    String name;
    public VariableDeclarationStatement(String name,InterfaceType type){
        this.name = name;
        this.type = type;
    }
    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceMyDictionary<String, InterfaceValue>  symbolTable;
        symbolTable = state.getSymbolTable();
        if(type instanceof BoolType)
            symbolTable.setValueDictionary(name,new BoolType().defaultValue());
        else
            if(type instanceof IntType)
                symbolTable.setValueDictionary(name,new IntType().defaultValue());
        else
            if(type instanceof StringType)
                symbolTable.setValueDictionary(name,new StringType().defaultValue());
        else
            if(type instanceof ReferenceType)
                symbolTable.setValueDictionary(name,this.type.defaultValue());
        return null;
    }
    public String toString(){
      return "Name: "+this.name+" Type: "+this.type.toString();
    }
}
