package pl.edu.amu.bawsj.jpaint.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;

/**
 * Created by Maciek on 2016-12-27.
 */
public class LayerManager
{

    private CommandManager commandManager;
    public LayerManager(CommandManager commandManager)
    {
        this.commandManager = commandManager;
    }
    ArrayList<Layer> layersList = new ArrayList<>();

    private int currentLayer=1;

    private Canvas canvas;

    public void setCanvas(Canvas canvas)
    {
        this.canvas = canvas;
    }

    public void addLayer()
    {
        int newLayerId = layersList.size() + 1;

        Layer newLayer= new Layer(newLayerId);

        layersList.add(newLayer);
        setCurrentLayer(newLayerId);
    }

    public void setCurrentLayer(int layerId)
    {
        this.currentLayer = layerId;

    }

    public void addToCurrentLayer(Drawable object)
    {
        for(int i=0; i<layersList.size(); i++)
        {
            if(layersList.get(i).getLayerId() == this.currentLayer)
            {
                layersList.get(i).addToLayer(object);


                commandManager.addCommand(object);
                commandManager.drawCommandsList();
                commandManager.drawUndoneCommandsList();
            }
        }

    }

    public void drawAllLayers()
    {
        this.canvas.getGraphicsContext2D().clearRect(0,0,this.canvas.getWidth(), this.canvas.getHeight());

        for(int i=0; i<layersList.size(); i++)
        {
            if(layersList.get(i).isDrawn)
                layersList.get(i).drawLayer();
        }
    }

    public void layerSwitch(int layerId)
    {
        for(int i=0; i<layersList.size(); i++)
        {
            if(layersList.get(i).getLayerId() == layerId)
            {
                if(layersList.get(i).isDrawn)
                {
                    layersList.get(i).isDrawn = false;
                }
                else
                {
                    layersList.get(i).isDrawn = true;
                }
            }
        }
    }

    public void moveLayerUp(int layerId)
    {
        for(int i=0; i<(layersList.size()-1); i++)
        {
            if(layersList.get(i).getLayerId() == layerId)
            {
                Layer tempLayer = layersList.get(i+1);
                layersList.set(i+1, layersList.get(i));
                layersList.set(i, tempLayer);
            }
        }

    }

    public void moveLayerDown(int layerId)
    {
        for(int i=1; i<layersList.size(); i++)
        {
            if(layersList.get(i).getLayerId() == layerId)
            {
                Layer tempLayer = layersList.get(i-1);
                layersList.set(i-1, layersList.get(i));
                layersList.set(i, tempLayer);
            }
        }
    }

    public void clear()
    {
        layersList.clear();
    }

    public void drawLayersList(GridPane gpanel)
    {
        gpanel.getChildren().clear();
        LayerListPainter painter = new LayerListPainter();
        for(int i=0; i<layersList.size(); i++)
        {
            painter.setLayerId(layersList.get(i).getLayerId());

            Label tempLabel = painter.createLabel();
            gpanel.add(tempLabel, 1, (i*4)+1);

            Button tempMoveUpButton = painter.createMoveUpButton();
            gpanel.add(tempMoveUpButton, 1, (i*4)+2);

            Button tempMoveDownButton = painter.createMoveDownButton();
            gpanel.add(tempMoveDownButton, 2, (i*4)+2);

            Button tempEnableDisableButton = painter.createEnableDisableButton();
            gpanel.add(tempEnableDisableButton, 1, (i*4)+3);

            Button tempSetCurrentButton = painter.createSetCurrentButton();
            gpanel.add(tempSetCurrentButton, 2, (i*4)+3);
        }

        Button tempAddLayerButton = painter.createAddLayerButton();
        gpanel.add(tempAddLayerButton, 1, (layersList.size()*4)+5);

    }


    public class LayerListPainter
    {
        private int layerId;
        public void setLayerId(int layerId)
        {
            this.layerId = layerId;
        }

        public Button createMoveUpButton()
        {
            Button tempMoveUpButton =  new Button("Move Up");

            tempMoveUpButton.setId(layerId+"");

            tempMoveUpButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    moveLayerUp(Integer.parseInt(tempMoveUpButton.getId()));
                    drawAllLayers();
                    drawLayersList((GridPane)tempMoveUpButton.getParent());
                }
            });

            return tempMoveUpButton;
        }

        public Button createMoveDownButton()
        {
            Button tempMoveDownButton = new Button("Move Down");
            tempMoveDownButton.setId(layerId+"");
            tempMoveDownButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    moveLayerDown(Integer.parseInt(tempMoveDownButton.getId()));
                    drawAllLayers();
                    drawLayersList((GridPane)tempMoveDownButton.getParent());
                }
            });
            return tempMoveDownButton;
        }

        public Button createEnableDisableButton()
        {
            Button tempEnableDisable = new Button("Enable/ Disable");
            tempEnableDisable.setId(layerId+"");
            tempEnableDisable.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    layerSwitch(Integer.parseInt(tempEnableDisable.getId()));
                    drawAllLayers();
                    drawLayersList((GridPane)tempEnableDisable.getParent());
                }
            });
            return tempEnableDisable;
        }

        public Button createSetCurrentButton()
        {
            Button tempSetCurrent = new Button("Set as current");
            tempSetCurrent.setId(layerId+"");
            tempSetCurrent.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    setCurrentLayer(Integer.parseInt(tempSetCurrent.getId()));
                    drawAllLayers();
                }
            });
            return tempSetCurrent;
        }

        public Button createAddLayerButton()
        {
            Button tempAddLayerButton = new Button("Add Layer");

            tempAddLayerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    addLayer();
                    drawLayersList((GridPane)tempAddLayerButton.getParent());
                }
            });
            return tempAddLayerButton;
        }

        public Label createLabel()
        {
            Label tempLabel = new Label("Layer "+layerId);
            return tempLabel;
        }
    }


}