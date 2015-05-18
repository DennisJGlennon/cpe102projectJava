public class Pair
{
    private Action action;
    private long time;

    public Pair(Action action, long time)
	{
        this.action = action;
        this.time = time;
	}

    public Action action()
	{
        return action;
	}

    public long time()
	{
        return time;
	}
}