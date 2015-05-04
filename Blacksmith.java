public class Blacksmith
    extends CoordEntity
{
    //are these needed? prob not; i didn't bother implementing setters & getters
    private int resource_limit;
    private int resource_count;
    private int resource_distance;
    public Blacksmith(String name, Point position, int resource_limit)
    {
        super(name, position, "blacksmith");
        this.resource_limit = resource_limit;
        this.resource_count = 0;
        this.resource_distance = 1;
    }
}