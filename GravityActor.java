 
import mayflower.*;

public class GravityActor extends Actor
{
    private boolean passLowLimit;
    public GravityActor()
    {
        passLowLimit = false;
    }
    public boolean isBlocked()
    {
        if(isTouching(Block.class))
        {
            return true;
        }
        return false;
    } 
    public boolean isFalling()
    {
        boolean ret;
        setLocation(getX(),getY()+1);
        ret = isTouching(Block.class);
        setLocation(getX(),getY()-1);
        return !ret;
    }
    public void act()
    {
        setLocation(getX(), getY()+1);
        if(getY()>600)
        {
            passLowLimit = true;
            setLocation(getX(), 0);
        }
        else{
            passLowLimit = false;
        }
        if(isBlocked())
        {
            setLocation(getX(), getY()-1);
        }
    }
}