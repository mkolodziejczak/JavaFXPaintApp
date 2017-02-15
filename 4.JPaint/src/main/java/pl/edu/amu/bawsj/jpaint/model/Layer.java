package pl.edu.amu.bawsj.jpaint.model;



import java.util.ArrayList;

/**
 * Created by Maciek on 2016-12-27.
 */
public class Layer
{

    private int layerId;
    public Boolean isDrawn = true;
    private ArrayList<Drawable> layerObjects = new ArrayList<>();

    public Layer(int layerId)
    {
        this.layerId = layerId;
    }

    public void addToLayer(Drawable object)
    {
        layerObjects.add(object);


    }

    public void drawLayer()
    {
        for (Drawable layerObject : layerObjects) {

            if (layerObject.isRemoved == false)
                layerObject.draw();
        }
    }

    public int getLayerId()
    {
        return this.layerId;
    }

}