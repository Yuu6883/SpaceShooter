import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends Actor
{
    /**
     * Act - do whatever the background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int speed;
    public Main main;
    public String background;
    protected void addedToWorld(World world)
    { 
        background=main.backgrounds[main.currentBg%main.backgrounds.length];
        setImage(background);
        getImage().scale(400,main.getHeight());
    }
    public void act() 
    {
        if (main.isGameRunning&&!main.isPausing)
        {
            if (main.timer%speed==0)
            {
                  main.currentBg++;
                  setLocation(getX(),getY()+1);
                  if(getY()>1.5*main.getHeight())
                  {
                      Background bg=new Background();
                      bg.speed=speed;
                      bg.main=main;
                      main.addObject(bg,getX(),-(int)(main.getHeight()/2.0));
                      main.removeObject(this);
                   }
            }
        }
    }    
}
