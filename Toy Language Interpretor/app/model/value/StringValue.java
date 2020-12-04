package app.model.value;

import app.model.type.InterfaceType;
import app.model.type.StringType;

public class StringValue implements  InterfaceValue{
    String value;

    public StringValue(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public InterfaceType getType() {
        return (InterfaceType) new StringType();
    }
    public String toString(){
        return this.value;
    }

}
