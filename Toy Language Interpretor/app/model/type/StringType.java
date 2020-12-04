package app.model.type;

import app.model.value.InterfaceValue;
import app.model.value.StringValue;

public class StringType implements  InterfaceType{

    public boolean equals(Object obj){
        if(obj instanceof StringType)
            return true;
        return false;
    }
    public InterfaceValue defaultValue() {
        return new StringValue("");
    }

    public String toString(){
        return "string";
    }
}
