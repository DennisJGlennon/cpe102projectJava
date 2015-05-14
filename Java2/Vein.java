public class Vein
    extends CoordEntity
{
    public Vein(String name, Point position)
    {
        super(name, position, "vein");
    }

    public Point find_open_around(WorldModel world, int dist)
    {
        Point pt = this.get_position();
        for (int dy = -dist; dy < dist + 1; dy++)
	{
            for (int dx = -dist; dx < dist + 1; dx++)
	    {
                Point new_pt = new Point(pt.get_x() + dx, pt.get_y() + dy);
                if (world.within_bounds(new_pt) && !(world.is_occupied(new_pt)))
		{
                    return new_pt;
		}
	    }
	}
        Point null_pt = new Point(-1, -1);
        return null_pt;
    }
}