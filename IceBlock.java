import mayflower.*;

public class IceBlock extends Actor
{
    private int catLastX;
    private int catLastY;
    public IceBlock(int lastX, int lastY)
    {
        catLastX = lastX;
        catLastY = lastY;
        setImage("img/Tiles/ICE.png");
        System.out.println("tiles");
    }
    public void act()
    {
        if(isTouching(Cat.class))
        {
         Object a = getOneIntersectingObject(Cat.class);  
         Cat c = (Cat) a;
         World w = getWorld();
         c.decreaseLives();
         c.setJumping(false);
         c.setLocation(catLastX+30,catLastY+0);
        }
    }
}