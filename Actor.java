/**
 * Created by David Ritter offline on 5/13/2015.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Actor extends CoordEntity
{
    private int rate;
    private List<Action> pending_actions = new ArrayList<>();

    public Actor(String name, int current_img, Point position, int rate)
    {
        super(name, current_img, position);
        this.rate = rate;
    }

    public void schedule_action(WorldModel world, int time)
    {

    }

    public void remove_entity(WorldModel world)
    {
        int temp_size = pending_actions.size();
        for(int i=0; i<temp_size;i++)
        {
            //world.unschedule_action(pending_actions.get(i));
            //clear_pending_actions();
            world.remove_entity(this);
        }
    }

    public int get_rate()
    {
        return this.rate;
    }
}
