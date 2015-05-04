public class Entity
        extends Object
{
    private String name;
    private String entity_string;

    public Entity(String name, String entity_string)
    {
        this.name = name;
        this.entity_string = entity_string;
    }

    public String get_name()
    {
        return this.name;
    }

    public String entity_string()
    {
        return entity_string;
    }

}