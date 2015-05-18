/**
 * Created by David Ritter offline on 5/13/2015.
 */
public class Action
{
    WorldModel world;
    Actor a;
    long current_ticks;

    public Action(WorldModel world, Actor a, long current_ticks)
    {
        this.world = world;
        this.a = a;
        this.current_ticks = current_ticks;

    }

    public WorldModel world()
	{
        return world;
	}

    public Actor actor()
	{
        return a;
	}
 
    public long ticks()
	{
        return current_ticks;
	}

    public Point [] act()
	{

        Point entity_pt;
        Point [] tiles;
        Actor new_entity;
        if (a instanceof MinerNotFull)
		{
            System.out.println("Not full action queued");
            a.remove_pending_action(this);
            entity_pt = a.get_position();
            MinerNotFull m = (MinerNotFull) a;
            Ore ore =  (Ore) world.find_nearest(entity_pt, "Ore");
            tiles = m.miner_to_ore(world, ore);
            new_entity = m;
            if (m.ore_found(ore.get_position()))
			{
                new_entity = (Actor) m.try_transform_miner(world);
			}
            
            new_entity.schedule_action(world, this, 
                       current_ticks + new_entity.get_rate()); 
            return tiles;
		}

        if (a instanceof MinerFull)
		{
            a.remove_pending_action(this);
            entity_pt = a.get_position();
            MinerFull m = (MinerFull) a;
            Blacksmith smith = (Blacksmith) world.find_nearest(entity_pt, "Blacksmith");
            tiles = m.miner_to_smith(world, smith);
            new_entity = m;
            if (m.smith_found(smith.get_position()))
			{
                new_entity = (Actor) m.try_transform_miner(world);
			}
            
            new_entity.schedule_action(world, this,
									   current_ticks + new_entity.get_rate());
            return tiles;
		}
            
            
            
        //if (a instance of MinerFull)
        return null;
	}

}

