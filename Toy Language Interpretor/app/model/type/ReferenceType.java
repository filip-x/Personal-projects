package app.model.type;

import app.model.value.InterfaceValue;
import app.model.value.ReferenceValue;

public class ReferenceType  implements  InterfaceType{
    InterfaceType innerType;
    public ReferenceType(InterfaceType innerType)
    {
        this.innerType = innerType;
    }
    public InterfaceType getInnerType()
    {
        return this.innerType;
    }
    public boolean equals(Object another){
        if (another instanceof ReferenceType)
        {
            return innerType.equals(((ReferenceType) another).innerType);
        }
        else
        {
            return false;
        }
    }

    public String toString(){
        return "Ref("+innerType.toString()+")";
    }

    public InterfaceValue defaultValue() {
        return new ReferenceValue(0,innerType);
    }

}
