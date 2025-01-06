
import mayflower.*;

public class MayAnimation
{
    private MayflowerImage[] frames;
    private int framerate;
    private int currentFrame;
    
    public MayAnimation(int fr, String[] f)
    {
        framerate = fr;
        currentFrame = 0;
        frames = new MayflowerImage[f.length];
        for(int i=0; i<f.length; i++)
        {
            frames[i]= new MayflowerImage(f[i]);
            frames[i].scale(200,174);
        }
    }
    public int getFrameRate()
    {
        return framerate;
    }
    public MayflowerImage getNextFrame()
    {
        currentFrame++;
        if(currentFrame>=frames.length)
        {
            currentFrame = 0;
        }
        return frames[currentFrame];
    }
    public void scale(int w, int h)
    {
        for(int i=0; i<frames.length; i++)
        {
            frames[i].scale(w,h);
        }
    }
    public void setTransparency(int p)
    {
        for(int i=0; i<frames.length; i++)
        {
            frames[i].setTransparency(p);
        }
    }
    public void mirrorHorizontally(int x)
    {
        if(x>0)
        {
            for(int i=0; i<frames.length; i++)
            {
                frames[i].mirrorHorizontally();
            }
        }
    }
    public void setBounds(int x, int y, int w, int h)
    {
        for(int i=0; i<frames.length; i++)
        {
            frames[i].crop(x,y,w,h);
        }
    }
}