package sample;

/**
 * Created by DTG2 on 09/01/16.
 */
public class ToDoItem {
    String text;
    boolean done;



    public ToDoItem(String todoText, boolean done) {
        this.text = todoText;
        this.done = done;
    }

    public ToDoItem() {
    }

    public ToDoItem(String text) {
        this.text = text;
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTodoText() {

        return text;
    }

    public void setTodoText(String todoText) {
        this.text = todoText;
    }

    public String toString() {
        if (isDone()) {
            return text + " (done)";
        } else {
            return text;
        }
    }
}

