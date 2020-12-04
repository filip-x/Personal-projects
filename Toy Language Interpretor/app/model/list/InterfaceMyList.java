package app.model.list;

public interface InterfaceMyList<dataType> {
    dataType getElementFromPosition(int position);
    int size();
    void addElement(dataType elementToAdd);
    void removeElement(dataType elementToRemove);
    void updateElement(dataType elementToUpdate,dataType update);

}
