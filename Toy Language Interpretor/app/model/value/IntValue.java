package app.model.value;

import app.model.type.IntType;
import app.model.type.InterfaceType;

public class IntValue implements InterfaceValue {
    int value;
    public IntValue(int value){
        this.value =value;
    }

    public int getValue(){
        return this.value;
    }
    public InterfaceType getType() {
        return (InterfaceType) new IntType();
    }
    public String toString() {
        return Integer.toString(this.value);
    }
}
