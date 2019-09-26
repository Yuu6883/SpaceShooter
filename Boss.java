import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor
{
    public Main main;
    public Image body;
    public float maxHealth;
    public float health;
    public int speed;
    private float[] bulletspeed={3,7,4,1.5f,2,7,5,7,7};
    private int[] shootTimer={10,35,10,10,50,4,5,5,5};
    private int[] shootNumber={2,5,10,3,2,1,3,2,1};
    private int[] damage= {20,25,20,30,80,5,5,5,8};
    private float shootTime=0;
    public float healthScale;
    private Image hp;
    private Image hpBar;
    private GreenfootImage bodyImage;
    private Effect bossImage;
    public boolean isDead=false;
    private int mode;
    public int type;
    private GreenfootSound alarm=new GreenfootSound("alarm.wav");
    private GreenfootSound explode=new GreenfootSound("bossexplode.wav");
    public Boss()
    {
        
    }
    protected void addedToWorld(World world)
    {
        alarm.play();
        switch (type)
        {
            case 1:
            bossImage=new Effect("boss1",4);
            bossImage.fadeInTime=50;
            if(main.player!=null)
            {
                 maxHealth=8000*main.player.length*healthScale;
             }
             else
             {
                 maxHealth=10000000;
             }
            mode=0;
            break;
        }
        bossImage.isSelfDestructive=false;
        bossImage.isFading=false;
        world.addObject(bossImage,getX(),getY());
        bodyImage=main.bodyImage;
        body=new Image();
        body.setImage(new GreenfootImage(bodyImage));
        body.getImage().scale((int)(bossImage.getImage().getWidth()*0.8),(int)(bossImage.getImage().getHeight()*0.8));
        world.addObject(body,getX(),getY());
        health=maxHealth;
        hpBar=new Image();
        hp=new Image();
        hpBar.setImage("HPbar.png");
        hpBar.getImage().scale(bossImage.getImage().getWidth(),14);
        world.addObject(hpBar,getX(),getY()-(int)(bossImage.getImage().getHeight()/2)-20);
        hp.setImage("green.png");
        hp.getImage().scale(hpBar.getImage().getWidth(),10);
        world.addObject(hp,hpBar.getX(),hpBar.getY());
    }
    private void showHealth()
    {
        hpBar.setLocation(getX(),getY()-(int)(bossImage.getImage().getHeight()/2)-20);
        if ((health/maxHealth)<=1.0&&health/maxHealth>=2.0/3.0)
        {
        hp.setImage("green.png");
        }
         if (health/maxHealth<2.0/3.0&&health/maxHealth>=1.0/3.0)
        {
            hp.setImage("yellow.png");
             if(main.timer%15==0)
             {
                 Effect effect=new Effect("explode2",8);
                 main.addObject(effect,getX()-(int)(body.getImage().getWidth()/2.0)+Greenfoot.getRandomNumber(body.getImage().getWidth()),getY()-(int)(body.getImage().getHeight()/2.0)+Greenfoot.getRandomNumber(body.getImage().getHeight()));
              }
            
        }
        if (health/maxHealth<1.0/3.0&&(health/maxHealth)*hpBar.getImage().getWidth()>3.0)
        {
           hp.setImage("red.png");
           if(main.timer%7==0)
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
    public void movement(int moveMode)
    {
        switch(moveMode)
        {
            case 0:
            if(timer%5==0)
            {
                if(getX()>500)
                {
                    setLocation(getX()-1,getY());
                }
                if(getX()<500)
                {
                    setLocation(getX()+1,getY());
                }
                if(main.timer%300>=150)
                {
                    setLocation(getX(),getY()-1);
                }
                else
                {
                    setLocation(getX(),getY()+1);
                }
            }
            break;
            case 1:
            if(timer%2==0)
            {
                if(getY()>150)
                {
                    setLocation(getX(),getY()-1);
                }
                if(getY()<150)
                {
                    setLocation(getX(),getY()+1);
                }
                if(timer%300>=150)
                {
                    setLocation(getX()+1,getY());
                }
                if(timer%300<150)
                {
                    setLocation(getX()-1,getY());
                }
            }
            break;
            case 2:
            if(timer%2==0)
            {
               if(getY()>150)
                {
                    setLocation(getX(),getY()-1);
                }
                if(getY()<150)
                {
                    setLocation(getX(),getY()+1);
                }
                if(timer%500>=250)
                {
                    setLocation(getX()+1,getY());
                }
                if(timer%500<250)
                {
                    setLocation(getX()-1,getY());
                }
            }
            break;
            case 3:
            if(timer%2==0)
            {
                 if(getY()>150)
                {
                    setLocation(getX(),getY()-1);
                }
                if(getY()<150)
                {
                    setLocation(getX(),getY()+1);
                }
                if(timer%300>=150)
                {
                    setLocation(getX()+1,getY());
                }
                if(timer%300<150)
                {
                    setLocation(getX()-1,getY());
                }
            }
            break;
            case 4:
            if(timer%5==0)
            {
                if(getX()>500)
                {
                    setLocation(getX()-1,getY());
                }
                if(getX()<500)
                {
                    setLocation(getX()+1,getY());
                }
                if(main.timer%300>=150)
                {
                    setLocation(getX(),getY()-1);
                }
                else
                {
                    setLocation(getX(),getY()+1);
                }
            }
            break;
        }
        bossImage.setLocation(getX(),getY());
        body.setLocation(getX(),getY());
    }
    public void shoot(float scale)
    {   
        if (shootTime>=shootTimer[mode])
        {
            int r=Greenfoot.getRandomNumber(60);
            for (int i=0;i<shootNumber[mode];i++)
            {
            bulletE BE=new bulletE();
            BE.main=main;
            BE.speed=bulletspeed[mode];
            BE.damage=damage[mode]*(float)(0.5+0.5*main.level);
             switch (mode)
             {
                 case 0:
                 BE.imageName="bulletE4.png";
                 switch (i)
                 {
                     case 0:
                     BE.setRotation(Greenfoot.getRandomNumber(60)-10);
                     main.addObject(BE,getX()-57,getY()+56);
                     break;
                     case 1:
                     BE.setRotation(Greenfoot.getRandomNumber(60)-50);
                     main.addObject(BE,getX()+57,getY()+56);
                     break;
                 }
                 break;
                 case 1:
                 BE.imageName="bulletE2.png";
                 BE.setRotation(-60+15*i+r);
                 switch (i)
                 {
                     case 0:
                     getWorld().addObject(BE,getX()-175,getY()+80);
                     break;
                     case 1:
                     getWorld().addObject(BE,getX()-112,getY()+70);
                     break;
                     case 2:
                     getWorld().addObject(BE,getX(),getY()+45);
                     break;
                     case 3:
                     getWorld().addObject(BE,getX()+112,getY()+70);
                     break;
                     case 4:
                     getWorld().addObject(BE,getX()+175,getY()+80);
                     break;
                 }
                 
                 break;
                 case 2:
                 BE.imageName="bulletE7.png";
                 BE.setRotation(-50+10*i);
                 getWorld().addObject(BE,getX(),getY()+75);
                 break;
                 case 3:
                 BE.imageName="bulletE9.png";
                 BE.setRotation(-30*i+timer%58);
                 getWorld().addObject(BE,getX(),getY()+75);
                 break;
                 case 4:
                 BE.imageName="bulletE10.png";
                 BE.explosiveNumber=8;
                 
                 switch (i)
                 {
                     case 0:
                     BE.setRotation(Greenfoot.getRandomNumber(60)-10);
                     main.addObject(BE,getX()-57,getY()+56);
                     break;
                     case 1:
                     BE.setRotation(Greenfoot.getRandomNumber(60)-50);
                     main.addObject(BE,getX()+57,getY()+56);
                     break;
                 }
              }
            }
            shootTime=0;
        }
        else
        {
            shootTime+=scale;
        }
    }
    private void selfDestruction()
    {
        if(timer%10==0)
        {
            Effect effect=new Effect("explode2",8);
                effect.sizeScale=3;
                effect.cloneNumber=5;
                effect.cloneRange=2;
                main.addObject(effect,getX()-(int)(bossImage.getImage().getWidth()/2.0)+Greenfoot.getRandomNumber(bossImage.getImage().getWidth()),getY()-(int)(bossImage.getImage().getHeight()/2.0)+Greenfoot.getRandomNumber(bossImage.getImage().getHeight()));
            bossImage.transparency-=20;
            if(bossImage.transparency<20)
            {
                 main.removeObject(bossImage);
                 main.boss=null;
                 main.removeObject(this);
             }
    }
    }

    private int timer=0;
    public void act() 
    {
        
        if (main.isGameRunning&&!main.isPausing)
        {
            if (health>0)
            {
                if(timer%1000==0)
                {
                    int currentMode=mode;
                    mode=Greenfoot.getRandomNumber(5);
                    while(mode==currentMode)
                    {
                        mode=Greenfoot.getRandomNumber(5);
                    }
                }
                showHealth();
                movement(mode);
                if(timer>200)
                {
                    if (health>2500)
                    {
                        if(mode==3&&timer%1000<800)
                        {
                            shoot(0.5f);
                        }
                        else
                        {
                           if(mode!=3&&timer%1000<800)
                           {
                              shoot(1);
                          }
                       }
                    }
                    else
                    {
                        if (mode!=3)
                        {
                             shoot(3);
                        }
                        else
                        {
                            shoot(1);
                        }
                    }
                }
            }
            else
            {
                explode.play();
                if(body!=null)
                {
                     main.removeObject(body);
                     body=null;
                }
                if(hp!=null)
                {
                    main.removeObject(hp);
                    hp=null;
                }
                if(hpBar!=null)
                {
                    main.removeObject(hpBar);
                    hpBar=null;
                }
                selfDestruction();
            }
            timer++;
        } 
    }
}
