package Controllers;

import Models.Data;
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

    @FXML
    private ListView<Data> listview_hidden;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //make listview hidden, it's just to temporarily hold the listview from the previous scene so we can pass it back later
        listview_hidden.setVisible(false);
        listview_hidden.managedProperty().bind(listview_hidden.visibleProperty());

    }

    //THESE METHODS ARE USED FOR PASSING DATA BETWEEN SCENES

    /***
     * Sets the name label to the name value from the first scene, or unknown if that value is null
     * @param text - value of petSelected.getAttributes().getName()
     */
    public void setNameText(String text){
        if(text != null)
            lbl_name.setText(text);
        else lbl_name.setText("Unknown");
    }

    /***
     * Sets the breed label to the breed value from the first scene, or unknown if that value is null
     * @param text - value of petSelected.getAttributes().getBreed()
     */
    public void setBreedText(String text){
        if(text != null)
            lbl_breed.setText(text);
        else lbl_breed.setText("Unknown");
    }

    /***
     * Sets the age label to the age value from the first scene, or unknown if that value is null
     * @param text - value of petSelected.getAttributes().getAge()
     */
    public void setAgeText(String text){
        if(text != null)
            lbl_age.setText(text);
        else lbl_age.setText("Unknown");
    }

    /***
     * Sets the gender label to the gender value from the first scene, or unknown if that value is null
     * @param text - value of petSelected.getAttributes().getSex()
     */
    public void setGenderText(String text){
        if(text != null)
            lbl_gender.setText(text);
        else lbl_gender.setText("Unknown");
    }

    /***
     * Sets the size label to the size value from the first scene, or unknown if that value is null
     * @param text - value of petSelected.getAttributes().getSize()
     */
    public void setSizeText(String text){
        if(text != null)
            lbl_size.setText(text);
        else lbl_size.setText("Unknown");
    }

    /***
     * Sets the website textview to the url value from the first scene, or unknown if that value is null
     * @param text - value of petSelected.getAttributes().getUrl()
     */
    public void setURLText(String text){
        if(text != null)
            textView_URL.setText(text);
        else textView_URL.setText("Unknown");
    }

    /***
     * Sets the image using the image source value from the first scene, or sets a default image if that value is null
     * @param text - value of petSelected.getAttributes().getPictureThumbnailUrl()
     */
    public void setImage(String text){
        if(text != null)
            petImage2.setImage(new Image(text));
        else petImage2.setImage(new Image("/Images/paw.png"));
    }

     /***
     * Sets the items in the Pet Detail scene's hidden listview to those being passed from the search scene's listview.
     * This stores the data so it can be passed back when the first scene is reloaded.
     * @param listview - listview from the AnimalAdoptionView search scene
     */
    public void setListview(ListView<Data> listview){
        ObservableList<Data> list = listview.getItems();
        listview_hidden.setItems(list);
    }

    /***
     * This method is called when the Back To Pet List button is clicked.
     * It reloads the Search scene and passes the listview values back to the Search scene for display there.
     * @param event - 'Back to Pet List' Button Click
     * @throws IOException
     */
    @FXML
    public void backToList(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/animalAdoptionView.fxml"));
        Parent root = loader.load();

        //pass values from listview to the search view
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
    }
}
