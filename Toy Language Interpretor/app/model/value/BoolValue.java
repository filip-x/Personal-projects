package app.model.value;

import app.model.type.BoolType;
import app.model.type.InterfaceType;

public class BoolValue implements InterfaceValue {
    boolean value;
    public BoolValue(boolean value){
        this.value = value;
    }

    public boolean getValue(){
        return this.value;
    }
    public InterfaceType getType() {
        return (InterfaceType) new BoolType();
    }
    public String toString() {
        return Boolean.toString(this.value);
    }

}
