import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bulletE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bulletE extends Actor
{
    /**
     * Act - do whatever the bulletE wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public float speed;
    public float damage;
    public int timer=0;
    public int explosiveNumber;
    public String imageName;
    public Image body;
    public Main main;
    private GreenfootImage bodyImage;
    public bulletE()
    {
        
    }
    protected void addedToWorld(World world)
    {
        setImage(imageName);
        bodyImage=main.bodyImage;
        body=new Image();
        body.setRotation(getRotation());
        body.setImage(new GreenfootImage(bodyImage));
        body.getImage().scale((int)(getImage().getWidth()*0.75),(int)(getImage().getHeight()*0.75));
        getWorld().addObject(body,getX(),getY());
    }
    private float debtX=0.0f;
    private float debtY=0.0f;
    public void movement()
    {
        debtX+=(speed*Math.sin(getRotation()*Math.PI/180.0))-(int)(speed*Math.sin(getRotation()*Math.PI/180.0));
        debtY+=(speed*Math.cos(getRotation()*Math.PI/180.0))-(int)(speed*Math.cos(getRotation()*Math.PI/180.0));
        setLocation(getX()-(int)(speed*Math.sin(getRotation()*Math.PI/180.0))-(int)debtX,getY()+(int)(speed*Math.cos(getRotation()*Math.PI/180.0))+(int)debtY);
        if(debtX>=1.0)
        {
            debtX--;
        }
        if(debtX<=-1.0)
        {
            debtX++;
        }
        if(debtY>=1.0)
        {
            debtY--;
        }
        if(debtY<=-1.0)
        {
            debtY++;
        }
        body.setLocation(getX(), getY());
    }
    private int updateTimer=0;
    public void hit()
    {
        if (getX()<=270||getX()>=730||getY()+(int)(getImage().getHeight()/2)<-30||getY()+(int)(getImage().getHeight()/2)>main.getHeight()+30)
        {
            getWorld().removeObject(body);
            getWorld().removeObject(this);
        }
        else
        {
           if(main.player[0].isShieldOn&&body.isCollideWith(main.player[0].shieldBody))
           {
               main.explodeSound();
               Effect effect=new Effect("explode2",8);
                   effect.transparency=150;
                   main.addObject(effect,getX()-3+Greenfoot.getRandomNumber(6),getY()-3+Greenfoot.getRandomNumber(6));
                   main.player[0].shieldHealth-=damage;
                   getWorld().removeObject(body);
                   getWorld().removeObject(this);
            }
            else
            {
               if(body.isCollideWith(main.player[0].Pbody))
               {
                   main.explodeSound();
                   Effect effect=new Effect("explode2",8);
                   effect.transparency=150;
                   main.addObject(effect,getX()-3+Greenfoot.getRandomNumber(6),getY()-3+Greenfoot.getRandomNumber(6));
                   main.player[0].health-=damage;
                   getWorld().removeObject(body);
                   getWorld().removeObject(this);
               }
           }
           if(main.player.length==2&&main.player[1].isShieldOn&&body.isCollideWith(main.player[1].shieldBody))
           {
               main.explodeSound();
               Effect effect=new Effect("explode2",8);
                   effect.transparency=150;
                   main.addObject(effect,getX()-3+Greenfoot.getRandomNumber(6),getY()-3+Greenfoot.getRandomNumber(6));
                   main.player[1].shieldHealth-=damage;
                   getWorld().removeObject(body);
                   getWorld().removeObject(this);
            }
            else
            {
               if(main.player.length==2&&body.isCollideWith(main.player[1].Pbody))
               { 
                   main.explodeSound();
                   Effect effect=new Effect("explode2",8);
                   effect.transparency=150;
                   main.addObject(effect,getX()-3+Greenfoot.getRandomNumber(6),getY()-3+Greenfoot.getRandomNumber(6));
                   main.player[1].health-=damage;
                   getWorld().removeObject(body);
                   getWorld().removeObject(this);
               }
            }
        }
        
    }
    public void act() 
    {
        if (main.isGameRunning&&!main.isPausing)
        {
            timer++;
            movement();
            if(explosiveNumber!=0&&timer==100)
            {
                for (int i=0;i<explosiveNumber;i++)
                {
                    bulletE clone=new bulletE();
                    clone.imageName=imageName;
                    clone.damage=damage/explosiveNumber;
                    clone.explosiveNumber=(int)(explosiveNumber/4.0);
                    clone.speed=speed+1;
                    clone.setRotation(Greenfoot.getRandomNumber(180)+(int)(i*360.0/explosiveNumber));
                    clone.main=main;
                    main.addObject(clone,getX(),getY());
                }
                main.removeObject(body);
                main.removeObject(this);
            }
            else
            {
            hit();
            }
        }
        // Add your action code here.
    }    
}
