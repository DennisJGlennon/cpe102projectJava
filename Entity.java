public class Entity
        extends Object
{
    private String name;
    private int current_img;

    public Entity(String name, int current_img)
    {
        this.name = name;
        this.current_img = current_img;
    }

    public String get_name()
    {
        return this.name;
    }

    public int get_current_img()
	{
        return this.current_img;
	}

    public void to_img(int img)
	{
        this.current_img = img;
	}



}