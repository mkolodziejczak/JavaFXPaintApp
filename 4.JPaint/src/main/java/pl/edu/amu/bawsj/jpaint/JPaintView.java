package pl.edu.amu.bawsj.jpaint;


import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.*;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Level;


/**
 * Created by s396350 on 02.12.2016.
 */
public class JPaintView extends Application{

    private JPaintPresenter presenter;
    public Canvas canvas;
    public GridPane undoneCommandsGrid = new GridPane();
    public GridPane commandsGrid = new GridPane();

    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        try
        {
            primaryStage.setTitle("Canvas");
            primaryStage.setResizable(true);
            Group root = new Group();

            canvas = new Canvas(450, 450);

            GraphicsContext gc = canvas.getGraphicsContext2D();
            presenter = new JPaintPresenter( this );
            root.getChildren().addAll(canvas);


            Scene scene = new Scene(root);
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    canvas.setWidth(newSceneWidth.doubleValue());
                }
            });
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                    canvas.setHeight(newSceneHeight.doubleValue());
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent ->
            {
                try
                {
                    presenter.pickedTool.onMousePressed(mouseEvent);
                }
                catch(NullPointerException e)
                {
                    System.out.print("No tool selected.");
                }
            });

            scene.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseEvent ->
            {
                try
                {
                    presenter.pickedTool.onMouseReleased(mouseEvent);
                }
                catch(NullPointerException e)
                {
                    System.out.print("No tool selected.");
                }
            });

            scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseEvent ->
            {
                try
                {
                    presenter.pickedTool.onMouseDragged(mouseEvent);
                }
                catch(NullPointerException e)
                {
                    System.out.print("No tool selected.");
                }
            });

            scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> presenter.keyPressHandler(keyEvent, canvas));

            primaryStage.setScene(scene);
            primaryStage.show();

            Pane toolbox = FXMLLoader.load( getClass()
                    .getResource( "/toolbox.fxml" ) );
            Stage toolboxStage = new Stage();
            toolboxStage.setTitle("Toolbox");
            toolboxStage.setScene(new Scene(toolbox, 300, 150));

            toolboxStage.show();
            SepiaTone sepiaTone = new SepiaTone();
            sepiaTone.setLevel(0.7);
            toolbox.lookup( "#circle" )
                    .setOnMouseClicked( event -> presenter.initCircleTool(gc) );
            toolbox.lookup( "#rectangle" )
                    .setOnMouseClicked( event -> presenter.initRectangleTool(gc) );
            toolbox.lookup( "#line" )
                    .setOnMouseClicked( event -> presenter.initLineTool(gc) );
            toolbox.lookup( "#curve" )
                    .setOnMouseClicked( event -> presenter.initCurveTool(gc) );
            toolbox.lookup( "#triangle" )
                    .setOnMouseClicked( event -> presenter.initTriangleTool(gc) );
            toolbox.lookup( "#text" )
                    .setOnMouseClicked( event -> presenter.initTextTool(gc) );


            ColorPicker lc = (ColorPicker) toolbox.lookup("#lineColor");
            lc.setOnAction(event -> presenter.pickedTool.onStrokeColorChange(lc.getValue()));
            ColorPicker fc = (ColorPicker) toolbox.lookup("#fillColor");
            fc.setOnAction(event -> presenter.pickedTool.onFillColorChange(fc.getValue()));

            GridPane gpanel = new GridPane();
            Pane layers = new Pane();
            layers.getChildren().add(gpanel);

            Scene layersScene = new Scene(layers, 300, 150);
            Stage layersStage = new Stage();
            layersStage.setTitle("Layers");
            layersStage.setScene(layersScene);
            layersStage.show();

            Pane commands = new Pane();
            commands.getChildren().add(commandsGrid);

            Scene commandsScene = new Scene(commands, 300, 150);
            Stage commandsStage = new Stage();
            commandsStage.setTitle("Command Stack");
            commandsStage.setScene(commandsScene);
            commandsStage.show();


            Pane undoneCommands = new Pane();
            undoneCommands.getChildren().add(undoneCommandsGrid);

            Scene undoneCommandsScene = new Scene(undoneCommands, 300, 150);
            Stage undoneCommandsStage = new Stage();
            undoneCommandsStage.setTitle("Undone Command Stack");
            undoneCommandsStage.setScene(undoneCommandsScene);
            undoneCommandsStage.show();

            presenter.layerManager.drawLayersList(gpanel);

            gc.setStroke(Color.BLACK);
            gc.setFill(Color.WHITE);

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }



}
