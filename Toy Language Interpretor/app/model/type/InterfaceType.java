package app.model.type;

import app.model.value.InterfaceValue;

public interface InterfaceType {
    boolean equals(Object element);
    InterfaceValue defaultValue();

}
