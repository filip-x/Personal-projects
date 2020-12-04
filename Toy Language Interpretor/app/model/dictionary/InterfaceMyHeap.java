package app.model.dictionary;

import java.util.HashMap;

public interface InterfaceMyHeap<keyDataType,valueDataType>{

    valueDataType getValueDictionary(keyDataType key);
    void setValueDictionary(keyDataType key,valueDataType value);
    boolean keyExists(keyDataType key);
    void removeKey(keyDataType key);
    int giveAddress();
    HashMap<keyDataType,valueDataType> getContent();
    void setContent(HashMap<keyDataType,valueDataType> heap);

}
