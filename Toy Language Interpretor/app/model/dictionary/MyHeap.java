package app.model.dictionary;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<keyDataType,valueDataType>implements InterfaceMyHeap<keyDataType,valueDataType>{

    HashMap <keyDataType,valueDataType> heap;
    int address = 1;
    public MyHeap()
    {
        heap = new HashMap<keyDataType, valueDataType>();

    }
    public MyHeap(int address)
    {
        heap = new HashMap<keyDataType, valueDataType>();
        this.address = address;
    }


    public valueDataType getValueDictionary(keyDataType key) {
        return heap.get(key);
    }

    public void setValueDictionary(keyDataType key, valueDataType value) {
        heap.put(key,value);
    }

    public boolean keyExists(keyDataType key) {
        if(heap.containsKey(key))
            return true;
        return false;
    }
    public void removeKey(keyDataType key)
    {
        heap.remove(key);
    }

    public int giveAddress()
    {
        this.address++;
        return this.address-1;
    }

    public HashMap<keyDataType, valueDataType> getContent() {
        return this.heap;
    }

    public void setContent(HashMap<keyDataType, valueDataType> heap) {
        this.heap = heap;
    }

    public String toString(){
        String str="";
        for(Map.Entry<keyDataType,valueDataType> item:heap.entrySet())// in item we will have all pairs of key and value
        {
            str += item.toString()+"\n";
        }
        return str;
    }

}

