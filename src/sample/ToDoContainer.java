package sample;

import java.util.ArrayList;

/**
 * Created by DTG2 on 09/13/16.
 */
public class ToDoContainer {

    public ArrayList<ToDoItem> ToDoContainer = new ArrayList<>();

    public ToDoContainer() {
    }

    public ArrayList<ToDoItem> getToDoContainer() {
        return ToDoContainer;
    }

    public void addToContainer(ToDoItem item) {
        ToDoContainer.add(item);
    }

    public void setToDoContainer(ArrayList<ToDoItem> toDoContainer) {
        ToDoContainer = toDoContainer;
    }
}
