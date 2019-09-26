import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gif here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends Actor
{
    /**
     * Act - do whatever the gif wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage[] image;
    public int fadeInTime=0;
    private int currentImage=0;
    public boolean isSelfDestructive=true;
    public boolean isFading=true;
    private int timer=0;
    public int frequency=5;
    public int transparency=255;
    public float sizeScale=1;
    public int cloneNumber=0;
    private Effect[] clone;
    private String name;
    private int ImageNumber;
    public int cloneRange=100;
    public Effect(String Name,int imageNumber)
    {
        image=new GreenfootImage[imageNumber];
        for (int i=0;i<imageNumber;i++)
        {
            image[i]=new GreenfootImage(Name+"."+(i+1)+".png");
        }
        name=Name;
        ImageNumber=imageNumber;
    }
    protected void addedToWorld(World world)
    {
         for (int i=0;i<image.length;i++)
        {
            image[i].scale((int)(image[i].getWidth()*sizeScale),(int)(image[i].getHeight()*sizeScale));
        }
         setImage(image[currentImage]);
    }
    public void act() 
    {
        
         if (cloneNumber!=0&&timer==10)
           {
                Effect clone=new Effect(name,ImageNumber);
                clone.sizeScale=sizeScale;
                clone.cloneNumber=cloneNumber-1;
                cloneNumber--;
                getWorld().addObject(clone,getX()-cloneRange+Greenfoot.getRandomNumber(2*cloneRange),getY()-cloneRange+Greenfoot.getRandomNumber(2*cloneRange));
           }  
        if (timer%frequency==0)
        {
            setImage(image[currentImage]);
            if (isFading)
            {
                   getImage().setTransparency(transparency-(int)(currentImage*transparency/(float)image.length));
            }
            else
            {
                getImage().setTransparency(transparency);
            }
            currentImage++;
        }
        if (currentImage>=image.length&&isSelfDestructive)
        {
           
            getWorld().removeObject(this);
        }
        if (currentImage>=image.length&&!isSelfDestructive)
        {
            currentImage=0;
        }
        if(fadeInTime!=0&&timer<fadeInTime)
        {
            getImage().setTransparency((int)(timer*transparency/(float)fadeInTime));
        }
        // Add your action code here.
        timer++;
    }    
}
