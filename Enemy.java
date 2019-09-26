import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Main main;
    public Image body;
    public int type;
    public float maxHealth;
    public float health;
    public float speed;
    private int[] bulletspeed={3,  5, 3,  5, 4, 8, 4, 3, 3};
    private int[] shootTimer= {130,50,100,50,110,60,120,70,80};
    private int[] shootNumber={2,  1, 1,  1, 5, 1, 3, 10,2};
    private float[] damage=   {10,  8,25,20,10, 10,15,15,50};
    private int timer=40;
    public float healthScale;
    private Image hp;
    private Image hpBar;
    private GreenfootImage bodyImage;
    public boolean isDead=false;
    public Enemy()
    {
        
    }
    protected void addedToWorld(World world)
    {
        
        
        switch(type)
        {
            case 1:
            speed=2;
            setImage("enemy"+type+".png");
            getImage().scale(80,80);
            maxHealth=healthScale*80.0f;
            break;
            case 2:
            speed=3;
            setImage("enemy"+type+".png");
            getImage().scale(80,80);
            maxHealth=healthScale*70.0f;
            break;
            case 3:
            speed=1;
            setImage("enemy"+type+".png");
            getImage().scale(90,90);
            maxHealth=healthScale*130.0f;
            break;
            case 4:
            speed=0.5f;
            setImage("enemy"+type+".png");
            getImage().scale(110,150);
            maxHealth=healthScale*190.0f;
            break;
            case 5:
            speed=1;
            setImage("enemy"+type+".png");
            getImage().scale(80,130);
            maxHealth=healthScale*100.0f;
            break;
            case 6:
            speed=5;
            setImage("enemy"+type+".png");
            getImage().scale(120,70);
            maxHealth=healthScale*40.0f;
            break;
            case 7:
            speed=2;
            setImage("enemy"+type+".png");
            getImage().scale(120,70);
            maxHealth=healthScale*140.0f;
            break;
            case 8:
            speed=0.6f;
            setImage("enemy"+type+".png");
            getImage().scale(200,210);
            maxHealth=healthScale*600.0f;
            break;
            case 9:
            speed=0.4f;
            setImage("enemy"+type+".PNG");
            getImage().scale(200,210);
            maxHealth=healthScale*800.0f;
            break;
        }
        bodyImage=main.bodyImage;
        body=new Image();
        GreenfootImage image=new GreenfootImage(bodyImage);
        body.setImage(image);
        body.getImage().scale((int)(getImage().getWidth()*0.8),(int)(getImage().getHeight()*0.8));
        world.addObject(body,getX(),getY());
        health=maxHealth;
        hpBar=new Image();
        hp=new Image();
        hpBar.setImage("HPbar.png");
        hpBar.getImage().scale(getImage().getWidth(),14);
        world.addObject(hpBar,getX(),getY()-(int)(getImage().getHeight()/2)-20);
        hp.setImage("green.png");
        hp.getImage().scale(hpBar.getImage().getWidth(),10);
        world.addObject(hp,hpBar.getX(),hpBar.getY());
    }
    private void showHealth()
    {
        hpBar.setLocation(getX(),getY()-(int)(getImage().getHeight()/2)-20);
        if ((health/maxHealth)<=1.0&&health/maxHealth>=2.0/3.0)
        {
             hp.setImage("green.png");
        }
         if (health/maxHealth<2.0/3.0&&health/maxHealth>=1.0/3.0)
        {
             hp.setImage("yellow.png");
             if(type==8&&main.timer%30==0)
             {
                 Effect effect=new Effect("explode2",8);
                 main.addObject(effect,getX()-(int)(body.getImage().getWidth()/2.0)+Greenfoot.getRandomNumber(body.getImage().getWidth()),getY()-(int)(body.getImage().getHeight()/2.0)+Greenfoot.getRandomNumber(body.getImage().getHeight()));
              }
        }
        if (health/maxHealth<1.0/3.0&&health/maxHealth>0.0)
        {
             hp.setImage("red.png");
             if(type==8&&main.timer%15==0)
             {
                 Effect effect=new Effect("explode2",8);
                 main.addObject(effect,getX()-(int)(body.getImage().getWidth()/2.0)+Greenfoot.getRandomNumber(body.getImage().getWidth()),getY()-(int)(body.getImage().getHeight()/2.0)+Greenfoot.getRandomNumber(body.getImage().getHeight()));
              }
        }
        if((health/maxHealth)*hpBar.getImage().getWidth()>3.0)
        {
        hp.getImage().scale((int)((health/maxHealth)*hpBar.getImage().getWidth())-2,10);
        hp.setLocation((int)(hpBar.getX()-(int)(hpBar.getImage().getWidth()/2)+hp.getImage().getWidth()/2)+1,hpBar.getY());
        }
        else
        {
            hp.getImage().clear();
        }
    }
    private float debtX=0.0f;
    private float debtY=0.0f;
    public void movement()
    {
        debtX+=(speed*Math.sin(getRotation()*Math.PI/180.0))-(int)(speed*Math.sin(getRotation()*Math.PI/180.0));
        debtY+=(speed*Math.cos(getRotation()*Math.PI/180.0))-(int)(speed*Math.cos(getRotation()*Math.PI/180.0));
        setLocation(getX()+(int)(speed*Math.sin(getRotation()*Math.PI/180.0))+(int)debtX,getY()-(int)(speed*Math.cos(getRotation()*Math.PI/180.0))-(int)debtY);
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
    public void shoot()
    {
        if (timer>=shootTimer[type-1])
        {
            for (int i=0;i<shootNumber[type-1];i++)
            {
            bulletE BE=new bulletE();
            BE.main=main;
            BE.speed=bulletspeed[type-1];
            BE.damage=damage[type-1]*(float)(0.5+0.5*main.level);
             switch (type)
             {
                 case 1:
                 BE.imageName="bulletE1.png";
                 switch (i)
                 {
                     case 0:
                     main.addObject(BE,getX()+9,getY()+36);
                     break;
                     case 1:
                     main.addObject(BE,getX()-7,getY()+36);
                     break;
                 }
                 break;
                 case 2:
                 BE.imageName="bulletE2.png";
                 getWorld().addObject(BE,getX(),getY()+35);
                 break;
                 case 3:
                 BE.imageName="bulletE3.png";
                 getWorld().addObject(BE,getX(),getY()+35);
                 BE.getImage().scale(25,25);
                 break;
                 case 4:
                 BE.imageName="bulletE5.png";
                 getWorld().addObject(BE,getX(),getY()+35);
                 break;
                 case 5:
                 BE.imageName="bulletE6.png";
                 BE.setRotation(-30+i*15);
                 getWorld().addObject(BE,getX(),getY()+35);
                 break;
                 case 6:
                 BE.imageName="bulletE2.png";
                 getWorld().addObject(BE,getX(),getY()+30);
                 BE.getImage().scale(10,30);
                 break;
                 case 7:
                 BE.imageName="bulletE7.png";
                 BE.setRotation(8-8*i);
                 getWorld().addObject(BE,getX()-10+10*i,getY()+30);
                 BE.getImage().scale(10,30);
                 break;
                 case 8:
                 BE.imageName="bulletE3.png";
                 BE.setRotation(45-10*i);
                 getWorld().addObject(BE,getX(),getY()+90);
                 BE.getImage().scale(10,30);
                 break;
                 case 9:
                 BE.imageName="bulletE8.png";
                 getWorld().addObject(BE,getX()-40+80*i,getY()+30);
                 BE.explosiveNumber=10;
                 //BE.getImage().scale(10,30);
                 break;
              }
            }
            timer=0;
        }
        else
        {
            timer++;
        }
    }
    public void selfDectruction()
    {
        java.util.List bombList=getObjectsInRange(290,Enemy.class);
          for (int i=0;bombList!=null&&i<bombList.size();i++)
             {
                Enemy bombTarget=(Enemy)(bombList.get(i));
                bombTarget.health-=150;
                main.addObject(new Effect("explode2",8),bombTarget.getX(),bombTarget.getY());
               }
       Effect nuke=new Effect("explode1",8);
       nuke.sizeScale=3;
       nuke.isFading=false;
       nuke.cloneNumber=10;
       nuke.cloneRange=5;
       main.addObject(nuke,getX(),getY());
       java.util.List bullets=getObjectsInRange(290,bulletE.class);
          for (int i=0;bullets!=null&&i<bullets.size();i++)
             {
                 main.removeObject(((bulletE)bullets.get(i)).body);
                main.removeObject((bulletE)bullets.get(i));
               }
    }
    public void hit()
    {
        if(getY()>main.getHeight()+(getImage().getHeight()/2.0)||health<=0)
        {
            if(type==8||type==9)
            {
                selfDectruction();
            }
            getWorld().removeObject(body);
            getWorld().removeObject(hp);
            getWorld().removeObject(hpBar);
            getWorld().removeObject(this);
        }
    }
    public void act() 
    {
        if (main.isGameRunning&&!main.isPausing)
        {
            movement();
            showHealth();
            shoot();
            hit();
        }
        // Add your action code here.
        
    }    
}
