package app.model.list;

import java.util.LinkedList;

public class MyList<dataType> implements InterfaceMyList<dataType> {

    LinkedList <dataType >linkedList;

    public MyList(){
        linkedList = new LinkedList<dataType>();
    }

    public dataType getElementFromPosition(int position) {
        return linkedList.get(position);
    }

    public int size() {
        return linkedList.size();
    }

    public void addElement(dataType elementToAdd) {
        linkedList.add(elementToAdd);
    }

    public void removeElement(dataType elementToRemove) {
        linkedList.remove(elementToRemove);

    }

    public void updateElement(dataType elementToUpdate, dataType update) {
        int indexToUpdate=linkedList.indexOf(elementToUpdate);
        linkedList.set(indexToUpdate,update);
    }
    public String toString(){
        String str="";
        for(dataType item: linkedList)
        {
            str += item.toString()+", ";
        }
        return str;
    }
}
