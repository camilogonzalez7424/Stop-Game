package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ventana2 {


    @FXML
    private AnchorPane ventana2;
    
	@FXML
    private Label ownNameResult;

    @FXML
    private Label opponentNameResult;

    @FXML
    private Label ownAnimalResult;

    @FXML
    private Label opponentAnimalResult;

    @FXML
    private Label ownLocationResult;

    @FXML
    private Label opponentLocationResult;

    @FXML
    private Label ownObjectResult;

    @FXML
    private Label opponentObjectResult;
    
    @FXML
    private Label ownTotal;

	@FXML
    private Label opponentTotal;

    @FXML
    private Button finishBtn;

    @FXML
    public void finishAction(ActionEvent event) {
		Stage stage = (Stage) ventana2.getScene().getWindow();
		stage.close();
    }

   //Getters And Setters 

	public Label getOwnNameResult() {
		return ownNameResult;
	}

	public void setOwnNameResult(Label ownNameResult) {
		this.ownNameResult = ownNameResult;
	}

	public Label getOpponentNameResult() {
		return opponentNameResult;
	}

	public void setOpponentNameResult(Label opponentNameResult) {
		this.opponentNameResult = opponentNameResult;
	}

	public Label getOwnAnimalResult() {
		return ownAnimalResult;
	}

	public void setOwnAnimalResult(Label ownAnimalResult) {
		this.ownAnimalResult = ownAnimalResult;
	}

	public Label getOpponentAnimalResult() {
		return opponentAnimalResult;
	}

	public void setOpponentAnimalResult(Label opponentAnimalResult) {
		this.opponentAnimalResult = opponentAnimalResult;
	}

	public Label getOwnLocationResult() {
		return ownLocationResult;
	}

	public void setOwnLocationResult(Label ownLocationResult) {
		this.ownLocationResult = ownLocationResult;
	}

	public Label getOpponentLocationResult() {
		return opponentLocationResult;
	}

	public void setOpponentLocationResult(Label opponentLocationResult) {
		this.opponentLocationResult = opponentLocationResult;
	}

	public Label getOwnObjectResult() {
		return ownObjectResult;
	}

	public void setOwnObjectResult(Label ownObjectResult) {
		this.ownObjectResult = ownObjectResult;
	}

	public Label getOpponentObjectResult() {
		return opponentObjectResult;
	}

	public void setOpponentObjectResult(Label opponentObjectResult) {
		this.opponentObjectResult = opponentObjectResult;
	}
	
	public Label getOwnTotal() {
		return ownTotal;
	}

	public void setOwnTotal(Label ownTotal) {
		this.ownTotal = ownTotal;
	}

	public Label getOpponentTotal() {
		return opponentTotal;
	}

	public void setOpponentTotal(Label opponentTotal) {
		this.opponentTotal = opponentTotal;
	}
    
}
