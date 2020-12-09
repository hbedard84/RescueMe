package Controllers;

import Utilities.APIUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    @FXML
    public void backToList(ActionEvent event) throws IOException {

       // btn_back.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/Views/animalAdoptionView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Pet Details");
        //add custom css styling to scene
        scene.getStylesheets().add("/CSS/style.css");
        // change default icon to paw print image
        stage.getIcons().add(new Image("/Images/paw.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
