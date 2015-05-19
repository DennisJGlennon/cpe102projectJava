public class Vein
    extends Actor
{
	int resource_distance;
    public Vein(String name, int current_img, Point position, int rate, int resource_distance)
    {
        super(name, current_img, position, rate);
        this.resource_distance = resource_distance;
    }

    public Point find_open_around(WorldModel world)
    {
        int dist = this.resource_distance;
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