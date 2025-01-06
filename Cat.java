 
 
import mayflower.*;

public class Cat extends MayMovableAnimatedActor
{
    private MayAnimation walkRight, walkLeft, idle, idleLeft, fallLeft, fall;    
    private  int score;
    private  int lives;
    public Cat() 
    {
        walkRight = makeAnimation(walkRight,10,"Walk", 100, 87, 50, 0);
        walkLeft = makeAnimation(walkRight,10,"Walk", 100, 87, 50, 1);
        idle = makeAnimation(idle,10,"Idle", 100, 87, 50, 0);
        idleLeft = makeAnimation(idle,10,"Idle", 100, 87, 50, 1);
        fall = makeAnimation(idle,8,"Fall", 100, 87, 50, 0);
        fallLeft = makeAnimation(idle,8,"Fall", 100, 87, 50, 1);
        
        walkRight.setBounds(18,5,54,80);
        walkLeft.setBounds(28,5,54,80);
        idle.setBounds(18,5,54,80);
        idleLeft.setBounds(28,5,54,80);
        fall.setBounds(18,5,54,80);
        fallLeft.setBounds(28,5,54,80);
        
        lives = 9;
        score = 0;
        
        super.setWalkRightAnimation(walkRight);
        super.setIdleAnimation(idle);
        super.setWalkLeftAnimation(walkLeft);
        super.setIdleLeftAnimation(idleLeft);
        super.setLeftFallingAnimation(fallLeft);
        super.setRightFallingAnimation(fall);
    }
    public MayAnimation makeAnimation(MayAnimation anim, int l, String name, int w, int h, int t, int m)
    {
        String[] frames = new String[l];
        for(int i=1; i<=frames.length; i++)
        {
            frames[i-1]= "img/cat/" + name + " ("+i+").png";
        }
        anim = new MayAnimation(50, frames);
        anim.scale(w,h);
        //anim.setTransparency(t);
        anim.mirrorHorizontally(m);
        return anim;
    }
    public void act()
    {
        super.act(); 
        World w= getWorld();
    }
    public void increaseScore(int amount)
    {
        score+=amount;
        updateText();
    }
    
    public void decreaseLives()
    {
        lives-=1;
        updateText();
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLives()
    {
        return lives;
    }
   
    
    public  void updateText()
    {
        World w= getWorld();
        w.removeText(10,30);
        w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.WHITE);
    }
}
