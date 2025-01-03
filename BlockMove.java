import mayflower.*;

//when comented code below is used, and extends animated actor, no super.act(), teleport works.

public class BlockMove extends AnimatedActor
{
    
    

    public BlockMove()
    {
        
        setImage("img/Tiles/smallBlock.png");
       
    }
    
    
    public void act()
    {
     super.act();
        
}
}