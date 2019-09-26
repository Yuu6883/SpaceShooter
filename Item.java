import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    public int speed;
    public int type;
    public Image body;
    public Main main;
    private GreenfootImage bodyImage;
    private GreenfootSound item1=new GreenfootSound("item1.wav");
    private GreenfootSound item2=new GreenfootSound("shield.wav");
    public Item(int Type)
    {
        type=Type;
        item1.setVolume(85);
        item2.setVolume(80);
    }
    protected void addedToWorld(World world)
    {
        //type1: repair ship
        //type2: add live
        //type3: add shield
        switch (type)
        {
            case 1:
            setImage("wrench.png");
            getImage().scale(60,60);
            speed=3;
            break;
            case 2:
            setImage("heart.png");
            getImage().scale(50,50);
            speed=3;
            break;
            case 3:
            setImage("shieldIcon.png");
            getImage().scale(40,40);
            speed=3;
            break;
            case 4:
            setImage("big bullet.png");
            getImage().scale(50,50);
            speed=3;
            break;
            case 5:
            setImage("big bullet2.png");
            getImage().scale(50,50);
            speed=3;
            break;
        }
        bodyImage=main.bodyImage;
        body=new Image();
        GreenfootImage image=new GreenfootImage(bodyImage);
        body.setImage(image);
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
        if (getX()<=300||getX()>=700||getY()+(int)(getImage().getHeight()/2)>main.getHeight())
        {
            getWorld().removeObject(body);
            getWorld().removeObject(this);
        }
        else
        {
           if(body.isCollideWith(main.player[0].Pbody))
           {
               switch(type)
               {
                   case 1:
                   item1.play();
                   main.player[0].turnOnMode(3);
                   break;
                   case 2:
                   item1.play();
                   if(main.player[0].lives<10)
                   {
                        main.player[0].lives++;
                   }
                   break;
                   case 3:
                   item2.play();
                   if(main.player[0].isShieldOn)
                   {
                       main.player[0].shieldHealth=main.player[0].shieldMaxHealth;
                    }
                    else
                   {
                       main.player[0].isShieldOn=true;
                   }
                   break;
                   case 4:
                   item1.play();
                   main.player[0].turnOnMode(1);
                   break;
                   case 5:
                   item1.play();
                   main.player[0].turnOnMode(2);
                   break;
                }
               getWorld().removeObject(body);
               getWorld().removeObject(this);
           }
           if(main.player.length==2&&body.isCollideWith(main.player[1].Pbody))
           {
               switch(type)
               {
                   case 1:
                   main.player[1].turnOnMode(3);
                   break;
                   case 2:
                   if(main.player[1].lives<10)
                   {
                        main.player[1].lives++;
                   }
                   break;
                   case 3:
                   if(main.player[1].isShieldOn)
                   {
                       main.player[1].shieldHealth=main.player[1].shieldMaxHealth;
                    }
                    else
                   {
                       main.player[1].isShieldOn=true;
                   }
                   break;
                   case 4:
                   main.player[1].turnOnMode(1);
                   break;
                   case 5:
                   main.player[0].turnOnMode(2);
                   break;
                }
               getWorld().removeObject(body);
               getWorld().removeObject(this);
           }
        }
        
    }
    public void act() 
    {
        if (main.isGameRunning&&!main.isPausing)
        {
            movement();
            hit();
        }
        // Add your action code here.
    }     
}
