public class CoordEntity
        extends Entity
{
    private Point position;

    public CoordEntity(String name, int current_img, Point position)
    {
        super(name, current_img);
        this.position = position;

    }

    public void set_position(Point pt)
    {
        this.position = pt;
    }

    public Point get_position()
    {
        return this.position;
    }

}