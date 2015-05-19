/**
 * Created by David Ritter offline on 5/13/2015.
 */
public class Action
{
    WorldModel world;
    Actor a;
    long current_ticks;
    int act;

    public Action(WorldModel world, Actor a, long current_ticks, int act)
    {
        this.world = world;
        this.a = a;
        this.current_ticks = current_ticks;
        this.act = act;

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
        Action go;
        Point entity_pt;
        Point [] tiles;
        Actor new_entity;
        boolean found = false;
        if (act == 0)
		{
            a.remove_pending_action(this);
            entity_pt = a.get_position();
            MinerNotFull m = (MinerNotFull) a;
            Ore ore; 
            for (Entity e : world.get_entities())
			{
                if (e instanceof Ore)
				{
                    ore =  (Ore) world.find_nearest(entity_pt, "Ore");
                    found = true;
				}
			}
            if (!found)
			{
                Point[] hm = {entity_pt};
                return hm;
			}
            ore =  (Ore) world.find_nearest(entity_pt, "Ore");
            tiles = m.miner_to_ore(world, ore);
            new_entity = m;
            go = this;
            if (m.ore_found(ore.get_position()) || m.get_resource_count() >= 2)
			{
                //System.out.println("~");
                new_entity = (Actor) m.try_transform_miner(world);
                go = new Action(world, a, current_ticks, 1);
			}

            new_entity.schedule_action(world, go, 
                       current_ticks + new_entity.get_rate()); 
            return tiles;
		}

        if (act == 1)
		{
            a.remove_pending_action(this);
            entity_pt = a.get_position();
            MinerFull m = full_from_actor((AnimatedActor) a);
            Blacksmith smith;
            for (Entity e : world.get_entities())
			{
                if (e instanceof Blacksmith)
				{
                    smith = (Blacksmith) world.find_nearest(entity_pt, 
                                      "Blacksmith");
                    found = true;
				}
			}
            if (!found)
			{
                Point[] hm = {entity_pt};
                return hm;
			}
            smith = (Blacksmith) world.find_nearest(entity_pt, "Blacksmith");
            tiles = m.miner_to_smith(world, smith);
            new_entity = m;
            go = this;
            if (m.smith_found(smith.get_position()))
			{
                new_entity = (Actor) m.try_transform_miner(world);
                go = new Action(world, a, current_ticks, 0);
			}
            
            new_entity.schedule_action(world, go,
									   current_ticks + new_entity.get_rate());
            return tiles;
		}
        if (act == 2)
		{
            a.remove_pending_action(this);
            Vein v = (Vein) a;
            Point open_pt = v.find_open_around(world);

            if (open_pt.get_x() != -1)
			{
                Ore ore = new Ore("ore", 0, open_pt, 200);
                world.add_entity(ore);
			}
            a.schedule_action(world, this, current_ticks + a.get_rate());
		}
		/* if (act == 3)
		{
            a.remove_current_action(this);
            a.next_image();
            if (repeat_count != 1)
			{
			a.schedule_action(world, */
        //if (a instance of MinerFull)
        return null;
	}
    public MinerFull full_from_actor(AnimatedActor a)
	{
        return new MinerFull(a.get_name(), a.get_current_img(), a.get_position(), a.get_rate(), a.get_animation_rate(), 2);
	}
       

}

