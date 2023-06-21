package guicontrollers.commandguicontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpSceneController implements Initializable {

    @FXML
    private Label reference = new Label();

    @FXML
    private Text title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StringBuilder s = new StringBuilder("");
        s.append(
                "\n\n\"help\" - вывести справку по доступным командам\n" +
                        "\"info\" - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                        "количество элементов и т.д.)\n" +
                        "\"show\" - вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "\"add\" - добавить новый элемент в коллекцию\n" +
                        "\"update id\" - обновить значение элемента коллекции, id которого равен заданному\n" +
                        "\"remove_by_id id\" - удалить элемент из коллекции по его id\n" +
                        "\"clear\" - очистить коллекцию\n" +
                        "\"execute_script file_name\" - считать и исполнить скрипт из указанного файла. " +
                        "В скрипте содержатся команды в таком же виде, " +
                        "в котором их вводит пользователь в интерактивном режиме.\n" +
                        "\"head\" - вывести первый элемент коллекции\n" +
                        "\"add_if_min\" - добавить новый элемент в коллекцию, если его значение меньше, " +
                        "чем у наименьшего элемента этой коллекции\n" +
                        "\"remove_greater\" - удалить из коллекции все элементы, превышающие заданный\n" +
                        "\"remove_all_by_nationality nationality\" - удалить из коллекции все элементы, значение поля " +
                        "nationality которого эквивалентно заданному\n" +
                        "\"filter_by_nationality nationality\" - вывести элементы, значение поля nationality которых " +
                        "равно заданному\n" +
                        "\"print_field_descending_height\" - вывести значения поля " +
                        "height всех элементов в порядке убывания\n");
        reference.setText(s.toString());
    }
}