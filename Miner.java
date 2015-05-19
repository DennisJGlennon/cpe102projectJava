public class Miner
        extends AnimatedActor
{
    private int resource_limit;
    private int resource_count;

    public Miner(String name, int current_img, Point position, int rate,
                 int animation_rate, int resource_limit, int resource_count)
    {
        super(name, current_img, position, rate, animation_rate);
        this.resource_limit = resource_limit;
        this.resource_count = resource_count;
        this.imgs = 5;

    }

    public int get_resource_limit()
    {
        return this.resource_limit;
    }

    public void set_resource_count(int r_count)
    {
        this.resource_count = r_count;
    }

    public int get_resource_count()
    {
        return this.resource_count;
    }

    public Point next_position(WorldModel world, Point dest_pt)
    {
        Point entity_pt = this.get_position();
        if (entity_pt.get_x() == dest_pt.get_x() && entity_pt.get_y() ==
            dest_pt.get_y())
		{
            System.out.println("Reached position");
		}
        int horiz = Utility.sign(dest_pt.get_x() - entity_pt.get_x());
        Point new_pt = new Point(entity_pt.get_x() + horiz, entity_pt.get_y());

        if (horiz == 0 /*|| world.occ_for_path(new_pt)*/)
        {
 
            int vert = Utility.sign(dest_pt.get_y() - entity_pt.get_y());
            new_pt = new Point(entity_pt.get_x(), entity_pt.get_y() + vert);

            if (vert == 0/* || world.occ_for_path(new_pt)*/)
            {
  
                new_pt = new Point(entity_pt.get_x(), entity_pt.get_y());
            }
        }
        return new_pt;
    }
}