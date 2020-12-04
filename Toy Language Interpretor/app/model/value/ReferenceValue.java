package app.model.value;

import app.model.type.InterfaceType;
import app.model.type.ReferenceType;

public class ReferenceValue implements InterfaceValue {

    int address;
    InterfaceType locationType;

    public ReferenceValue(int address,InterfaceType locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return this.address;
    }
    public InterfaceType getLocationType()
    {
        return this.locationType;
    }


    public InterfaceType getType() {
        return new ReferenceType(locationType);
    }
}
