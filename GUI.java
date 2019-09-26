import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI extends Actor
{
    /**
     * Act - do whatever the GUI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Image choosePlayerButton1;
    private Image choosePlayerButton2;
    private Image shipIcon1;
    private Image shipIcon2;
    private Image chooseModeButton1;
    private Image chooseModeButton2;
    private Image playButton;
    private Image[] image;
    private Image[] gif;
    public Main world;
    private GreenfootSound beap=new GreenfootSound("button.wav");
    private GreenfootSound blastoff=new GreenfootSound("blastoff.wav");
    private GreenfootSound play=new GreenfootSound("play.wav");
    public void addButton(String buttonName, int x, int y)
    {
        boolean isAdded=false;
        if (buttonName=="choosePlayer1")
        {
            choosePlayerButton1=new Image();
            choosePlayerButton1.setImage("button.png");
            choosePlayerButton1.getImage().scale(100,100);
            getWorld().addObject(choosePlayerButton1,x ,y);
            getWorld().showText("1",x ,y);
            isAdded=true;
        }
        if (buttonName=="choosePlayer2")
        {
            choosePlayerButton2=new Image();
            choosePlayerButton2.setImage("button.png");
            choosePlayerButton2.getImage().scale(100,100);
            getWorld().addObject(choosePlayerButton2,x ,y);
            getWorld().showText("2",x ,y);
            isAdded=true;
        }
        if (buttonName=="shipImage1")
        {
            shipIcon1=new Image();
            shipIcon1.setImage("spaceship"+world.player[0].type+".png");
            shipIcon1.getImage().scale(100,100);
            getWorld().addObject(shipIcon1,x ,y);
            isAdded=true;
        }
        if (buttonName=="shipImage2")
        {
            shipIcon2=new Image();
            shipIcon2.setImage("spaceship"+world.player[1].type+".png");
            shipIcon2.getImage().scale(100,100);
            getWorld().addObject(shipIcon2,x ,y);
            isAdded=true;
        }
        if (buttonName=="chooseModeButton1")
        {
            chooseModeButton1=new Image();
            chooseModeButton1.setImage("button.png");
            chooseModeButton1.getImage().scale(200,100);
            getWorld().addObject(chooseModeButton1,x ,y);
            getWorld().showText("Mouse Control",x ,y);
            isAdded=true;
        }
        if (buttonName=="chooseModeButton2")
        {
            chooseModeButton2=new Image();
            chooseModeButton2.setImage("button.png");
            chooseModeButton2.getImage().scale(400,100);
            getWorld().addObject(chooseModeButton2,x ,y);
            getWorld().showText("Mouse Control       /       Keyboard Control",x ,y);
            isAdded=true;
        }
        if (buttonName=="playButton")
        {
            playButton=new Image();
            playButton.setImage("button.png");
            playButton.getImage().scale(300,100);
            getWorld().addObject(playButton, x ,y);
            getWorld().showText("Play",x ,y);
            isAdded=true;
        }
        if (!isAdded)
        {
        world.error();
        }

    }
    public void removeButton(String buttonName)
    {
        boolean isRemoved=false;
        if (buttonName=="choosePlayer1"&&choosePlayerButton1!=null)
        {
        showText("",choosePlayerButton1.getX(), choosePlayerButton1.getY());
        getWorld().removeObject(choosePlayerButton1);
        choosePlayerButton1=null;
        isRemoved=true;
        }
        if (buttonName=="choosePlayer2"&&choosePlayerButton2!=null)
        {
        showText("",choosePlayerButton2.getX(), choosePlayerButton2.getY());
        getWorld().removeObject(choosePlayerButton2);
        choosePlayerButton2=null;
        isRemoved=true;
        }
        if (buttonName=="shipImage1"&&shipIcon1!=null)
        {
        getWorld().removeObject(shipIcon1);
        shipIcon1=null;
        isRemoved=true;
        }
        if (buttonName=="shipImage2"&&shipIcon2!=null)
        {
        getWorld().removeObject(shipIcon2);
        shipIcon2=null;
        isRemoved=true;
        }
        if (buttonName=="chooseModeButton1"&&chooseModeButton1!=null)
        {
        showText("",chooseModeButton1.getX(), chooseModeButton1.getY());
        getWorld().removeObject(chooseModeButton1);
        chooseModeButton1=null;
        isRemoved=true;
        }
        if (buttonName=="chooseModeButton2"&&chooseModeButton2!=null)
        {
        showText("",chooseModeButton2.getX(), chooseModeButton2.getY());
        getWorld().removeObject(chooseModeButton2);
        chooseModeButton2=null;
        isRemoved=true;
        }
        if (buttonName=="playButton"&&playButton!=null)
        {
        showText("",playButton.getX(), playButton.getY());
        getWorld().removeObject(playButton);
        playButton=null;
        isRemoved=true;
        }
        if (!isRemoved)
        {
        world.error();
        }
    }
    public void showText(String text, int x, int y)
    {
        getWorld().showText(text, x ,y);
    }
    public void choosePlayerButton1Behavior()
    {
        if(choosePlayerButton1!=null)
        {
            if (choosePlayerButton1.mouseOn())
            {
            choosePlayerButton1.getImage().setTransparency(150);
            }
            else
            {
            choosePlayerButton1.getImage().setTransparency(255);
            }
            if (choosePlayerButton1.click())
            {
                beap.play();
                world.player=new Player[1];
                world.player[0]=new Player();
                world.player[0].type=Greenfoot.getRandomNumber(8)+1;
                world.player[0].controltype=1;
                showText("Choose Your Ship", 500, 200);
                removeButton("choosePlayer1");
                removeButton("choosePlayer2");
                addButton("shipImage1", 500, 400);
                showText("Player 1", 500, 330);
                addButton("chooseModeButton1", 500, 520);
                addButton("playButton", 500, 700);
            }
        }
    }
    public void choosePlayerButton2Behavior()
    {
        if(choosePlayerButton2!=null)
        {
            if (choosePlayerButton2.mouseOn())
            {
            choosePlayerButton2.getImage().setTransparency(150);
            }
            else
            {
            choosePlayerButton2.getImage().setTransparency(255);
            }
            if (choosePlayerButton2.click())
            {
                beap.play();
            world.player=new Player[2];
            world.player[0]=new Player();
            world.player[1]=new Player();
            world.player[0].type=Greenfoot.getRandomNumber(8)+1;
            world.player[1].type=Greenfoot.getRandomNumber(8)+1;
            world.player[0].controltype=1;
            world.player[1].controltype=2;
            showText("Choose Your Ship", 500, 200);
            removeButton("choosePlayer1");
            removeButton("choosePlayer2");
            addButton("shipImage1", 400, 400);
            addButton("shipImage2", 600, 400);
            showText("Player 1", 400, 330);
            showText("Player 2", 600, 330);
            addButton("chooseModeButton2", 500, 520);
            addButton("playButton", 500, 700);
            }
        }
    }
    public void shipImage1Behavior()
    {
        if(shipIcon1!=null)
        {
            if (shipIcon1.mouseOn())
            {
            shipIcon1.turn(1);
            }
            else
            {
            shipIcon1.setRotation(0);
            }
            if (shipIcon1.click())
            {
                beap.play();
                if (world.player[0].type<9)
                {
                 world.player[0].type++;
                 shipIcon1.setImage("spaceship"+world.player[0].type+".png");
                 shipIcon1.getImage().scale(100,100);
                }
                else
                {
                 world.player[0].type=1;
                 shipIcon1.setImage("spaceship"+world.player[0].type+".png");
                 shipIcon1.getImage().scale(100,100);
                }
            }
        }
    }
    public void shipImage2Behavior()
    {
        if(shipIcon2!=null)
        {
            if (shipIcon2.mouseOn())
            {
            shipIcon2.turn(1);
            }
            else
            {
            shipIcon2.setRotation(0);
            }
            if (shipIcon2.click())
            {
                beap.play();
                if (world.player[1].type<9)
                {
                 world.player[1].type++;
                 shipIcon2.setImage("spaceship"+world.player[1].type+".png");
                 shipIcon2.getImage().scale(100,100);
                }
                else
                {
                 world.player[1].type=1;
                 shipIcon2.setImage("spaceship"+world.player[1].type+".png");
                 shipIcon2.getImage().scale(100,100);
                }
            }
        }
    }
    public void chooseModeButton1Behavior()
    {
        if(chooseModeButton1!=null)
        {
            if (chooseModeButton1.mouseOn())
            {
            chooseModeButton1.getImage().setTransparency(150);
            }
            else
            {
            chooseModeButton1.getImage().setTransparency(255);
            }
            if (chooseModeButton1.click())
            {
                beap.play();
                if (world.player[0].controltype==1)
                {
                 world.player[0].controltype=2;
                 showText("Keyboard Control", chooseModeButton1.getX(), chooseModeButton1.getY());
                }
                else
                {
                 world.player[0].controltype=1;
                 showText("Mouse Control", chooseModeButton1.getX(), chooseModeButton1.getY());
                }
            }
        }
    }
    public void chooseModeButton2Behavior()
    {
        if(chooseModeButton2!=null)
        {
            if (chooseModeButton2.mouseOn())
            {
            chooseModeButton2.getImage().setTransparency(150);
            }
            else
            {
            chooseModeButton2.getImage().setTransparency(255);
            }
            if (chooseModeButton2.click())
            {
                beap.play();
                if (world.player[0].controltype==1)
                {
                 world.player[0].controltype=2;
                 world.player[1].controltype=1;
                 showText("Keyboard Control    /           Mouse Control", chooseModeButton2.getX(), chooseModeButton2.getY());
                }
                else
                {
                 world.player[0].controltype=1;
                 world.player[1].controltype=2;
                 showText("Mouse Control           /    Keyboard Control", chooseModeButton2.getX(), chooseModeButton2.getY());
                }
            }
        }
    }
    public void playButtonBehavior()
    {
        if(playButton!=null)
        {
            if (playButton.mouseOn())
            {
            playButton.getImage().setTransparency(150);
            }
            else
            {
            playButton.getImage().setTransparency(255);
            }
            if (playButton.click())
            {
                play.play();
                blastoff.play();
                if (world.player.length==2)
                {
                removeButton("shipImage1");
                removeButton("shipImage2");
                removeButton("chooseModeButton2");
                showText("", 500, 200);
                showText("", 400, 330);
                showText("", 600, 330);
                world.startGame();
                removeButton("playButton");
                
                }
                if (world.player.length==1)
                {
                removeButton("shipImage1");
                removeButton("chooseModeButton1");
                showText("", 500, 200);
                showText("", 500, 330);
                world.startGame();
                removeButton("playButton");
                
                }
            }
        }
    }
    public boolean isWorldInfoShown=true;
    public boolean isPlayerInfoShown=true;
    private int coolDownTimer1=0;
    private int coolDownTimer2=0;
    private int updateTimer1=0;
    private int updateTimer2=0;
    private Image icon1;
    private Image icon2;
    public void keyEvent()
    {
            //world info bar
            if (isWorldInfoShown)
            {
                coolDownTimer1++;
                if (updateTimer1==50)
                {
                    showText("World Info", 150, 100);
                    showText("Object number: "+world.numberOfObjects(), 150, 250);
                    showText("Wave: "+world.wave, 150, 300);
                    showText("Level: "+world.level, 150, 350);
                    showText("Timer: "+world.timer, 150, 400);
                    if (world.player!=null)
                    {
                        showText("Player number: "+world.player.length, 150, 200);
                    }
                    else
                    {
                        showText("Player number: 0", 150, 200);    
                    }
                    updateTimer1=0;
                }
                else
                {
                    updateTimer1++;
                }
                if (Greenfoot.isKeyDown("O")&&coolDownTimer1>20)
                {
                    beap.play();
                     isWorldInfoShown=false;
                     showText("", 150, 100);
                     showText("", 150, 200);
                     showText("", 150, 250);
                     showText("", 150, 300);
                     showText("", 150, 350);
                     showText("", 150, 400);
                     showText("", 150, 450);
                     showText(" Press 'O' to show World Info", 150, 780);
                     coolDownTimer1=0;
                }   
            }
            else
            {
                coolDownTimer1++;
                if (Greenfoot.isKeyDown("O")&&coolDownTimer1>20)
                {
                    beap.play();
                    showText(" Press 'O' to hide World Info", 150, 780);
                    coolDownTimer1=0;
                     isWorldInfoShown=true;
                }
            }
            //player info bar
            if (isPlayerInfoShown)
            {
                coolDownTimer2++;
                if (updateTimer2==50)
                {
                    showText("Player Info", 850, 100);
                    if (world.player!=null)
                    {
                        showText("", 850, 200);
                        if (world.player.length==1)
                        {
                            showText("Player 1", 850, 200);
                            showText("Lives: "+world.player[0].lives, 850, 320);
                            showText("Health: "+(int)world.player[0].health, 850, 350);
                            showText("Score: "+world.player[0].score, 850, 400);
                            showText("Enemy Killed: "+world.player[0].enemyKilled, 850, 450);
                            if(icon1==null)
                            {
                                icon1= new Image();
                                icon1.setImage("spaceship"+world.player[0].type+".png");
                                icon1.getImage().scale(100, 100);
                                world.addObject(icon1, 850, 250);
                            }
                            else
                            {
                                icon1.setImage("spaceship"+world.player[0].type+".png");
                                icon1.getImage().scale(100, 100);
                            }
                        }
                        if (world.player.length==2)
                        {
                            showText("Player 1", 850, 190);
                            showText("Lives: "+world.player[0].lives, 850, 320);
                            showText("Health: "+(int)world.player[0].health, 850, 350);
                            showText("Score: "+world.player[0].score, 850, 380);
                            showText("Enemy Killed: "+world.player[0].enemyKilled, 850, 410);
                            if(icon1==null)
                            {
                                icon1= new Image();
                                icon1.setImage("spaceship"+world.player[0].type+".png");
                                icon1.getImage().scale(100, 100);
                                world.addObject(icon1, 850, 250);
                            }
                            else
                            {
                                icon1.setImage("spaceship"+world.player[0].type+".png");
                                icon1.getImage().scale(100, 100);
                            }
                            showText("Player 2", 850, 490);
                            showText("Lives: "+world.player[1].lives, 850, 620);
                            showText("Health: "+(int)world.player[1].health, 850, 650);
                            showText("Score: "+world.player[1].score, 850, 680);
                            showText("Enemy Killed: "+world.player[1].enemyKilled, 850, 710);
                            if(icon2==null)
                            {
                                icon2= new Image();
                                world.addObject(icon2, 850, 550);
                                icon2.setImage("spaceship"+world.player[1].type+".png");
                                icon2.getImage().scale(100, 100);
                            }
                            else
                            {
                                icon2.setImage("spaceship"+world.player[1].type+".png");
                                icon2.getImage().scale(100, 100);
                            }
                        }
                         
                    }
                    else
                    {
                        showText("No player", 850, 200);    
                    }
                    updateTimer2=0;
                }
                else
                {
                    updateTimer2++;
                }
                if (Greenfoot.isKeyDown("P")&&coolDownTimer2>30)
                {
                    beap.play();
                     isPlayerInfoShown=false;
                     for (int i=0; i<700;i++)
                     {
                     showText("", 850, 10*i);
                     }
                     world.removeObject(icon1);
                     icon1=null;
                     if (icon2!=null)
                     {
                         world.removeObject(icon2);
                         icon2=null;
                     }
                     showText(" Press 'P' to show Player Info", 850, 780);
                     coolDownTimer2=0;
                }  
            }
            else
            {
                coolDownTimer2++;
                if (Greenfoot.isKeyDown("P")&&coolDownTimer2>30)
                {
                    beap.play();
                    showText(" Press 'P' to hide Player Info", 850, 780);
                    coolDownTimer2=0;
                    isPlayerInfoShown=true;
                }
            }
    }
    public void act() 
    {
        choosePlayerButton1Behavior();
        choosePlayerButton2Behavior();
        shipImage1Behavior();
        shipImage2Behavior();
        chooseModeButton1Behavior();
        chooseModeButton2Behavior();
        playButtonBehavior();
        keyEvent();
        // Add your action code here.
    }    
}
