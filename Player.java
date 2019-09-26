import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean isDead=false;
    public int type;
    public int controltype;
    public int speed;
    public Main main;
    private int[] bulletspeed={5,8,7,5, 7,3,5,7,9};
    private int[] shootTimer= {4,3,4,6, 7,4,5,5,10};
    private int[] shootNumber={1,1,2,1, 4,1,3,2,1};
    private int[] damage=    {11,7,5,15,5,8,5,5,25};
    private int timer=0;
    public float maxHealth=500.0f;
    public float health;
    public int lives=3;
    public int score=0;
    public int enemyKilled=0;
    private float[] modeMaxTime={700,700,500,300,300,300};
    private float[] modeTimer={0,0,0,0,0,0};
    public boolean[] isModeOn={false,false,false,false,false,false};
    private int lastX;
    private int lastY;
    private Image hp;
    private Image hpBar;
    public Image Pbody;
    private Image liveIcon;
    private Image bulletIcon;
    private Image bulletIcon2;
    private Image shieldIcon;
    private Image wrenchIcon;
    public Player()
    {
        health=maxHealth;
        timeUp.setVolume(87);
    }
    protected void addedToWorld(World world)
    {
        liveIcon=new Image();
        liveIcon.setImage("heart.png");
        liveIcon.getImage().scale(40,40);
        bulletIcon=new Image();
        bulletIcon.setImage("big bullet.png");
        bulletIcon.getImage().scale(40,40);
        bulletIcon2=new Image();
        bulletIcon2.setImage("big bullet2.png");
        bulletIcon2.getImage().scale(40,40);
        wrenchIcon=new Image();
        wrenchIcon.setImage("wrench.png");
        wrenchIcon.getImage().scale(40,40);
        shieldIcon=new Image();
        shieldIcon.setImage("shieldIcon.png");
        shieldIcon.getImage().scale(40,40);
        shieldIcon.getImage().setTransparency(200);
        Pbody=new Image();
        Pbody.setImage(new GreenfootImage(main.bodyImage));
        Pbody.getImage().scale((int)(getImage().getWidth()*0.7),(int)(getImage().getHeight()*0.7));
        world.addObject(Pbody,getX(),getY());
        hpBar=new Image();
        hp=new Image();
        hpBar.setImage("HPbar.png");
        hpBar.getImage().scale(2*getImage().getWidth(),14);
        world.addObject(hpBar,getX(),getY()+(int)(getImage().getHeight()/2)+20);
        hp.setImage("green.png");
        hp.getImage().scale(hpBar.getImage().getWidth(),10);
        world.addObject(hp,hpBar.getX(),hpBar.getY());
    }
    public void turnOnMode(int mode)
    {    
        modeTimer[mode-1]=modeMaxTime[mode-1];
        isModeOn[mode-1]=true;
    }
    //mode=1: add shield
    public boolean isShieldOn=false;
    private Image shield;
    public Image shieldBody;
    public float shieldHealth;
    public float shieldMaxHealth=300.0f;
    private Image shieldHp;
    private Image shieldHpBar;
    private void shieldUpdate()
    {
        if(shield==null)
        {
            shieldHealth=shieldMaxHealth;
            shield=new Image();
            shield.setImage("shield.png");
            shield.getImage().scale(70,70);
            main.addObject(shield,getX(),getY());
            hpBar.getImage().setTransparency(0);
            hp.getImage().setTransparency(0);
            shieldBody=new Image();
            shieldBody.setImage(new GreenfootImage(main.bodyImage));
            shieldBody.getImage().scale((int)(shield.getImage().getWidth()*0.7),(int)(shield.getImage().getHeight()*0.7));
            main.addObject(shieldBody,shield.getX(),shield.getY());
            shieldHpBar=new Image();
            shieldHp=new Image();
            shieldHpBar.setImage("HPbar.png");
            shieldHpBar.getImage().scale((int)(1.5*shield.getImage().getWidth()),14);
            main.addObject(shieldHpBar,shield.getX(),shield.getY()+(int)(shield.getImage().getHeight()/2)+20);
            shieldHp.setImage("blue.png");
            shieldHp.getImage().scale(shieldHpBar.getImage().getWidth()-2,10);
            main.addObject(shieldHp,shieldHpBar.getX(),shieldHpBar.getY());
        }
        else
        {
            if(shieldHealth>0&&(shieldHealth/shieldMaxHealth)*shieldHpBar.getImage().getWidth()-2>1)
            {
                shield.setLocation(getX(),getY());
                shieldBody.setLocation(shield.getX(),shield.getY());
                shieldHpBar.setLocation(shield.getX(),shield.getY()+(int)(shield.getImage().getHeight()/2)+20);
                shieldHp.getImage().scale((int)((shieldHealth/shieldMaxHealth)*shieldHpBar.getImage().getWidth())-2,10);
                shieldHp.setLocation((int)(shieldHpBar.getX()-(int)(shieldHpBar.getImage().getWidth()/2)+shieldHp.getImage().getWidth()/2)+1,shieldHpBar.getY());
            }
            else
            {
                main.removeObject(shieldHp);
                main.removeObject(shieldHpBar);
                main.removeObject(shield);
                main.removeObject(shieldBody);
                shield=null;
                hpBar.getImage().setTransparency(255);
                hp.getImage().setTransparency(255);
                isShieldOn=false;
            }
        }
    }
    private void showHealth()
    {
        if(!isShieldOn)
        {
            hpBar.setLocation(getX(),getY()+(int)(getImage().getHeight()/2)+20);
            if ((health/maxHealth)<=1.0&&health/maxHealth>=2.0/3.0)
            {
            hp.setImage("green.png");
            }
             if (health/maxHealth<2.0/3.0&&health/maxHealth>=1.0/3.0)
            {
            hp.setImage("yellow.png");
            }
            if (health/maxHealth<1.0/3.0&&health/maxHealth>0.0)
            {
            hp.setImage("red.png");
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
        if(!isDead)
        {
            main.addObject(liveIcon,getX()-40,getY()+70);
            liveIcon.setLocation(getX()-40,getY()+70);
            main.showText("",lastX-10,lastY+70);
            main.showText(""+lives,getX()-10,getY()+70);
            lastX=getX();
            lastY=getY();
        }
    }
    private void movement()
    {
        Pbody.setLocation(getX(),getY());
        switch (controltype){
            case 1:
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse!=null)
            {
                int x=mouse.getX();
                int y=mouse.getY();
                // getWorld().showText("x:"+x+"  y:"+y,80,30);
                if (x>=300&&x<=700)
                {
                setLocation(x,y);
                }
                if (x>700)
                {
                setLocation(700,y);
                }
                if (x<300)
                {
                setLocation(300,y);
                }
                
                
            }
            break;
            case 2:
            if(Greenfoot.isKeyDown("Shift"))
           {
                    if(Greenfoot.isKeyDown("W")&&getY()>=0)
               {
                   setLocation(getX(),getY()-speed/2);
                }
                 if(Greenfoot.isKeyDown("A")&&getX()>=300)
               {
                   setLocation(getX()-speed/2,getY());
                }
                 if(Greenfoot.isKeyDown("S")&&getY()<=main.getHeight())
               {
                   setLocation(getX(),getY()+speed/2);
                }
                 if(Greenfoot.isKeyDown("D")&&getX()<=700)
               {
                   setLocation(getX()+speed/2,getY());
                }
               
            }
            else
            {
                if(Greenfoot.isKeyDown("W")&&getY()>=0)
               {
                   setLocation(getX(),getY()-speed);
                }
                 if(Greenfoot.isKeyDown("A")&&getX()>=300)
               {
                   setLocation(getX()-speed,getY());
                }
                 if(Greenfoot.isKeyDown("S")&&getY()<=main.getHeight())
               {
                   setLocation(getX(),getY()+speed);
                }
                 if(Greenfoot.isKeyDown("D")&&getX()<=700)
               {
                   setLocation(getX()+speed,getY());
                }
            }
             break;
        }
    }
    public void shoot(int scale)
    {
        if (timer>=shootTimer[type-1])
        {
            updateBodyImage();
            if(isModeOn[1])
            {
                main.shootsound[3].play();
                for (int i=0;i<3*shootNumber[type-1];i++)
                {
                bulletP bullet=new bulletP();
                bullet.main=main;
                bullet.player=this;
                bullet.damage=damage[type-1];
                bullet.speed=bulletspeed[type-1]*2.5f;
                if(isModeOn[0])
                {
                    bullet.damage=damage[type-1]*3;
                    bullet.speed*=1.2f;
                }
                 switch (type)
                 {
                     case 1:
                     bullet.setImage("bullet3.png");
                     bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                     getWorld().addObject(bullet,getX()-1,getY()-17);
                     break;
                     case 2:
                     bullet.setImage("bullet4.png");
                     bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                     getWorld().addObject(bullet,getX()-1,getY()-17);
                     break;
                     case 3:
                     bullet.setImage("bullet2.png");
                     if(i<3)
                     {
                         bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                         getWorld().addObject(bullet,getX()+18,getY()-18);
                     }
                     if (i>=3)
                     {
                         bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                         getWorld().addObject(bullet,getX()-18,getY()-18);
                      }
                     break;
                     case 4:
                     bullet.setImage("bullet1.png");
                     bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                     getWorld().addObject(bullet,getX()-1,getY()-9);
                     break;
                     case 5:
                     bullet.setImage("bullet5.png");
                     if(i<3)
                     {
                         bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                         getWorld().addObject(bullet,getX()+15,getY()-15);
                      }
                      if(i>=3&&i<6)
                     {
                         bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                         getWorld().addObject(bullet,getX()-17,getY()-15);
                     }
                     break;
                     case 6:
                     bullet.setImage("bullet2.png");
                     bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                     getWorld().addObject(bullet,getX()-1,getY()-18);
                     break;
                     case 7:
                     bullet.setImage("bullet7.png");
                     bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                     getWorld().addObject(bullet,getX()-1,getY()-18);
                     break;
                     case 8:
                     bullet.setImage("bullet6.png");
                     if(i<3)
                     {
                         bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                         getWorld().addObject(bullet,getX()+14,getY()-21);
                      }
                      if(i>03&&i<6)
                     {
                         bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                         getWorld().addObject(bullet,getX()-16,getY()-21);
                     }
                     break;
                     case 9:
                     bullet.setImage("bullet8.png");
                     bullet.setRotation(60*(i%3)+((i%3)-1)*(-3)*((int)modeTimer[1]%20)-60);
                     getWorld().addObject(bullet,getX()-1,getY()-19);
                     break;
                  }
                }
            }
            else
            {
                for (int i=0;i<shootNumber[type-1];i++)
                {
                bulletP bullet=new bulletP();
                bullet.main=main;
                bullet.player=this;
                bullet.damage=damage[type-1];
                
                if(isModeOn[0])
                {
                    bullet.damage=damage[type-1]*3;
                    bullet.speed=bulletspeed[type-1]*3;
                }
                else
                {
                    bullet.speed=bulletspeed[type-1];
                }
                
                 switch (type)
                 {
                     case 1:
                     main.shootsound[0].play();
                     bullet.setImage("bullet3.png");
                     getWorld().addObject(bullet,getX()-1,getY()-17);
                     break;
                     case 2:
                     main.shootsound[1].play();
                     bullet.setImage("bullet4.png");
                     getWorld().addObject(bullet,getX()-1,getY()-17);
                     break;
                     case 3:
                     main.shootsound[1].play();
                     bullet.setImage("bullet2.png");
                     switch (i)
                     {
                         case 0:
                         getWorld().addObject(bullet,getX()+18,getY()-18);
                         break;
                         case 1:
                         getWorld().addObject(bullet,getX()-18,getY()-18);
                         break;
                     }
                     break;
                     case 4:
                     main.shootsound[0].play();
                     bullet.setImage("bullet1.png");
                     getWorld().addObject(bullet,getX()-1,getY()-9);
                     break;
                     case 5:
                     main.shootsound[1].play();
                     bullet.setImage("bullet5.png");
                     switch (i)
                     {
                         case 0:
                         bullet.setRotation(15);
                         getWorld().addObject(bullet,getX()+15,getY()-15);
                         break;
                         case 1:
                         bullet.setRotation(-15);
                         getWorld().addObject(bullet,getX()+15,getY()-15);
                         break;
                         case 2:
                         bullet.setRotation(15);
                         getWorld().addObject(bullet,getX()-17,getY()-15);
                         break;
                         case 3:
                         bullet.setRotation(-15);
                         getWorld().addObject(bullet,getX()-17,getY()-15);
                         break;
                     }
                     break;
                     case 6:
                     main.shootsound[2].play();
                     bullet.setImage("bullet2.png");
                     getWorld().addObject(bullet,getX()-1,getY()-18);
                     break;
                     case 7:
                     main.shootsound[1].play();
                     bullet.setImage("bullet7.png");
                     switch (i)
                     {
                         case 0:
                         bullet.setRotation(15);
                         getWorld().addObject(bullet,getX()-1,getY()-18);
                         break;
                         case 1:
                         bullet.setRotation(-15);
                         getWorld().addObject(bullet,getX()-1,getY()-18);
                         break;
                         case 2:
                         getWorld().addObject(bullet,getX()-1,getY()-18);
                         break;
                     }
                     break;
                     case 8:
                     main.shootsound[1].play();
                     bullet.setImage("bullet6.png");
                     switch (i)
                     {
                         case 0:
                         getWorld().addObject(bullet,getX()+14,getY()-21);
                         break;
                         case 1:
                         getWorld().addObject(bullet,getX()-16,getY()-21);
                         break;
                     }
                     break;
                     case 9:
                     main.shootsound[2].play();
                     bullet.setImage("bullet8.png");
                     getWorld().addObject(bullet,getX()-1,getY()-19);
                     break;
                  }
                }
            }
            timer=0;
        }
        else
        {
            timer+=scale;
        }
    }
    private void hit()
    {
        int key=0;
        while (key<main.getObjects(Enemy.class).size())
       {
           Enemy enemy=(Enemy)(getWorld().getObjects(Enemy.class).get(key));
           if(Pbody.isCollideWith(enemy.body))
           {
               main.explodeSound();
               if(!isShieldOn)
               {
                   enemy.health-=5;
                   health-=5;
               }
               else
               {
                   enemy.health-=5;
                   shieldHealth-=5;
               }
               if(main.timer%10==0)
               {
                     Effect effect=new Effect("explode2",8);
                     effect.transparency=150;
                     main.addObject(effect,(int)((getX()+enemy.getX())/2.0),(int)((getY()+enemy.getY())/2.0));
               }
               if(enemy.health<=0&&enemy.isDead==false)
                   {
                       enemyKilled++;
                       switch (enemy.type)
                       {
                              case 1:
                              score+=100*main.level;
                              break;
                              case 2:
                              score+=80*main.level;
                              break;
                              case 3:
                              score+=120*main.level;
                              break;
                              case 4:
                              score+=150*main.level;
                              break;
                              case 5:
                              score+=150*main.level;
                              break;
                              case 6:
                              score+=70*main.level;
                              break;
                              case 7:
                              score+=140*main.level;
                              break;
                              case 8:
                              score+=1000*main.level;
                              break;
                              case 9:
                              score+=1200*main.level;
                              break;
                        }
                        enemy.isDead=true;
                   }
           }
            key++;
       }
       Boss boss=main.boss;
       if(boss!=null&&boss.body!=null&&Pbody.isCollideWith(boss.body))
                   {
                       main.explodeSound();
                       if(!isShieldOn)
                       {
                           boss.health-=5;
                           health-=5;
                       }
                       else
                       {
                           boss.health-=5;
                           shieldHealth-=5;
                       }
                       if(boss.health<=0&&!boss.isDead)
                       {
                           enemyKilled++;
                           switch (boss.type)
                           {
                                  case 1:
                                  score+=10000;
                                  break;
                            }
                            boss.isDead=true;
                       }
                       if(main.timer%10==0)
                      {   
                           Effect effect=new Effect("explode2",8);
                           effect.transparency=150;
                           main.addObject(effect,(int)((getX()+boss.getX())/2.0),(int)((getY()+boss.getY())/2.0));
                       }
               }
       if(health<=0)
       {
           lives--;
           Effect effect=new Effect("explode1",8);
           effect.sizeScale=3;
           main.addObject(effect,getX(),getY());
           if (lives<=0)
           {
               isDead=true;
               setLocation(0,0);
               main.removeObject(hp);
               main.removeObject(hpBar);
               main.removeObject(liveIcon);
               main.removeObject(bulletIcon);
               main.removeObject(bulletIcon2);
               main.removeObject(wrenchIcon);
               main.showText("",lastX-10,lastY+70);
               main.showText("",getX()-10,getY()+70);
            }
            else
            {
               health=maxHealth;
            }
       }
    }
    public void updateBodyImage()
    {
        Pbody.setImage(new GreenfootImage(main.bodyImage));
        Pbody.getImage().scale((int)(getImage().getWidth()*0.7),(int)(getImage().getHeight()*0.7));
    }
    private GreenfootSound timeUp=new GreenfootSound("itemTimeUp.wav");
    public void modeUpdate()
    {
        for (int i=0;i<modeTimer.length;i++)
            {
                if (isModeOn[i]&&modeTimer[i]>0)
                {
                    modeTimer[i]--;
                    if(modeTimer[i]==100)
                    {
                        timeUp.play();
                    }
                }
                else
                {
                    isModeOn[i]=false;
                }
            }
        if(isShieldOn)
        {
            shieldUpdate();
            shieldBody.setImage(new GreenfootImage(main.bodyImage));
            shieldBody.getImage().scale((int)(getImage().getWidth()*0.7),(int)(getImage().getHeight()*0.7));
            shieldIcon.getImage().setTransparency(130);
            main.addObject(shieldIcon,getX()-40,getY()-40);
            shieldIcon.setLocation(getX()-40,getY()-40);
        }
        else
        {
            main.removeObject(shieldIcon);
        }
        if (isModeOn[0])
        {
             main.addObject(bulletIcon,getX()+40,getY()-40);
             bulletIcon.setLocation(getX()+40,getY()-40);
             bulletIcon.getImage().setTransparency((int)(255*modeTimer[0]/modeMaxTime[0]));
        }
        else
        {
            main.removeObject(bulletIcon);
        }
        if (isModeOn[1])
        {
             main.addObject(bulletIcon2,getX()+40,getY());
             bulletIcon2.setLocation(getX()+40,getY());
             bulletIcon2.getImage().setTransparency((int)(255*modeTimer[1]/modeMaxTime[1]));
        }
        else
        {
            main.removeObject(bulletIcon2);
        }
        if (isModeOn[2])
        {
             main.addObject(wrenchIcon,getX()-40,getY());
             wrenchIcon.setLocation(getX()-40,getY());
             wrenchIcon.getImage().setTransparency((int)(255*modeTimer[2]/modeMaxTime[2]));
             if(isShieldOn&&shieldHealth<shieldMaxHealth)
             {
                 shieldHealth++;
             }
             else
             {
                 if(health<maxHealth)
                 {
                     health++;
                 }
             }
        }
        else
        {
            main.removeObject(wrenchIcon);
        }
    }
    
    public void act() 
    {
        if (main.isGameRunning&&!main.isPausing&&!isDead)
        {
            modeUpdate();
            movement();
            showHealth();
            if (isModeOn[1])
            {
                shoot(2);
            }
            else
            {
                 shoot(1);
            }
            hit();
        }
        // Add your action code here.
    }    
}
