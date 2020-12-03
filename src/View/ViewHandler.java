package View;

import Model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;

  // Tilføj alle view controllers her
  private ViewLoginController viewLoginController;
  private ViewListController viewListController;
  private ViewProjectController viewProjectController;

  private Model model;

  public ViewHandler(Model model)
  {
    this.model = model;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("viewLogin");
  }

  public void openView(String id)
  {
    Region root = null;

    switch (id)
    {
      case "viewLogin":
        root = loadViewLogin();
        break;
      case "viewList":
        root = loadViewList();
        break;
      case "viewProject":
        root = loadViewProject();
        break;
    }

    currentScene.setRoot(root);
    primaryStage.setTitle((String) root.getUserData());
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadViewLogin()
  {
    if (viewLoginController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewLogin.fxml"));
        Region root = loader.load();
        viewLoginController = loader.getController();
        viewLoginController.init(this, model, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewLoginController.reset();
    }

    return viewLoginController.getRoot();
  }

  private Region loadViewList()
  {
    if (viewListController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewList.fxml"));
        Region root = loader.load();
        viewListController = loader.getController();
        viewListController.init(this, model, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewListController.reset();
    }

    return viewListController.getRoot();
  }

  private Region loadViewProject()
  {
    if (viewProjectController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewProject.fxml"));
        Region root = loader.load();
        viewProjectController = loader.getController();
        viewProjectController.init(this, model, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewProjectController.reset();
    }

    return viewProjectController.getRoot();
  }
}
