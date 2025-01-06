 
import mayflower.*;

public class MayStableAnimatedActor extends Actor
{
    private MayAnimation animation;
    private Timer animationTimer;
    public MayStableAnimatedActor()
    {
        animationTimer = new Timer(200000000);
    }
    public void setAnimation(MayAnimation a)
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