/**
 * Created by David Ritter offline on 5/13/2015.
 */
public class AnimatedActor
extends Actor
{
    private int animation_rate;
    protected int imgs;

    public AnimatedActor(String name, int current_img, Point position, int rate,
                         int animation_rate)
    {
        super(name, current_img, position,rate);
        this.animation_rate = animation_rate;
    }

    public void schedule_entity()
    {
        //schedule_animation();
    }

    public int get_animation_rate()
    {
        return this.animation_rate;
    }

    public void /*Action*/ create_animation_action(WorldModel world, int repeat_count)
    {
        //this.next_image;
    }

    public void schedule_animation(WorldModel world)
    {
		Action act = new Action(world, (Actor) this, System.currentTimeMillis(), 3);
        this.schedule_action(world,act,this.animation_rate);
    }
}
