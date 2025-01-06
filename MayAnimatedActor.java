  
import mayflower.*;

public class MayAnimatedActor extends MayGravityActor
{
    private MayAnimation animation;
    private Timer animationTimer;
    public MayAnimatedActor()
    {
        animationTimer = new Timer(50000000);
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
        super.act();
    }
}