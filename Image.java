import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Image here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Image extends Actor
{
    /**
     * Act - do whatever the Image wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean mouseOn() 
    {
    MouseInfo mouse = Greenfoot.getMouseInfo();
    return mouse != null && mouse.getX() > getX() - getImage().getWidth()/2 && mouse.getX() < getX() + getImage().getWidth()/2 && mouse.getY() > getY() - getImage().getHeight()/2 && mouse.getY() < getY() + getImage().getHeight()/2;
    }
    public boolean click()
    {
        if (Greenfoot.mouseClicked(this))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isCollideWith(Actor actor)
    {
        if (intersects(actor))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
