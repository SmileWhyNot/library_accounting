package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField name;
    @FXML
    private TextField author;
    @FXML
    private TextField genre;
    @FXML
    private TextField year;
    @FXML
    private TextField pagesCount;
    @FXML
    private TableColumn<Book, String> colName;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, String> colGenre;
    @FXML
    private TableColumn<Book, Integer> colYear;
    @FXML
    private TableColumn<Book, Integer> colPagesCount;
    @FXML
    private TableView<Book> table;
    @FXML
    private TextField find;
    @FXML
    private Button back;
    @FXML
    private Button addLine;
    @FXML
    private Button delete;
    @FXML
    private Button search;
    private ObservableList<Book> list = FXCollections.observableArrayList();
    private ArrayList<Book> arrayList=new ArrayList<Book>();

    private int counter = 0;

    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        colYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        colPagesCount.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pagesCount"));
        table.setEditable(true);
        arrayList.add(new Book("Мастер и Маргарита", "Михаил Булгаков", "Роман", 1940, 571));
        arrayList.add(new Book("Собачье сердце", "Михаил Булгаков", "Повесть", 1925, 128));
        arrayList.add(new Book("Мертвые души", "Николай Гоголь", "Комедия", 1835, 448));
        arrayList.add(new Book("Евгений Онегин", "Александр Пушкин", "Роман", 1833, 220));
        arrayList.add(new Book("Ревизор", "Николай Гоголь", "Комедия", 1836, 128));
        list.addAll(arrayList);
        table.setItems(list);
    }
    @FXML //Метод удаления строки
    protected void delete(){
        if (!list.isEmpty())
        {
            int row = table.getSelectionModel().getSelectedIndex();
            if (row >= 0){
                arrayList.remove(row);
                list.remove(row);
                sort();
            }
            else {
                wrongInput("Удаление", "Не выбран ряд удаления");
            }
        }
        else {
            wrongInput("Удаление", "Список пуст");
        }
    }
    @FXML
    protected void addLine(){
        try {
            if (name.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*(\s)*[а-яА-Я]*$") && !name.getText().equals("")
                    && author.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*$") && !author.getText().equals("")
                    && genre.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*$") && !genre.getText().equals("")
                    && Integer.parseInt(year.getText()) >= 1800 && Integer.parseInt(year.getText()) <= 2022 && year.getText().matches("^[0-9]*$")
                    && Integer.parseInt(pagesCount.getText()) > 0 && pagesCount.getText().matches("^[0-9]*$")) {
                Book book = new Book(name.getText(), author.getText(), genre.getText(),
                        Integer.parseInt(year.getText()), Integer.parseInt(pagesCount.getText()));
                list.add(book);
                arrayList.add(book);
                table.setItems(list);
                sort();
                freeInput();
            }
            else {
                wrongInput("Некорректный ввод", "Введите корректные данные\n Текст на русском\n Число положительное\n Год издания 1800-2022");
            }
        }catch (IllegalStateException | NumberFormatException exc){
            wrongInput("Некорректный ввод", "Введите корректные данные\n Текст на русском\n Число положительное\n Год издания 1800-2022");
        }
    }
    protected void wrongInput(String header, String exc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(exc);
        freeInput();
        alert.showAndWait();
    }

    protected void wrongInputForEdit(String header, String exc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(exc);
        alert.showAndWait();
    }
    protected void freeInput(){
        name.setText("");
        author.setText("");
        genre.setText("");
        year.setText("");
        pagesCount.setText("");
    }
    @FXML //Метод сортировки по названию
    protected void sort(){
        if (!list.isEmpty()){
            table.getSortOrder().addAll(colName);
        }
        else {
            wrongInput("Сортировка", "Список пуст");
        }
    }
    @FXML
    protected void edit(){
        int row = table.getSelectionModel().getSelectedIndex();
        if(counter == 0)
        {
            if (row >= 0) {
                Book chosen = list.get(row);
                name.setText(chosen.getName());
                author.setText(chosen.getAuthor());
                genre.setText(chosen.getGenre());
                year.setText(Integer.toString(chosen.getYear()));
                pagesCount.setText(Integer.toString(chosen.getPagesCount()));
                addLine.setDisable(true);
                delete.setDisable(true);
                search.setDisable(true);
            }
            else {
                wrongInputForEdit("Редактирование", "Не выбран ряд");
                return;
            }
        }
        if (counter > 0)
        {
            if(row >= 0) {
                if (!list.isEmpty())
                {
                    try{
                        if (name.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*(\s)*[а-яА-Я]*$") && !name.getText().equals("")
                                && author.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*$") && !author.getText().equals("")
                                && genre.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*$") && !genre.getText().equals("")
                                && Integer.parseInt(year.getText()) >= 1800 && Integer.parseInt(year.getText()) <= 2022 && year.getText().matches("^[0-9]*$")
                                && Integer.parseInt(pagesCount.getText()) > 0 && pagesCount.getText().matches("^[0-9]*$")) {
                            Book book = new Book(name.getText(), author.getText(), genre.getText(),
                                    Integer.parseInt(year.getText()), Integer.parseInt(pagesCount.getText()));
                            arrayList.remove(row);
                            list.remove(row);
                            arrayList.add(row, book);
                            list.add(row, book);
                            sort();
                            counter = 0;
                            addLine.setDisable(false);
                            delete.setDisable(false);
                            search.setDisable(false);
                            freeInput();
                            return;
                        }
                        else {
                            wrongInputForEdit("Некорректный ввод", "Введите корректные данные\n Текст на русском\n Число положительное\n Год издания 1800-2022");
                        }
                    }catch (IllegalStateException | NumberFormatException exc){
                        wrongInputForEdit("Некорректный ввод", "Введите корректные данные\n Текст на русском\n Число положительное\n Год издания 1800-2022");
                    }
                }
                else {
                    wrongInputForEdit("Редактирование", "Список пуст");
                }
            }
            else{
                wrongInputForEdit("Редактирование", "Не выбран ряд редактирования");
            }
        }
        counter++;
    }
    @FXML //Метод поиска по автору
    protected void search(){
        if (find.getText().matches("^([а-яА-Я])+(\s)*[а-яА-Я]*$") && !find.getText().equals("")){
            table.setItems(list);
            ObservableList<Book> names=FXCollections.observableArrayList();
            if(!list.isEmpty()){
                for(int i=0;i<list.size();i++){
                    if (colAuthor.getCellData(i).equals(find.getText())) {
                        names.add(list.get(i));
                    }}}
            else {
                wrongInput("Поиск", "Список пуст");
                find.setText("");
            }
            if (!names.isEmpty()) {
                table.setItems(names);
                back.setDisable(false);
            }
            else {
                wrongInput("Поиск", "Такого автора не найдено");
                find.setText("");
            }
        }
        else {
            wrongInput("Поиск", "Некорректный ввод данных");
            find.setText("");
        }
    }
    @FXML //Метод осуществления возвращения к первоначальной таблице
    protected void back(){
        table.setItems(list);
        find.setText(null);
        back.setDisable(true);
    }
}