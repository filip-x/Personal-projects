package app.model.type;


import app.model.value.IntValue;
import app.model.value.InterfaceValue;

public class IntType implements InterfaceType {




    public boolean equals(Object obj) {
        if(obj instanceof IntType)
            return true;
        return false;
    }

    public InterfaceValue defaultValue() {
        return new IntValue(0);
    }

    public String toString(){
        return "int";
    }
}
