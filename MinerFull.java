public class MinerFull
    extends Miner
{
    public MinerFull(String name, int current_img, Point position, int rate, int animation_rate, int resource_limit)
    {
        super(name, current_img, position, rate, animation_rate, resource_limit, resource_limit);
    }

    public Point [] miner_to_smith(WorldModel world, Blacksmith smith)
	{
        Point entity_pt = this.get_position();
        Point smith_pt = smith.get_position();

        if (entity_pt.adjacent(smith_pt))
		{
            this.set_resource_count(0);
            return null;
		}
        else
		{
            Point new_pt = this.next_position(world, smith_pt);
            return world.move_entity(this, new_pt);
		}
	}

    public boolean smith_found(Point smith_pt)
	{
        return this.get_position().adjacent(smith_pt);
	}

    public Miner try_transform_miner(WorldModel world)
	{
        Miner miner = new MinerNotFull(this.get_name(), this.get_current_img(),
									   this.get_position(), this.get_rate(),
                                       this.get_animation_rate(), 
                                       this.get_resource_limit());
        return miner;
	}

    

}