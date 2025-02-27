import mayflower.*;

public class Portal extends MayAnimatedActor
{
    private MayAnimation cool;
    
    private int goForX;
    private int goForY;
    
    private int goBackX;
    private int goBackY;
    public Portal()
    {        
        String[] arr = new String[4];
        for(int i = 1; i<=4; i++)
        {
            arr[i-1] ="img/Portals/Portal ("+i+").png";
            System.out.println(i);
        }
        cool = new MayAnimation(120, arr);
        cool.scale(70,90);
        setAnimation(cool);
    }
    
    public void setLoc(int x, int y)
    {
        goBackX = x;
        goBackY= y;
    }
    
    public void act()
    {
     
        if(isTouching(Cat.class))
        {
         Object a = getOneIntersectingObject(Cat.class);  
         Cat c = (Cat) a;
         World w = getWorld();
        
         c.setLocation(goBackX+30,goBackY+0);
         
         
    }
}
}