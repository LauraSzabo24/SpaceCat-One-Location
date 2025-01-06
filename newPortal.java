import mayflower.*;

public class newPortal extends MayStableAnimatedActor
{
    private MayAnimation cool;
    
    private int goForX;
    private int goForY;
    
    private int goBackX;
    private int goBackY;
    public boolean touching;
    public boolean moved;
    public newPortal()
    {
        String[] arr = new String[4];
        //System.out.println("portal in the making");
        for(int i = 1; i<=4; i++)
        {
            arr[i-1] = "img/Portals/Portal ("+i+").png";
            System.out.println(i);
        }
        cool = new MayAnimation(20, arr); //120, arr
        cool.scale(20,80);
        setAnimation(cool);
        touching = false;
        moved = false;
    }
    public void setLoc(int x, int y)
    {
        goBackX = x;
        goBackY= y;
    }
    public void act()
    {
        super.act(); 
        if(isTouching(Cat.class))
        {
            touching = true;
        }
        else if(!isTouching(Cat.class))
        {
            touching = false;
        }
    }
    public void moveBack()
    {
        Object a = getOneIntersectingObject(Cat.class);  
        Cat c = (Cat) a;
        World w = getWorld();
        //c.setLocation(goBackX+20,goBackY+0); // +30 +0    '
        c.setLocation(goBackX,goBackY);
    }
}