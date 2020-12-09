package Controllers;

import Models.Data;
import Utilities.APIUtility;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PetDetailViewController implements Initializable {

    @FXML
    private TextArea textView_URL;

    @FXML
    private Button btn_back;

    @FXML
    private ImageView img_cat;

    @FXML
    private Label lbl_title2;

    @FXML
    private ImageView img_dog;

    @FXML
    private ImageView petImage2;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_breed;

    @FXML
    private Label lbl_age;

    @FXML
    private Label lbl_size;

    @FXML
    private Label lbl_url;

    @FXML
    private Label lbl_gender;

    @FXML
    private GridPane gridpane;

    @FXML
    private ListView<Data> listview_hidden;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //make listview hidden, it's just to hold the listview from the previous scene so we can pass it back later
        listview_hidden.setVisible(false);
        listview_hidden.managedProperty().bind(listview_hidden.visibleProperty());

    }

    public void setNameText(String text){
        if(text != null)
            lbl_name.setText(text);
        else lbl_name.setText("Unknown");
    }
    public void setBreedText(String text){
        if(text != null)
            lbl_breed.setText(text);
        else lbl_breed.setText("Unknown");
    }
    public void setAgeText(String text){
        if(text != null)
            lbl_age.setText(text);
        else lbl_age.setText("Unknown");
    }
    public void setGenderText(String text){
        if(text != null)
            lbl_gender.setText(text);
        else lbl_gender.setText("Unknown");
    }
    public void setSizeText(String text){
        if(text != null)
            lbl_size.setText(text);
        else lbl_size.setText("Unknown");
    }
    public void setURLText(String text){
        if(text != null)
            textView_URL.setText(text);
        else textView_URL.setText("Unknown");
    }
    public void setImage(String text){
        if(text != null)
            petImage2.setImage(new Image(text));
        else petImage2.setImage(new Image("/Images/paw.png"));
    }
    public void setListview(ListView<Data> listview){
        ObservableList<Data> list = listview.getItems();
        listview_hidden.setItems(list);
    }

    @FXML
    public void backToList(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/animalAdoptionView.fxml"));
        Parent root = loader.load();

        //pass values from selected pet to the detail view  (this knowledge was gained from source:https://jagar.me/post/passingdatainjavafx/)
        AnimalAdoptionViewController animalAdoptionViewController = loader.getController();

        animalAdoptionViewController.setListview(listview_hidden);

        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();

        stage.setTitle("Rescue Me - Animal Adoption Finder");
        //add custom css styling to scene
        scene.getStylesheets().add("/CSS/style.css");
        // change default icon to paw print image
        stage.getIcons().add(new Image("/Images/paw.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();






       // btn_back.getScene().getWindow().hide();

//        Parent root = FXMLLoader.load(getClass().getResource("/Views/animalAdoptionView.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
//        stage.setTitle("Pet Details");
//        //add custom css styling to scene
//        scene.getStylesheets().add("/CSS/style.css");
//        // change default icon to paw print image
//        stage.getIcons().add(new Image("/Images/paw.png"));
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
    }
}
