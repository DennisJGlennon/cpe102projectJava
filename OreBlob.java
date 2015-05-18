public class OreBlob
    extends AnimatedActor
{
    public OreBlob(String name, int current_img, Point position, int rate, int animation_rate)
    {
        super(name, current_img, position, rate, animation_rate);
        this.imgs = 12;
    }

    public Point blob_next_position(WorldModel world, Point dest_pt)
    {
        Point entity_pt = this.get_position();
        int horiz = Utility.sign(dest_pt.get_x() - entity_pt.get_x());
        Point new_pt = new Point(entity_pt.get_x() +
                                    horiz, entity_pt.get_y());

	if (horiz == 0 || (world.is_occupied(new_pt) &&
	        !(world.get_tile_occupant(new_pt) instanceof Ore)))
	{
            int vert = Utility.sign(dest_pt.get_y() - entity_pt.get_y());
            new_pt = new Point(entity_pt.get_x(), entity_pt.get_y() +
					vert);

	    if (vert == 0 || (world.is_occupied(new_pt) &&
	        !(world.get_tile_occupant(new_pt) instanceof Ore)))
	    {
                new_pt = new Point(entity_pt.get_x(),
                                            entity_pt.get_y());
	    }
	}
        return new_pt;
    }


}