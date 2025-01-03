  
import mayflower.*;

public class AnimatedObject extends Actor
{
    private Animation animation;
    private Timer animationTimer;
    public AnimatedObject()
    {
        animationTimer = new Timer(1000);
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