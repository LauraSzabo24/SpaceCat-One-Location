
import mayflower.*;
public class Star extends Actor
{
    int count;
    boolean dir;
    public Star()
    {
        count = 0;
        dir = true;
        setImage("img/Object/Star.png");
    }
    public void act()
    {
        ///////touchingg-- new
        if(isTouching(Cat.class))
        {
         Object a = getOneIntersectingObject(Cat.class);  
         Cat c = (Cat) a;
         World w = getWorld();
         w.removeObject(this);
         c.increaseScore(1);
        }
        /*
        if(count<50 && dir==true)
        {
            if(count%3==0)
            {
                 setLocation(getX(), getY()+1);
            }
        }
        else{
            count = 0;
            dir = false;
        }
        if(count<50 && dir == false)
        {
            if(count%3==0)
            {
                 setLocation(getX(), getY()-1);
            }
        }
        else{
            count = 0;
            dir = true;
        }*/
    }
}