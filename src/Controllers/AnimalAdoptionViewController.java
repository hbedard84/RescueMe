package Controllers;

import Models.*;
import Utilities.APIUtility;
import javafx.collections.ObservableList;
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

        //Configure Results Hbox as Hidden - it's just for storing data to pass to the next scene
        hiddenResults.setVisible(false);
        hiddenResults.managedProperty().bind(hiddenResults.visibleProperty());

        //Configure PostCode TextBox
        txt_postCode.setPromptText("eg. L0M1B1");

        //Configure Distance choicebox
        choiceBox_km.getItems().addAll("10","25","50","100","150","Any distance");  //define the items in the choicebox
        choiceBox_km.setValue("Any distance");  //sets the default value

        //Configure Species Radio Buttons
        //Group the radio buttons so only one can be selected at a time
        ToggleGroup speciesRadioGroup = new ToggleGroup();
        radio_cat.setToggleGroup(speciesRadioGroup);
        radio_dog.setToggleGroup(speciesRadioGroup);

        //Configure Warning Label
        lbl_warning.setText("");
        lbl_warning.setVisible(false);  //hide the warning label
        lbl_warning.managedProperty().bind(lbl_warning.visibleProperty());  //remove the space saved for the label until the visibility is set to true

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

        //Configure event listener for click on a row in the listview, if the listview is populated
            listView_results.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldValue, petSelected) -> {
                        if(petSelected != null) {  //only if a row is currently selected
                            petImage.setImage(new Image(petSelected.getAttributes().getPictureThumbnailUrl()));  //get the image retrieved from the API
                            petImage.setVisible(true);  //make pet image
                            btn_details.setVisible(true);  //make Details Button appear

                            //store the details of selected pet to pass to detail scene
                            hiddenName.setText(petSelected.getAttributes().getName());
                            hiddenAge.setText(petSelected.getAttributes().getAgeGroup());
                            hiddenGender.setText(petSelected.getAttributes().getSex());
                            hiddenImage.setText(petSelected.getAttributes().getPictureThumbnailUrl());
                            hiddenSize.setText(petSelected.getAttributes().getSizeGroup());
                            hiddenBreed.setText(petSelected.getAttributes().getBreedString());
                            hiddenURL.setText(petSelected.getAttributes().getUrl());
                        }
                    }
            );

    }

    /***
     * This method is called when the Search Button is clicked.
     * It uses the user input for species (cats or dogs) to make a call to the RescueOrg API
     * and return the list of available pets matching that species.
     * (Note:  The API is currently not working for searching with parameters of postal code and gender, so those variables will only come into play once the API updates)
     */
    @FXML
    private void searchPets() {

        //validate the search fields are valid
        validateSearchFields();

        //hide the More Details button until a row is clicked
        btn_details.setVisible(false);

        String species = null;

        //if search fields are valid, use those inputs to search API for matching pets then display in listview
        if (lbl_warning.getText().equals("")) {

            //get value of species from selected radio button
            if (radio_dog.isSelected()) {
                species = "dogs";
            } else {
                species = "cats";
            }

            //get value of postal code from textbox input
            String postalCode = txt_postCode.getText();

            //get value of distance from choicebox
            String distance = choiceBox_km.getValue();
            if (distance.equals("Any distance")){
                distance = "100000";
            }

            //get value of gender from selected checkboxes
            String gender = null;
            if (chkbox_male.isSelected() && !chkbox_female.isSelected())
                gender = "Male";
            else if (chkbox_male.isSelected() && chkbox_female.isSelected())
                gender = "Both";
            else if (!chkbox_male.isSelected() && chkbox_female.isSelected())
                gender = "Female";

            //clear the previous results from the listview and set image to the default
            listView_results.getItems().clear();
            petImage.setImage(new Image("./Images/paw.png"));

            //connect to API to get pet search results, then add them to the listview, and display the results
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
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /***
     * This method validates the input entered by the user in the search form
     */
    @FXML
    private void validateSearchFields() {

        //reset warning label to default empty and hidden
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
            if (txt_postCode.getText().equals("")) {
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

    /***
     * This method is called when the More Details button is clicked.
     * It loads new scene and passes the values of the selected pet to the new scene for display there.
     * @param event - 'More Details' Button Click
     * @throws IOException
     */
    @FXML
    void displayDetails(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/petDetailView.fxml"));
        Parent root = loader.load();

        //Pass values from selected pet to the detail view  (this knowledge was gained from source:https://jagar.me/post/passingdatainjavafx/)
        //Define the controller for the second scene
        PetDetailViewController petDetailViewController = loader.getController();
        //Use methods from the second scene controller to pass the values
        petDetailViewController.setNameText(hiddenName.getText());
        petDetailViewController.setBreedText(hiddenBreed.getText());
        petDetailViewController.setAgeText(hiddenAge.getText());
        petDetailViewController.setGenderText(hiddenGender.getText());
        petDetailViewController.setSizeText(hiddenSize.getText());
        petDetailViewController.setURLText(hiddenURL.getText());
        petDetailViewController.setImage(hiddenImage.getText());
        petDetailViewController.setListview(listView_results);

        Scene scene2 = new Scene(root);

        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();

        stage.setTitle("Pet Details");
        //add custom css styling to scene
        scene2.getStylesheets().add("/CSS/style.css");
        // change default icon to paw print image
        stage.getIcons().add(new Image("/Images/paw.png"));
        stage.setResizable(false);
        stage.setScene(scene2);
        stage.show();
    }

    /***
     * This method is used to pass data between the scenes.
     * It sets the items in the AnimalAdoptionView scene's listview to those being passed back from the pet detail scene's listview
     * @param listview - Pet Detail View's listview
     */
    public void setListview(ListView<Data> listview){
        ObservableList<Data> list = listview.getItems();
        listView_results.setItems(list);
    }
}