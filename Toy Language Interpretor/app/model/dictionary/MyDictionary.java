package app.model.dictionary;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<keyDataType,valueDataType>implements InterfaceMyDictionary<keyDataType,valueDataType> {

    HashMap <keyDataType,valueDataType> dictionary;
    public MyDictionary()
    {
        dictionary = new HashMap<keyDataType, valueDataType>();
    }


    public valueDataType getValueDictionary(keyDataType key) {
        return dictionary.get(key);
    }

    public void setValueDictionary(keyDataType key, valueDataType value) {
        dictionary.put(key,value);
    }

    public boolean keyExists(keyDataType key) {
        if(dictionary.containsKey(key))
            return true;
        return false;
    }
    public void removeKey(keyDataType key)
    {
       dictionary.remove(key);
    }

    public HashMap<keyDataType, valueDataType> getContent() {
        return dictionary;
    }

    public void setContent(HashMap<keyDataType, valueDataType> dict) {
        this.dictionary =dict;
    }

    public InterfaceMyDictionary<keyDataType, valueDataType> clone() {
        MyDictionary<keyDataType,valueDataType> temporaryDict = new MyDictionary<>();
        for(Map.Entry<keyDataType,valueDataType> item:dictionary.entrySet())// in item we will have all pairs of key and value
        {
             temporaryDict.setValueDictionary(item.getKey(),item.getValue());
        }
        return temporaryDict;



    }

    public String toString(){
        String str="";
        for(Map.Entry<keyDataType,valueDataType> item:dictionary.entrySet())// in item we will have all pairs of key and value
        {
            str += item.toString()+"\n";
        }
        return str;

    }


}
