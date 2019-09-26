import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bulletP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bulletP extends Actor
{
    /**
     * Act - do whatever the bulletP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Player player;
    public float speed;
    public int damage;
    public int shootTimer;
    public int explosiveNumber;
    public Image body;
    public Main main;
    private GreenfootImage bodyImage;
    public bulletP()
    {
        
    }
    protected void addedToWorld(World world)
    {
        body=new Image();
        bodyImage=main.bodyImage;
        body.setRotation(getRotation());
        GreenfootImage image=new GreenfootImage(bodyImage);
        body.setImage(image);
        body.getImage().scale((int)(getImage().getWidth()*0.8),(int)(getImage().getHeight()*0.8));
        getWorld().addObject(body,getX(),getY());
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
    public void hit()
    {
        if (getX()<=290||getX()>=710||getY()-(int)(getImage().getHeight()/2.0)<0||getY()+(int)(getImage().getHeight()/2.0)>main.getHeight())
        {
            getWorld().removeObject(body);
            getWorld().removeObject(this);
        }
        else
        {
           if(!main.getObjects(Boss.class).isEmpty())
           {
               Boss boss=main.getObjects(Boss.class).get(0);
               if(boss!=null&&boss.body!=null&&body.isCollideWith(boss.body))
                   {
                       main.explodeSound();
                       boss.health-=damage;
                       if(boss.health<=0&&!boss.isDead)
                       {
                           player.enemyKilled++;
                           switch (boss.type)
                           {
                                  case 1:
                                  player.score+=10000;
                                  break;
                            }
                            boss.isDead=true;
                       }
                       main.addObject(new Effect("explode1",8),getX()-10+Greenfoot.getRandomNumber(20),getY()-10+Greenfoot.getRandomNumber(20));
                       getWorld().removeObject(body);
                       getWorld().removeObject(this);
               }
               else
               {
                   java.util.List list=main.getObjects(Enemy.class);
                   int key=0;
                    while (list!=null&&key<list.size())
                   {
                       Enemy enemy=(Enemy)(list.get(key));
                       if(body.isCollideWith(enemy.body))
                       {
                           main.explodeSound();
                           main.addObject(new Effect("explode1",8),getX()-10+Greenfoot.getRandomNumber(20),getY()-10+Greenfoot.getRandomNumber(20));
                           enemy.health-=damage;
                           if(enemy.health<=0&&enemy.isDead==false)
                           {
                               main.addObject(new Effect("explode2",8),enemy.getX(),enemy.getY());
                               player.enemyKilled++;
                               switch (enemy.type)
                               {
                                      case 1:
                                      player.score+=100*main.level;
                                      break;
                                      case 2:
                                      player.score+=80*main.level;
                                      break;
                                      case 3:
                                      player.score+=120*main.level;
                                      break;
                                      case 4:
                                      player.score+=150*main.level;
                                      break;
                                      case 5:
                                      player.score+=150*main.level;
                                      break;
                                      case 6:
                                      player.score+=70*main.level;
                                      break;
                                      case 7:
                                      player.score+=140*main.level;
                                      break;
                                      case 8:
                                      player.score+=1000*main.level;
                                      break;
                                      case 9:
                                      player.score+=1200*main.level;
                                      break;
                                    }
                               
                              enemy.isDead=true;
                            }
                           
                           getWorld().removeObject(body);
                           getWorld().removeObject(this);
                           break;
                       }
                       else
                       {
                        key++;
                       }
                }
              }
           }
           else
           {
                   java.util.List list=main.getObjects(Enemy.class);
                   int key=0;
                    while (list!=null&&key<list.size())
                   {
                       Enemy enemy=(Enemy)(list.get(key));
                       if(body.isCollideWith(enemy.body))
                       {
                           main.explodeSound();
                           main.addObject(new Effect("explode1",8),getX()-10+Greenfoot.getRandomNumber(20),getY()-10+Greenfoot.getRandomNumber(20));
                           enemy.health-=damage;
                           if(enemy.health<=0&&enemy.isDead==false)
                           {
                               player.enemyKilled++;
                               switch (enemy.type)
                               {
                                      case 1:
                                      player.score+=100*main.level;
                                      break;
                                      case 2:
                                      player.score+=80*main.level;
                                      break;
                                      case 3:
                                      player.score+=120*main.level;
                                      break;
                                      case 4:
                                      player.score+=150*main.level;
                                      break;
                                      case 5:
                                      player.score+=150*main.level;
                                      break;
                                      case 6:
                                      player.score+=70*main.level;
                                      break;
                                      case 7:
                                      player.score+=140*main.level;
                                      break;
                                      case 8:
                                      player.score+=1000*main.level;
                                      break;
                                      case 9:
                                      player.score+=1200*main.level;
                                      break;
                                }
                                enemy.isDead=true;
                           }
                           getWorld().removeObject(body);
                           getWorld().removeObject(this);
                           break;
                       }
                       else
                       {
                        key++;
                       }
                   }
            }
       }
    }
    // public void isCollide(Actor A, Actor B)
    // {
        // int distance;
        // if(Math.sqrt((A.getX()-B.getX())^2+(A.getY()-B.getY())^2))
        // {
            
        // }
    // }
    // public Actor[] collideWith(Actor actor,Class cls)
    // {
        // int key=0;
        // int collideNumber=0;
        // Actor[] returnActors=new Actor[100];
        // while (world.getObjects(cls).get(key)!=null)
        // {
            // if(intersects((Actor)world.getObjects(cls).get(key)))
            // {
                
            // }
            // key++;
        // }
        // return returnActors;
    // }
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
