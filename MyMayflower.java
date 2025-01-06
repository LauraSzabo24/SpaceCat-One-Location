 
import mayflower.*;

public class MyMayflower extends Mayflower 
{
    public MyMayflower()
    {
        super("window", 800, 600);
    }

    public void init()
    {
        Mayflower.setFullScreen(false);
        World w =  new OurWorld();
        Mayflower.setWorld(w);
    }
}
