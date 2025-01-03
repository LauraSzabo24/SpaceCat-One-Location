import mayflower.*;
import java.util.*;

public class OurWorld extends World {
    private Cat cat;
    private String[][] tiles;
    public boolean generateLevel;
    private ArrayList<Actor> objects; 
    public int catLastY;
    public int catLastX;
    public Play play;
    private boolean started;
    private newPortal p1;
    private newPortal p2;
    
    public OurWorld() 
    {
        started = false;
        setBackground("img/Tiles/SPACECAT (1).png");
        play = new Play();
        addObject(play, 300, 100);
        //addObject(play, 250, 150);
        play.scale(200,200);
        
        p1 = new newPortal();
        p2 = new newPortal();
    }
    public String[][] getTiles()
    {
        return tiles;
    }
    public void act()
    {
        if (play.getNewAction().equals("PlayGame")){
            playGame();
            play.setNewAction("Pause");
            started = true;
        }
        if(started)
        {
            if(cat.location[0]>=780)
            {
                destroyWorld();
                cat.setLocation(1, (((int)catLastY/100))*100);
                createWorld(1, ((int)catLastY/100)+1, 0, 0, 0);
            }
            else if(cat.location[0]<0)
            {
                destroyWorld();
                cat.setLocation(750, (((int)catLastY/100))*100);
                createWorld(8, ((int)catLastY/100)+1, 0, 1,1);
            }
            if(cat.getScore()>=20){
                destroyWorld();
                removeObject(cat);
                setBackground("img/Tiles/YOU WIN!.png");
            }
            if(cat.getLives()<=0){
                destroyWorld();
                removeObject(cat);
                setBackground("img/Tiles/YOU LOSE.png");
            }
        }
        
        //portals
        if(p1.moved == true)
        {
            if(p2.touching==false)
            {
                p1.moved = false;
            }
        }
        if(p2.moved == true)
        {
            if(p1.touching==false)
            {
                p2.moved = false;
            }
        }
        if((p1.touching || p2.touching) && (p1.moved==false && p2.moved==false))
        {
            System.out.println("movable");
            if(p1.touching)
            {
               p1.moveBack();
               System.out.println("p1");
               p1.moved = true;
            }
            else if(p2.touching)
            {
                p2.moveBack();
                System.out.println("p2");
                p2.moved = true;
            }
        }
    }
     public void playGame(){
        setBackground("img/Tiles/galaxy2.png");
        removeObject(play);
        tiles = new String[8][11]; //6,9
        objects = new ArrayList<Actor>();
        cat = new Cat();
        catLastY = 0;
        catLastX = 0;
        
        createWorld(1,1, 0, 0, 0);
        addMainCharacter(cat);
        
        //Mayflower.showBounds(true);
        showText("Score : " + cat.getScore() + " Lives: " + cat.getLives(), 10, 30, Color.WHITE);
    }
    public void createWorld(int catX, int catY, int xdir, int ydir, int dir)
    {
        System.out.println("World Created");
        buildWall();
        buildLowBorder();
        //System.out.println("cat's here: " + catX + " " + catY);
        makePath(catX,catY, xdir, ydir, dir);
        //printTiles();
        destroyLowBorder();
        buildWorld((catX-1)*100, (catY-1)*100);
        addStars();
        createPortal(catX, catY);
        //printTiles();
    }
    public void destroyWorld()
    {
        //System.out.println("World Destroyed");
        catLastY = cat.location[1];
        catLastX = cat.location[0];
        for(Actor object: objects)
        {
            removeObject(object);
        }
        
        for(int r=0; r<tiles.length; r++)
        {
            for(int c=0; c<tiles[r].length; c++)
            {
                tiles[r][c]= null;
            }
        }
    }
    public void addMainCharacter(Actor actor)
    {
        boolean added = false;
        int row = (int)(Math.random()*tiles.length);
        int col = (int)(Math.random()*tiles[0].length);
        while(added==false)
        {
            //row = (int)(Math.random()*tiles.length);
            //col = (int)(Math.random()*tiles[0].length);
            //System.out.println("r - " +row + "c - " + col);
            if(tiles[1][1]==null || tiles[1][1].equals("path"))
            {
                //System.out.println("added");
                addObject(actor,1, 1);
                tiles[1][1] = "actor";
                added = true;
            }
        }
    }
    public void buildWorld(int catX, int catY)
    {
        for(int r=0; r<tiles.length; r++)
        {
            for(int c=0; c<tiles[r].length; c++)
            {
                int random=(int)(Math.random()*6);
                if(tiles[r][c]==null && random==1)
                {
                    IceBlock i = new IceBlock(catX, catY);
                    addObject(i, (c-1)*(90), ((r)*100)-90);
                    objects.add(i);
                    tiles[r][c] = "iceblock";
                }
                else if(tiles[r][c]==null && random<4)
                {
                    Block b = new Block();
                    addObject(b, (c-1)*(90), ((r)*100)-90);
                    objects.add(b);
                    tiles[r][c] = "block";
                }
                else if(!(tiles[r][c]==null) && tiles[r][c].equals("block"))
                {
                    Block b = new Block();
                    addObject(b, (c-1)*(90), ((r)*100)-90);
                    objects.add(b);
                }
            }
        }
    }
    public void printTiles()
    {
        System.out.println();
        for(int r=0; r<tiles.length; r++)
        {
            for(int c=0; c<tiles[r].length; c++)
            {
                System.out.print(tiles[r][c]);
            }
            System.out.println();
        }
    }
    public void makePath(int startX, int startY, int xdir, int ydir, int dir)
    {
        int[] coor = new int[]{startY,startX};
        tiles[coor[0]+xdir][coor[1]+ydir] = "actor";
        //System.out.println(" block location " + (coor[0]+xdir-1) + " " + (coor[1]+ydir));
        tiles[coor[0]+xdir+1][coor[1]+ydir] = "block"; 
        tiles[coor[0]+xdir][coor[1]+ydir] = "path";
        //System.out.println("start = " + coor[0] + " " + coor[1]);
        //System.out.println("dir = " + dir);
        if(dir==0)
        {
            while(coor[0]<10)
            {
                coor = getNeighbor(coor[0], coor[1]);
                //System.out.println("coordinate = " + coor[0] + " " + coor[1]);
                if(!(coor[0]==-1 || coor[1]==-1))
                {
                    tiles[coor[0]][coor[1]] = "path";
                }
                else{
                    break;
                }
            }
        }
        else
        {
            //System.out.println("reversed");
            //System.out.println("coor1 is " + coor[0]);
            tiles[coor[0]][coor[1]] = "path";
            while(coor[0]<10)
            {
                coor = getNeighborBackwards(coor[0], coor[1]);
                //System.out.println("coordinate = " + coor[0] + " " + coor[1]);
                if(!(coor[0]==-1 || coor[1]==-1))
                {
                    tiles[coor[0]][coor[1]] = "path";
                }
                else{
                    break;
                }
            }
            //printTiles();
        }
        System.out.println("path complete");
    }
    public int[] getNeighbor(int r, int c)
    {
        //System.out.println("Startcoordinate - " + r + " " + c);
        if(c>=9)
        {
             //System.out.println("very early finish path complete");
             return new int[]{-1,-1};
        }
         ArrayList possibleNeighbors = new ArrayList<int[]>();
         int[][] neighbors = new int[][]{{r+1,c},{r-1,c},{r,c+1}}; //{r,c-1}
         for(int row=0; row<neighbors.length; row++)
         {
            //System.out.println("neighbor - " + neighbors[row][0] + " " + neighbors[row][1]);
            //System.out.println("tested - " + tiles[neighbors[row][0]][neighbors[row][1]]);
            if(tiles[neighbors[row][0]][neighbors[row][1]]==null)
            {
                possibleNeighbors.add(neighbors[row]);
                //System.out.println("added - " + neighbors[row][0] + " " + neighbors[row][1]);
            }
            else if(tiles[neighbors[row][0]][neighbors[row][1]].equals("path") && (neighbors[row][1]>9))
            {
                //System.out.println("early finish path complete - " + neighbors[row][0] + " " + neighbors[row][1]);
                return new int[]{-1,-1};
            }
         }
         int[] chosenOne = new int[2];
         if(possibleNeighbors.size()>0)
         {
             int random=(int)(Math.random()*possibleNeighbors.size());
             //System.out.println("random is " + random + " and pn is " + possibleNeighbors.size());
             chosenOne = (int[])possibleNeighbors.get(random);
             //System.out.print("chosen one - " + chosenOne[0] + chosenOne[1]);
         }
         else{
             if(c<9)
             {
                 chosenOne = new int[]{r,c+1};
             }
             else{
                 return new int[]{-1,-1};
             }
         }
         return chosenOne;
    }
    public int[] getNeighborBackwards(int r, int c)
    {
        //System.out.println("Startcoordinate - " + r + " " + c);
        if(c<=1)
        {
             //System.out.println("very early finish path complete");
             return new int[]{-1,-1};
        }
         ArrayList possibleNeighbors = new ArrayList<int[]>();
         int[][] neighbors = new int[][]{{r+1,c},{r-1,c},{r,c-1}}; //{r,c-1}
         for(int row=0; row<neighbors.length; row++)
         {
            //System.out.println("neighbor - " + neighbors[row][0] + " " + neighbors[row][1]);
            //System.out.println("tested - " + tiles[neighbors[row][0]][neighbors[row][1]]);
            if(tiles[neighbors[row][0]][neighbors[row][1]]==null)
            {
                possibleNeighbors.add(neighbors[row]);
                //System.out.println("added - " + neighbors[row][0] + " " + neighbors[row][1]);
            }
            else if(tiles[neighbors[row][0]][neighbors[row][1]].equals("path") && (neighbors[row][1]<1))
            {
                //System.out.println("early finish path complete - " + neighbors[row][0] + " " + neighbors[row][1]);
                return new int[]{-1,-1};
            }
         }
         int[] chosenOne = new int[2];
         if(possibleNeighbors.size()>0)
         {
             int random=(int)(Math.random()*possibleNeighbors.size());
             //System.out.println("random is " + random + " and pn is " + possibleNeighbors.size());
             chosenOne = (int[])possibleNeighbors.get(random);
             //System.out.print("chosen one - " + chosenOne[0] + chosenOne[1]);
         }
         else{
             if(c>1)
             {
                 chosenOne = new int[]{r,c-1};
             }
             else{
                 return new int[]{-1,-1};
             }
         }
         return chosenOne;
    }
    public void buildWall()
    {
        for(int t=0; t<11; t++)
        {
            tiles[0][t] = "wall";
        }
        for(int b=0; b<11; b++)
        {
            tiles[7][b] = "wall";
        }
        for(int l=0; l<8; l++)
        {
            tiles[l][0] = "wall";
        }
        for(int r=0; r<8; r++)
        {
            tiles[r][10] = "wall";
        }
    }
    public void buildLowBorder()
    {
        for(int b=0; b<11; b++)
        {
            tiles[6][b] = "tempWall";
        }
    }
    public void destroyLowBorder()
    {
        for(int b=0; b<11; b++)
        {
            tiles[6][b] = null;
        }
    }
    public void addStars()
    {
        for(int r=0; r<tiles.length-1; r++)
        {
            for(int c=0; c<tiles[r].length; c++)
            {
                int random = (int)(Math.random()*6);
                if(random<2 && tiles[r][c]!="block" && tiles[r][c]!="iceblock")
                {
                    Star s = new Star();
                    s.scale(1,1);
                    objects.add(s);
                    addObject(s, (c-1)*(95), ((r)*100)-70);
                    //System.out.println("ry - " +r + "cy- " + c);
                    tiles[r][c] = "star";
                }
            }
        }
        
        
    }
    //catX cat Y is if it doesn't work any other way
    public void createPortal(int catX, int catY)
    {
        //complicated portal thing
        int firstX=catX;
        int firstY=catY;
        
        int secX =catX;
        int secY = catY;
        
        
        //p1= new newPortal();
        FirstPortal:
        for(int r=1; r<tiles.length-1; r++)
        {
            for(int c=2; c<6; c++)
            {
                int random = (int)(Math.random()*6);
                if(random<3 && tiles[r][c]!="block" && tiles[r][c]!="iceblock")
                {
                   
                    
                    firstX = (c-1)*(95);
                    firstY = ((r)*100)-70;
                    
                    /////good
                    
                    objects.add(p1);
                    
                    addObject(p1, firstX-9, firstY);
                    
    
                    //System.out.println("ry - " +r + "cy- " + c);
                    tiles[r][c] = "portal";
                    break FirstPortal;
                }
            }
            
            }
        
        boolean added =false;
        SecondPortal:
        for(int r=2; r<tiles.length-1; r++)
        {
            for(int c=6; c<10; c++)
            {
               
                if(tiles[r][c]!="block" && tiles[r][c]!="iceblock" && tiles[r][c]=="star")
                {
                    //p2 = new newPortal();
                    objects.add(p2);
                    secX = (c-1)*(95);
                    secY = ((r)*100)-70;
                    addObject(p2, secX-20, secY);
                    p2.setLoc(firstX-9, firstY);
                    p1.setLoc(secX-9, secY);
                    
                    //System.out.println("ry - " +r + "cy- " + c);
                    tiles[r][c] = "portal";
                    added=true;
                    break SecondPortal;
                }
            }
        }
        //if it doesn't generate)
        if(added=false)
        {
           SecondNoStarPortal:
            for(int r=2; r<tiles.length-1; r++)
          {  
            for(int c=6; c<10; c++)
            {
               
                if(tiles[r][c]!="block" && tiles[r][c]!="iceblock" && tiles[r][c]=="star")
                {
                    //p2 = new newPortal();
                    objects.add(p2);
                    secX = (c-1)*(95);
                    secY = ((r)*100)-70;
                    addObject(p2, secX-20, secY);
                    p2.setLoc(firstX-9, firstY);
                    p1.setLoc(secX-9, secY);
                    
                    //System.out.println("ry - " +r + "cy- " + c);
                    tiles[r][c] = "portal";
                    break SecondNoStarPortal;
                }
            }
          }
        }
        }
}       
    
    /*
    public void buildWorld()
    {
        for(int r=0; r<tiles.length; r++)
        {
            for(int c=0; c<tiles[r].length; c++)
            {
                int random=(int)(Math.random()*6);
                
                if(tiles[r][c]==null && random<3)
                {
                    addObject(new Block(), (c)*(90), ((r+1)*100)-90);
                    tiles[r][c] = "block";
                }
            }
        }
    }*/
