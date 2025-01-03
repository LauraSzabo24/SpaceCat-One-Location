import mayflower.*;
public class MovableAnimatedActor extends AnimatedActor
{
   
     private Animation walkRight;
     private Animation idle;
     private Animation idleLeft;
     private Animation leftFalling;
     private Animation rightFalling;
     private Animation walkLeft;
     private String currentAction;
     private String direction;
     
     private boolean jumping;
     private boolean passLimit;
     private int oldY;
     
     public int[] location;
     
     public MovableAnimatedActor()
     {
         direction = "right";
         jumping = false;
         passLimit = false;
         oldY = getY();
         location = new int[]{getX(), getY()};
     }
     public void setJumping(boolean state)
     {
         jumping = state;
     }
    public void act()
    {
       
        super.act();

        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
        location = new int[]{x, y};
        String newAction = null;
        
        if(y<0)
        {
            passLimit = true;
        }
        else{
            passLimit = false;
        }
        
        if(currentAction==null)
        {
            newAction= "idle";
        }
       
        if(jumping && !passLimit)
        {
             if(getY()>oldY-20 && !isBlocked())
             {
                 setLocation(x, y-13);
             }
             else
             {
                 jumping = false;
                 setLocation(x, y+12);
             }
         }
         if(jumping && passLimit)
        {
             oldY = getY();
             setLocation(x, oldY+610);
             if(getY()>oldY-20 && !isBlocked())
             {
                 setLocation(x, getY()-13);
             }
             else
             {
                 jumping = false;
                 setLocation(x, y+12);
             }
         }
         
        //MovingForward Section
        if(Mayflower.isKeyDown(Keyboard.KEY_UP))//&& y-5>0 
        {
            jumping = true;
            int oldY = y;
            if(isBlocked())
            {
                setLocation(x, y+1);
            }
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT) )//x+5+w<800
        {
            if(!Mayflower.isKeyDown(Keyboard.KEY_UP))
            {
                jumping = false;
            }
            setLocation(x+1, y);
            if(!isFalling())
            {
                newAction = "walkRight";
            }
            direction = "right";
            if(isBlocked())
            {
                setLocation(x-1, y);
            }
        }
         else if(Mayflower.isKeyDown(Keyboard.KEY_LEFT))// && x-5>0
        {
            if(!Mayflower.isKeyDown(Keyboard.KEY_UP))
            {
                jumping = false;
            }
            setLocation(x-1, y);
            if(!isFalling())
            {
                newAction = "walkLeft";
            }
            direction = "left";
            if(isBlocked())
            {
                setLocation(x+1, y);
            }
        }
        else if(!isFalling())
        {
            newAction = "idle";
            if(direction!=null && direction.equals("left"))
            {
                newAction = "idleLeft";
            }
        }
        if(isFalling())
        {
            if(direction!=null && direction.equals("left"))
            {
                newAction = "fallLeft";
            }
            if(direction!=null && direction.equals("right"))
            {
                 newAction = "fallRight";
            }
        }
       
        if((!(newAction==null)) && !(newAction.equals(currentAction)))
        {
         
        if(newAction.equals("walkRight"))
         {
            setAnimation(walkRight);
         }
         if(newAction.equals("idle"))
         {
             setAnimation(idle);
         }
         if(newAction.equals("idleLeft"))
         {
             setAnimation(idleLeft);
         }
         if(newAction.equals("walkLeft"))
         {
             setAnimation(walkLeft);
         }
         if(newAction.equals("fallLeft"))
         {
             setAnimation(leftFalling);
         }
         if(newAction.equals("fallRight"))
         {
             setAnimation(rightFalling);
         }
         
         currentAction = newAction;
        }
       
            }
    //might need to remove that
    public void setAnimation(Animation a)
    {
        super.setAnimation(a);
    }
   
    public void setWalkRightAnimation(Animation ani)
    {
        walkRight = ani;
    }
   
    public void setIdleAnimation(Animation ani)
    {
        idle = ani;
    }
   
    public void setWalkLeftAnimation(Animation ani)
    {
        walkLeft = ani;
    }
   
    public void setIdleLeftAnimation(Animation ani)
    {
        idleLeft = ani;
    }
   
    public void setLeftFallingAnimation(Animation ani)
    {
        leftFalling = ani;
    }
   
    public void setRightFallingAnimation(Animation ani)
    {
        rightFalling = ani;
    }
   
}
