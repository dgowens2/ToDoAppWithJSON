package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public String jsonSerialize(ToDoItem todoToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(todoToSave);

        return jsonString;
    }

    public void saveJsonFile(String jsonString) {
        try {
            File jsonFile = new File("Donald.txt");
            FileWriter jsonWriter = new FileWriter(jsonFile);
            jsonWriter.write(jsonString);
            jsonWriter.close();
        } catch (Exception exception) {
            System.out.println("Exception while writing to file ...");
        }
    }

    public ToDoItem readJsonFile(ToDoItem todoItem) {
        try {
            File jsonFile = new File("Donald.txt");
            Scanner fileScanner = new Scanner(jsonFile);
            todoItem = null;
            while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            System.out.println(currentLine);
            todoItem = jsonDeserialize(currentLine);
            }
            return todoItem;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("File read");
        return null;
    }

    public ToDoItem jsonDeserialize(String jsonTD) {
        JsonParser ToDoItemParser = new JsonParser();
        ToDoItem item = ToDoItemParser.parse(jsonTD, ToDoItem.class);

        return item;
    }

    public void checkUserFile(String userName, File jsonFile, ToDoItem todoSave) {
        Users myUsers = new Users();

        try {
//            File userFile = new File("UserName.txt");

            File userFile = jsonFile;
            Scanner fileScanner = new Scanner(userFile);
//
//            String scanString = fileScanner.nextLine();
//
//            String[] parts = scanString.split(",");
            boolean exists = false;
            int arrayIndex = 0;

            for (String currentPart : myUsers.users) {
                if (userName.equals(currentPart)) {
                    System.out.println("Welcome back, " + userName);
                    myUsers.setUsers(myUsers.users);
                    exists = true;
                }
                arrayIndex++;
            }
            if (exists == false) {
                jsonSerialize(todoSave);
                System.out.println("Welcome to new user, " + userName);
                myUsers.setUserName(userName);
                myUsers.setUsers(myUsers.users);

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        ToDoItem testToDo = new ToDoItem("Test ToDo", true);
//
//        Main myMain = new Main();
//
//        Users myUsers = new Users();
//
//        Scanner userInput = new Scanner(System.in);
//
//        System.out.println("What is your name?");
//
//        myUsers.setUserName(userInput.nextLine());
//
//        File jsonFile = new File(myUsers.getUserName() + ".txt");
//
//        myMain.checkUserFile(myUsers.getUserName(), jsonFile, testToDo);
//
//        System.out.println("ToDo Before JSON serialization");
//        String savedToDoAsJSON = myMain.jsonSerialize(testToDo);
//
//        System.out.println("ToDo as JSON = " + savedToDoAsJSON);
//
//        myMain.saveJsonFile(savedToDoAsJSON);
//
//        ToDoItem myTodo = myMain.readJsonFile(testToDo);
////        String n = myTodo.getTodoText();
////        System.out.println(n);

        launch(args);
    }
}