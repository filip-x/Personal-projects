package app.model.type;


import app.model.value.BoolValue;
import app.model.value.InterfaceValue;

public class BoolType implements InterfaceType {


    public boolean equals(Object obj) {
        if(obj instanceof BoolType)
            return true;
        return false;
    }

    public InterfaceValue defaultValue() {
        return  new BoolValue(false);
    }

    public String toString(){
        return "bool";
    }
}
