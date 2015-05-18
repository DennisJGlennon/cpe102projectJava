public class CoordEntity
        extends Entity
{
    private Point position;

    public CoordEntity(String name, Point position, String entity_string)
    {
        super(name, entity_string);
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