package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    ListView todoList;

    @FXML
    TextField todoText;

    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList();
    String fileName = "todos.json";

    Users myUsers = new Users();
    ToDoContainer container;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("What is your name?");

        myUsers.setUserName(userInput.nextLine());
        System.out.println(myUsers.getUserName() + " username set");

        System.out.println("Name Entered");

        if (myUsers.getUserName() != null) {
            fileName = myUsers.getUserName() + ".json";
        }

        System.out.println(myUsers.getUserName());
        System.out.println("If statement reached");

        retrieveList();

        System.out.println("For loop ran through");

        todoList.setItems(todoItems);
    }

    public void addItem() {
        System.out.println("Adding item ...");
        todoItems.add(new ToDoItem(todoText.getText()));/*todoItems object of type observable list*/
        todoText.setText("");
        System.out.println(todoText.getText());
        writeToFile();
    }

    public void removeItem() {
        ToDoItem todoItem = (ToDoItem)todoList.getSelectionModel().getSelectedItem();/*returns the actual object of the item, not just the text*/
        System.out.println("Removing " + todoItem.text + " ...");
        todoItems.remove(todoItem);/*"remove()" works on any list object in Java*/
        writeToFile();
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.done = !todoItem.done;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
        writeToFile();
    }

    public void addItemOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Enter Key pressed");
            addItem();
        }
    }

    public void toggleAll() {
        for (ToDoItem item : todoItems) {
            item.done = !item.done;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
        writeToFile();
    }

    public void markAllAsDone() {
        for (ToDoItem item : todoItems) {
            item.done = true;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
        writeToFile();
    }

    public void markAllAsNotDone() {
        for (ToDoItem item : todoItems) {
            item.done = false;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
        writeToFile();
    }

    public String jsonSerialize(ToDoContainer myContainer) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(myContainer);

        return jsonString;
    }

    public void writeToFile() {
        FileWriter writeToFile = null;
        try {
            File userFile = new File(myUsers.getUserName() + ".json");
            writeToFile = new FileWriter(userFile);

            container = new ToDoContainer();
            for (ToDoItem thisToDoItem : todoItems) {
                container.addToContainer(thisToDoItem);
            }
            writeToFile.write(jsonSerialize(container));
            writeToFile.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public ToDoContainer retrieveList() {
        try {
            System.out.println("Start to return");
            File myFile = new File(myUsers.getUserName() + ".json");
            Scanner fileScanner = new Scanner(myFile);
            String fileContents = fileScanner.next();
            JsonParser toDoItemParser = new JsonParser();
            System.out.println("still returning");
            if (fileContents == null) {
                container = new ToDoContainer();
            } else {
                container = toDoItemParser.parse(fileContents, ToDoContainer.class);
                System.out.println("in the midst of retreiving");

                for (ToDoItem item : container.ToDoContainer) {
                   boolean thisItem = item.done;
                   String stringItem = item.text;
                    todoItems.add(new ToDoItem(stringItem, thisItem));
                }
            }
            System.out.println("should have returned");
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("didn't return");
            return null;
        }
        return container;
    }
}
