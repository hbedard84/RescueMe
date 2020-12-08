package Controllers;

import Models.*;
import Utilities.APIUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        //Configure the rows returned label
        lbl_rowsReturned.setText("");
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
            if (distance == "Any distance"){
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

            //connect to API to get results

            try {
                APIResponse apiResponse = APIUtility.callResponseAPI(species);
                List<Data> pets = Arrays.asList(apiResponse.getData());
                MetaData meta = apiResponse.getMeta();

                listView_results.getItems().addAll(pets);
                //int allPetCount = metaResponse.getCount();
//                int petsReturned = metaResponse.getCountReturned();


                System.out.println(meta);


//                MetaData metaResponse = APIUtility.callMetaAPI(species);
//                Data dataResponse = APIUtility.callDataAPI(species);
//                Attributes attributesResponse = APIUtility.callAttributesAPI(species);
//
//                //get data from meta
//
//                int allPetCount = metaResponse.getCount();
//                int petsReturned = metaResponse.getCountReturned();

//                //get data from data
//                 Attributes pets = dataResponse.getAttributes();
//
//                //get data from attributes
//                String petAge = attributesResponse.getAgeGroup();





 //               lbl_rowsReturned.setText(String.format("Displaying %d / %d pets available", petsReturned, allPetCount));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }






//            try {
//                //PetJsonResponse response = APIUtility.callDataAPI(species);
//                HighLevelInfo highLevelResponse = APIUtility.callPetAPI(species);
//                //AnimalAdoptionInfo animalResponse = APIUtility.callAdoptionAPI(species);
//
//                List<AnimalAdoptionInfo> pets = Arrays.asList(highLevelResponse.getAttributes());
//                //List<HighLevelInfo> data = Arrays.asList(response.getData());
//                //List<String> details = Arrays.asList(animalResponse.getName());
//
//                listView_results.getItems().addAll(pets);
//                System.out.println(pets);
//                lbl_rowsReturned.setText("Pets Returned: "+highLevelResponse.getCount());
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
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
}