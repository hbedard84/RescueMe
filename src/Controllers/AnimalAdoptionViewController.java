package Controllers;

import Models.*;
import Utilities.APIUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class AnimalAdoptionViewController implements Initializable {

    @FXML
    private ImageView img_cat;

    @FXML
    private Label lbl_title;

    @FXML
    private ImageView img_dog;

    @FXML
    private VBox vBox_searchFields;

    @FXML
    private Label lbl_species;

    @FXML
    private RadioButton radio_cat;

    @FXML
    private RadioButton radio_dog;

    @FXML
    private Label lbl_postCode;

    @FXML
    private Label lbl_choiceBox;

    @FXML
    private ChoiceBox<String> choiceBox_km;

    @FXML
    private Label lbl_gender;

    @FXML
    private CheckBox chkbox_male;

    @FXML
    private CheckBox chkbox_female;

    @FXML
    private Button btn_search;

    @FXML
    private ListView<Data> listView_results;

    @FXML
    private TextField txt_postCode;

    @FXML
    private Label lbl_warning;

    @FXML
    private Label lbl_rowsReturned;

    @FXML
    private ImageView petImage;

    @FXML
    private Button btn_details;

    @FXML
    private HBox hiddenResults;

    @FXML
    private TextField hiddenName;

    @FXML
    private TextField hiddenURL;

    @FXML
    private TextField hiddenGender;

    @FXML
    private TextField hiddenBreed;

    @FXML
    private TextField hiddenAge;

    @FXML
    private TextField hiddenSize;

    @FXML
    private TextField hiddenImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Configure Results box as Hidden
        hiddenResults.setVisible(false);
        hiddenResults.managedProperty().bind(hiddenResults.visibleProperty());

        //Configure PostCode TextBox
        txt_postCode.setPromptText("eg. L0M1B1");

        //Configure Distance choicebox
        choiceBox_km.getItems().addAll("10","25","50","100","150","Any distance");
        choiceBox_km.setValue("Any distance");  //sets the default value

        //Configure Species Radio Buttons
        ToggleGroup speciesRadioGroup = new ToggleGroup();
        radio_cat.setToggleGroup(speciesRadioGroup);
        radio_dog.setToggleGroup(speciesRadioGroup);

        //Configure Warning Label
        lbl_warning.setText("");
        lbl_warning.setVisible(false);
        lbl_warning.managedProperty().bind(lbl_warning.visibleProperty());

        //Configure the rows returned label
        lbl_rowsReturned.setText("");
        lbl_rowsReturned.setVisible(false);
        lbl_rowsReturned.managedProperty().bind(lbl_rowsReturned.visibleProperty());

        //Configure the details button
        btn_details.setVisible(false);
        btn_details.managedProperty().bind(btn_details.visibleProperty());

        //Configure the Pet Image
        petImage.setVisible(false);
        petImage.managedProperty().bind(petImage.visibleProperty());

        //Configure event listener for click on a row in the listview
        listView_results.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, petSelected) -> {
                    petImage.setImage(new Image(petSelected.getAttributes().getPictureThumbnailUrl()));
                    petImage.setVisible(true);
                    btn_details.setVisible(true);

                    //store the details of selected pet to pass to detail scene
                    hiddenName.setText(petSelected.getAttributes().getName());
                    hiddenAge.setText(petSelected.getAttributes().getAgeGroup());
                    hiddenGender.setText(petSelected.getAttributes().getSex());
                    hiddenImage.setText(petSelected.getAttributes().getPictureThumbnailUrl());
                    hiddenSize.setText(petSelected.getAttributes().getSizeGroup());
                    hiddenBreed.setText(petSelected.getAttributes().getBreedString());
                    hiddenURL.setText(petSelected.getAttributes().getUrl());
                }
        );
    }

    @FXML
    private void searchPets() {
        validateSearchFields();
        String species = null;
        //if search fields are valid, use those inputs to search API for matching pets and list in listview
        if (lbl_warning.getText().equals("")) {

            //get value of species
            if (radio_dog.isSelected()) {
                species = "dogs";
            } else {
                species = "cats";
            }

            //get value of postal code
            String postalCode = txt_postCode.getText();

            //get value of distance
            String distance = choiceBox_km.getValue();
            if (distance.equals("Any distance")){
                distance = "100000";
            }

            //get value of gender
            String gender = null;
            if (chkbox_male.isSelected() && !chkbox_female.isSelected())
                gender = "Male";
            else if (chkbox_male.isSelected() && chkbox_female.isSelected())
                gender = "Both";
            else if (!chkbox_male.isSelected() && chkbox_female.isSelected())
                gender = "Female";

            //clear the previous results
            listView_results.getItems().clear();
            petImage.setImage(new Image("./Images/paw.png"));

            //connect to API to get results

            try {
                APIResponse apiResponse = APIUtility.callResponseAPI(species, postalCode, gender);
                List<Data> pets = Arrays.asList(apiResponse.getData());
                MetaData meta = apiResponse.getMeta();

                listView_results.getItems().addAll(pets);

                lbl_rowsReturned.setText(meta.toString()+" - Click on a row to view pet picture!");
                lbl_rowsReturned.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6);");
                lbl_rowsReturned.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void validateSearchFields() {
        lbl_warning.setVisible(false);
        lbl_warning.setText("");

        //VALIDATIONS

            //Species - must select one
            if (!radio_cat.isSelected() && !radio_dog.isSelected()) {
                lbl_warning.setText("Select either dog or cat.");
                lbl_warning.setVisible(true);
                lbl_species.setStyle("-fx-text-fill: red");
            } else lbl_species.setStyle("-fx-text-fill: green");

            //Postal code - must not be blank and must match pattern: L4N6H1 (no spaces or dashes)
            if (txt_postCode.equals("")) {
                lbl_warning.setText("You must complete all search fields.");
                lbl_warning.setVisible(true);
                lbl_postCode.setStyle("-fx-text-fill: red");
            } else if (!txt_postCode.getText().matches("^[A-Za-z]\\d[A-Za-z]\\d[A-Za-z]\\d$")) {
                lbl_warning.setText("Postal code must match pattern: L4N6H1");
                lbl_warning.setVisible(true);
                lbl_postCode.setStyle("-fx-text-fill: red");
            } else lbl_postCode.setStyle("-fx-text-fill: green");

            //Gender - at least one must be selected
            if (!chkbox_female.isSelected() && !chkbox_male.isSelected()) {
                lbl_warning.setText("Select gender(s)");
                lbl_warning.setVisible(true);
                lbl_gender.setStyle("-fx-text-fill: red");
            } else lbl_gender.setStyle("-fx-text-fill: green");
    }

    @FXML
    void displayDetails(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/petDetailView.fxml"));
        Parent root = loader.load();


        //pass values from selected pet to the detail view  (this knowledge was gained from source:https://jagar.me/post/passingdatainjavafx/)
        PetDetailViewController petDetailViewController = loader.getController();
        petDetailViewController.setNameText(hiddenName.getText());
        petDetailViewController.setBreedText(hiddenBreed.getText());
        petDetailViewController.setAgeText(hiddenAge.getText());
        petDetailViewController.setGenderText(hiddenGender.getText());
        petDetailViewController.setSizeText(hiddenSize.getText());
        petDetailViewController.setURLText(hiddenURL.getText());
        petDetailViewController.setImage(hiddenImage.getText());


        Scene scene2 = new Scene(root);
        //Stage stage = new Stage();
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();

//        //hold the current scene open while switching to other scene so you can return with values preloaded
//        stage.initOwner(btn_details.getScene().getWindow());
//        stage.showAndWait();

        stage.setTitle("Pet Details");
        //add custom css styling to scene
        scene2.getStylesheets().add("/CSS/style.css");
        // change default icon to paw print image
        stage.getIcons().add(new Image("/Images/paw.png"));
        stage.setResizable(false);
        stage.setScene(scene2);
        stage.show();
    }
}