import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private GUI MainGUI;
    public Player[] player;
    public boolean isGameRunning=false;
    private int[] spawnType={7,8,9};
    public GreenfootImage bodyImage=new GreenfootImage("empty.png");
    private boolean isBodyShown=false;
    public String[] backgrounds={"background1.jpg","background2.jpg"};
    private GreenfootSound[] explodesound=new GreenfootSound[5];
    private int explodesoundTimer=0;
    private void setupGUI()
    {
        MainGUI=new GUI();
        addObject(MainGUI,0,0);
        MainGUI.world=this;
        MainGUI.addButton("choosePlayer1",400,400);
        MainGUI.addButton("choosePlayer2",600,400);
        MainGUI.showText("Choose Player Number", 500, 200);
        MainGUI.showText("Choose Player Number", 500, 200);
        Image bar1=new Image();
        bar1.setImage("grey.png");
        bar1.getImage().scale(300,800);
        addObject(bar1,150,400);
        Image bar2=new Image();
        bar2.setImage("grey.png");
        bar2.getImage().scale(300,800);
        addObject(bar2,850,400);
        showText(" Press 'P' to hide Player Info", 850, 780);
        showText(" Press 'O' to hide World Info", 150, 780);
        for(int i=0;i<explodesound.length;i++)
         {
             explodesound[i]=new GreenfootSound("explode"+(i+1)+".wav");
             explodesound[i].setVolume(85);
         }
        for(int i=0;i<shootsound.length;i++)
         {
             shootsound[i]=new GreenfootSound("shoot"+(i+1)+".wav");
             shootsound[i].setVolume(68);
         }
    }
     private void updateexplodeSound()
    {
        if(explodesoundTimer==25)
        {
             for(int i=0;i<explodesound.length;i++)
             {
                 explodesound[i]=new GreenfootSound("explode"+(i+1)+".wav");
                 explodesound[i].setVolume(85);
             }
             explodesoundTimer=0;
        }
        else
        {
        explodesoundTimer++;
        }
    }
    public void explodeSound()
    {
        explodesound[Greenfoot.getRandomNumber(explodesound.length)].play();
    }
    public GreenfootSound[] shootsound=new GreenfootSound[4];
    private int shootsoundTimer=0;
    private void updateshootSound()
    {
        if(shootsoundTimer==8)
        {
             for(int i=0;i<shootsound.length;i++)
             {
                 shootsound[i]=new GreenfootSound("shoot"+(i+1)+".wav");
                 shootsound[i].setVolume(72);
             }
             shootsoundTimer=0;
        }
        else
        {
        shootsoundTimer++;
        }
    }
    public int currentBg=0;
    public void addBackground()
    {
        Background bg=new Background();
        bg.main=this;
        bg.speed=5;
        addObject(bg,(int)(getWidth()/2.0),(int)(getHeight()/2.0));
        bg=new Background();
        bg.main=this;
        bg.speed=5;
        addObject(bg,(int)(getWidth()/2.0),-(int)(getHeight()/2.0));
    }
    public void addEnemy(int Type,int x, int y, float healthScale)
    {
        if (Type!=0)
        {
            Enemy enemy=new Enemy();
            enemy.main=this;
            enemy.type=Type;
            enemy.setRotation(180);
            enemy.healthScale=healthScale;
            addObject(enemy,x,y);
        }
    }
    private void addPlayer()
    {
        if (player.length==1)
        {
            addObject(player[0],500,600);
        }
        if (player.length==2)
        {
            addObject(player[0],400,600);
            addObject(player[1],600,600);
        }
    }
    public void startGame()
    {
        for (int i=0; i<player.length;i++)
        {
            player[i].setImage("spaceship"+player[i].type+".png");
            player[i].getImage().scale(50,50);
            player[i].main=this;
            if (player[i].controltype==2)
            {
                player[i].speed=4;
            }
        }
        addPlayer();
        isGameRunning=true;
    }
    public void error()
    {
        isGameRunning=false;
        // Greenfoot.stop();
    }
    public Main()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false); 
        setupGUI();
        addBackground();
        setupCover();
        randomType();
        setUpBGM();
        setPaintOrder(GUI.class,Item.class,Effect.class,Image.class,bulletE.class,bulletP.class,Boss.class,Player.class,Enemy.class,Boss.class,Background.class,Main.class);
        setActOrder(Main.class,GUI.class,Effect.class,Image.class,Boss.class,Item.class,Player.class,Enemy.class,bulletP.class,bulletE.class,Background.class);
    }
    public int timer=0;;
    public int wave=1;
    public int level=1;
    private int enemyNumber=3;
    private int bossNumber=0;
    private int wavelength=1000;
    private int wavebreak=250;
    private int currentTime=0;
    public Boss boss;
    private void spawnEnemy(int type,float frequency,float healthScale)
    {
        if ((int)(10*frequency*(1+0.2*player.length))>Greenfoot.getRandomNumber(10*wavelength))
        {
            addEnemy(type,300+Greenfoot.getRandomNumber(400),-50,healthScale*(1+0.1f*player.length));
        }
    }
    public void setLevel(int Level,int Wave)
    {
        level=Level;
        wave=Wave;
    }
    public void addItem(int type,int x, int y)
    {
        Item item=new Item(type);
        item.main=this;
        addObject(item,x,y);
    }
    public void spawnItems(int type,float frequency)
    {
         if ((int)(10*frequency*(1+0.2*player.length))>Greenfoot.getRandomNumber(10*wavelength))
        {
            //showText("fr:"+(int)(10*frequency*(0.8+0.2*player.length)),400,400);
            
            addItem(type,300+Greenfoot.getRandomNumber(400),-20);
        }
    }
    public void addBoss(int Type,int x, int y, float healthScale)
    {
        if (Type!=0&&boss==null)
        {
            boss=new Boss();
            boss.main=this;
            boss.type=Type;
            boss.setRotation(180);
            boss.healthScale=healthScale;
            addObject(boss,x,y);
        }
    }
    public boolean isBossSpawned=false;
    
    private float[] frequencyBase={15,16,10,7,7,15,9,2,2};
    private void spawnWave()
    {
        spawnItems(1,1+level*0.1f);
        if(level>=2)
        {
            spawnItems(2,0.5f+level*0.1f);
            spawnItems(3,0.5f+level*0.1f);
            spawnItems(4,0.5f+level*0.05f);
        }
        if(level>=4)
        {
            spawnItems(5,0.5f+level*0.1f);
        }
        if(timer>currentTime+wavebreak&&timer<currentTime+wavelength&&wave<8)
        {
            
            switch(wave)
            {
                case 1:
                spawnEnemy(spawnType[0],frequencyBase[spawnType[0]-1]*(1+0.1f*level),1+0.1f*level);
                break;
                case 2:
                spawnEnemy(spawnType[1],frequencyBase[spawnType[1]-1]*(1+0.1f*level),1+0.1f*level);
                break;
                case 3:
                spawnEnemy(spawnType[2],frequencyBase[spawnType[2]-1]*(1+0.1f*level),1+0.1f*level);
                break;
                case 4:
                spawnEnemy(spawnType[0],frequencyBase[spawnType[0]-1]*(0.6f+0.1f*level),1+0.1f*level);
                spawnEnemy(spawnType[1],frequencyBase[spawnType[1]-1]*(0.6f+0.1f*level),1+0.1f*level);
                break;
                case 5:
                spawnEnemy(spawnType[0],frequencyBase[spawnType[0]-1]*(0.6f+0.1f*level),1+0.1f*level);
                spawnEnemy(spawnType[2],frequencyBase[spawnType[2]-1]*(0.6f+0.1f*level),1+0.1f*level);
                break;
                case 6:
                spawnEnemy(spawnType[1],frequencyBase[spawnType[1]-1]*(0.6f+0.1f*level),1+0.1f*level);
                spawnEnemy(spawnType[2],frequencyBase[spawnType[2]-1]*(0.6f+0.1f*level),1+0.1f*level);
                break;
                case 7:
                spawnEnemy(spawnType[0],frequencyBase[spawnType[0]-1]*(0.45f+0.1f*level),1+0.1f*level);
                spawnEnemy(spawnType[1],frequencyBase[spawnType[1]-1]*(0.45f+0.1f*level),1+0.1f*level);
                spawnEnemy(spawnType[2],frequencyBase[spawnType[2]-1]*(0.45f+0.1f*level),1+0.1f*level);
                break;
            }
        }
        if (wave==8)
        {
            //BOSS INCOMING
            if(timer>currentTime+200)
            {
                if(isBossSpawned)
                {
                    if(boss==null)
                    {
                        wave++;
                        currentTime=timer;
                        isBossSpawned=false;
                    }
                }
                else
                {
                     addBoss(1,500,200,(float)(1+0.5f*level));
                     isBossSpawned=true;
                }
            }
        }
        else
        {
            if(timer>currentTime+wavelength&&getObjects(Enemy.class).isEmpty())
            {
                wave++;
                currentTime=timer;
            }
            if (wave>8)
            {
                boss=null;
                randomType();
                wave=1;
                level++;
            }
        }
}
public void randomType()
{
    int r=0;
    int x=0;
    if(level==1)
    {
        r=3;
        x=0;
    }
    if(level>1&&level<4)
    {
        r=7;
        x=0;
    }
    if(level>=4)
    {
        r=6;
        x=3;
    }
    int a=Greenfoot.getRandomNumber(r)+1+x;
    int b=Greenfoot.getRandomNumber(r)+1+x;
    int c=Greenfoot.getRandomNumber(r)+1+x;
    while (b==a)
    {
        b=Greenfoot.getRandomNumber(r)+1+x;
    }
    while (c==a||c==b)
    {
        c=Greenfoot.getRandomNumber(r)+1+x;
    }
    spawnType[0]=a;
    spawnType[1]=b;
    spawnType[2]=c;
}
public boolean isPausing=false;
private boolean gameover=false;
private int restartTimer=600;
private GreenfootSound[] bgm=new GreenfootSound[14];
private void setUpBGM()
{
    for (int i=0;i<bgm.length;i++)
    {
        bgm[i]=new GreenfootSound("bgm"+(i+1)+".mp3");
        bgm[i].setVolume(78);
    }
}
private GreenfootSound currentSong;
private boolean bgmPause=false;
private void playBGM()
{
    if(currentSong==null)
    {
        currentSong=bgm[Greenfoot.getRandomNumber(bgm.length)];
        currentSong.play();
    }
    else
    {
        
        if(!currentSong.isPlaying())
        {
            if(bgmPause)
            {
                GreenfootSound lastSong=currentSong;
                do {
                    currentSong=bgm[Greenfoot.getRandomNumber(bgm.length)];
                } while (currentSong==lastSong);
                bgmPause=false;
            }
            currentSong.play();
            bgmPause=false;
        }
    }
}
private GreenfootSound gameover1=new GreenfootSound("gameover1.wav");
private GreenfootSound gameover2=new GreenfootSound("gameover2.wav");
public void act()
{
    keyDetection();
    if (isGameRunning&&!isPausing)
    {
        updateshootSound();
        updateexplodeSound();
        bgmPause=false;
        playBGM();
        spawnWave();
        timer++;
    }
    if(isPausing)
    {
        bgmPause=true;
        currentSong.pause();
    }
    if(isGameRunning)
    {
        if(player.length==1&&player[0].isDead)
        {
            isGameRunning=false;
            addObject(cover,500,getHeight()/2);
            showText("Game Over",500,300);
            showText("Player 1 Score:"+player[0].score,500,350);
            showText("Player 1 Enemy Killed:"+player[0].enemyKilled,500,400);
            showText("Level:"+level,500,450);
            showText("Wave:"+wave,500,500);
            if(level>=10)
            {
                showText("OMG IMPOSSIBLE HACKER!!",500,200);
            }
            gameover=true;
            currentSong.stop();
            gameover1.play();
        }
        if(player.length==2&&player[0].isDead&&player[1].isDead)
        {
            isGameRunning=false;
            addObject(cover,500,getHeight()/2);
            showText("Game Over",500,300);
            showText("Player 1 score:"+player[0].score,500,350);
            showText("Player 1 enemy killed:"+player[0].enemyKilled,500,400);
            showText("Player 2 score:"+player[1].score,500,450);
            showText("Player 2 enemy killed:"+player[1].enemyKilled,500,500);
            showText("Level:"+level,500,550);
            showText("Wave:"+wave,500,600);
            if(level>=10)
            {
                showText("OMG IMPOSSIBLE HACKER!!",500,200);
            }
            gameover=true;
            currentSong.stop();
            gameover2.play();
        }
        
    }
    if (gameover)
    {
        showText("Restart in "+(int)(restartTimer/60),500,700);
        restartTimer--;
        if(restartTimer==60)
        {
            Greenfoot.setWorld(new Main());
        }
    }
}
private int keyCoolDownTimer=0;
private Image cover;
private void setupCover()
{
     cover=new Image();
     cover.setImage("black.png");
     cover.getImage().scale(400,getHeight());
     cover.getImage().setTransparency(150);
}
private void keyDetection()
{
    keyCoolDownTimer++;
    if(Greenfoot.isKeyDown("B")&&keyCoolDownTimer>50)
    {
       if(isBodyShown)
       {
           isBodyShown=false;
           bodyImage=new GreenfootImage("empty.png");
        }
        else
        {
           isBodyShown=true;
           bodyImage=new GreenfootImage("grey.png");
        }
        keyCoolDownTimer=0;
    }
     if(Greenfoot.isKeyDown("SPACE")&&keyCoolDownTimer>10)
    {
       if(isGameRunning&&isPausing)
       {
           isPausing=false;
           removeObject(cover);
           showText("",500,400);
        }
        else
        {
            if(isGameRunning&&!isPausing)
            {
               isPausing=true;
               addObject(cover,500,getHeight()/2);
               showText("Press SPACE to continue",500,400);
            }
        }
        keyCoolDownTimer=0;
    }
}
}
