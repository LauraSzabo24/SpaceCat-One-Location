

import mayflower.*;
public class Play extends Actor
{
    private String newAction = "PauseGame";
   
    public Play(){
       setImage("img/cat/Fall (1).png");
       //setImage("img/Tiles/play.png");
    }
   
    public String getNewAction(){
        return newAction;
    }
   
    public void setNewAction(String action){
         newAction = action;
    }
    public void act(){
   
        if (Mayflower.isKeyPressed(Keyboard.KEY_SPACE) ){
            //System.out.println("Inside play keyboard");
            //return true;
            newAction = "PlayGame";
        }
       
    }
    //banana
   

}