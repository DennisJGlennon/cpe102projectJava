/**
 * Created by David Ritter offline on 5/13/2015.
 */
public class AnimatedActor
extends Actor
{
    private int animation_rate;

    public AnimatedActor(String name, Point position, int rate,
                         int animation_rate, String entity_string)
    {
        super(name,position,rate,entity_string);
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

    public void schedule_animation(WorldModel world, int repeat_count)
    {
        //schedule_action(world,create_animation_action(world,repeat_count),animation_rate);
    }
}
