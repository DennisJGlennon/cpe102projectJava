public class MinerNotFull
    extends Miner
{
    public MinerNotFull(String name, int current_img, Point position, int rate, int animation_rate, int resource_limit)
    {
        super(name, current_img, position, rate, animation_rate, resource_limit, 0);
    }

    public Point [] miner_to_ore(WorldModel world, Ore ore)
	{
        Point entity_pt = this.get_position();
        Point ore_pt = ore.get_position();
        if (entity_pt.adjacent(ore_pt))
		{
            this.set_resource_count(this.get_resource_count() + 1);
            world.remove_entity(ore);
            Point [] pt = {ore_pt};
            return pt;
		}
		else
		{
            Point new_pt = this.next_position(world, ore_pt);
            return world.move_entity(this, new_pt);
		}
	}

    public boolean ore_found(Point ore_pt)
	{
        return this.get_position().adjacent(ore_pt);
	}

    public Miner try_transform_miner(WorldModel world)
	{
        if (this.get_resource_count() < this.get_resource_limit())
		{
            return (Miner) this;
		}
        else
		{
            System.out.println("madenew");
            Miner miner = new MinerFull(this.get_name(), 
                                  this.get_current_img(),
                                  this.get_position(), this.get_rate(),
                                  this.get_animation_rate(),                                                      this.get_resource_limit());
            world.remove_entity(this);
            world.add_entity((CoordEntity) miner);
            
            return miner;
		}
	}
}