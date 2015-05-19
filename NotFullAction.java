/*public class NotFullAction
    extends Action
{
    public NotFullAction(WorldModel world, MinerNotFull m, long current_ticks)
	{
        super(world, m, current_ticks);
	}

    public void act()
	{
        this.a.remove_pending_action(this);
        Point entity_pt = this.a.get_position();
        Ore ore;
        for (Entity e : world.get_entities())
		{
            if (e instanceof Ore)
			{
                ore =  (Ore) world.find_nearest(entity_pt, "Ore");
			}
		}
        if (this.a.ore_found(ore.get_position()))
		{
            MinerFull new_entity = (Actor) m.try_transform_miner(world);
            new_entity.schedule_action(world, this, 
                                       current_ticks + new_entity.get_rate());
		}
		}
}*/