package guicontrollers.commandguicontrollers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import commands.Add;
import commands.RemoveById;
import data.*;
import guicontrollers.SessionController;
import guicontrollers.abstractions.LanguageChanger;
import interfaces.AssemblableCommand;
import interfaces.Command;
import interfaces.CommandWithArg;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import managers.InputManager;
import util.UserSessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static guicontrollers.SessionController.loadErrPortChoosingField;

public class RemoveByIdSceneController extends LanguageChanger implements Initializable {
    @FXML
    private Text errorMsg;

    @FXML
    private TextField idField;

    @FXML
    private Text label;

    @FXML
    private Button sendCommandBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMsg.setVisible(false);
        changeLanguage();

        sendCommandBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    sendCommand();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        idField.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.ENTER)) {
                try {
                    sendCommand();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    public void sendCommand() throws IOException {
        setDefaultDesign();
        CommandWithArg command = new RemoveById(null);

        try {
            String id = InputManager.readId(idField.getText());
            command.setArg(id);
        }
        catch (NumberFormatException e){
            errorMsg.setText(UserSessionManager.getCurrentBundle().getString("Wrong id value! (Id should " +
                    "be > 0 and contain " +
                    "only digits)"));
            errorMsg.setVisible(true);
            idField.setStyle("-fx-background-color: #ffe6e6");
            return;
        }

        Stage currentStage = (Stage) idField.getScene().getWindow();
        currentStage.close();
        Stage resStage = SessionController.openResWindow();

        Task<String> commandTask = new Task<>() {
            @Override
            protected String call() throws IOException {
                String res = UserSessionManager.getCommandManager().processUserCommand(command);
                return UserSessionManager.getCurrentBundle().getString(res);
            }
        };

        commandTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Parent root = resStage.getScene().getRoot();
                TextArea response = (TextArea) root.lookup("#textArea");
                VBox vBox = (VBox) root.lookup("#vBox");
                vBox.setVisible(true);
                vBox.setDisable(false);
                response.setText(commandTask.getValue());
                response.setEditable(false);
            }
        });

        new Thread(commandTask).start();
    }

    private void setDefaultDesign(){
        idField.setStyle("-fx-background-color: #e5e5e5");
    }

    @Override
    protected void changeLanguage() {
        sendCommandBtn.setText(UserSessionManager.getCurrentBundle().getString("Remove"));
        label.setText(UserSessionManager.getCurrentBundle().getString(label.getText()));
        idField.setPromptText(UserSessionManager.getCurrentBundle().getString("ID"));
    }
}