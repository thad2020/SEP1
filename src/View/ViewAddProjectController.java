package View;

import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewAddProjectController
{
  @FXML private Label title;
  @FXML private Button closeButton;

  public void closeButtonAction()
  {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }
}
