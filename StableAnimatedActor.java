 
import mayflower.*;

public class StableAnimatedActor extends Actor
{
    private Animation animation;
    private Timer animationTimer;
    public StableAnimatedActor()
    {
        animationTimer = new Timer(200000000);
    }
    public void setAnimation(Animation a)
    {
        animation = a;
    }
    public void act()
    {
        if(animation!=null)
        {
            if(animationTimer.isDone())
            {
                animationTimer.reset();
                MayflowerImage next = animation.getNextFrame();
                setImage(next);
            }
        }
    }
}