public class MinerFull
    extends Miner
{
    public MinerFull(String name, int current_img, Point position, int rate, int animation_rate, int resource_limit)
    {
        super(name, current_img, position, rate, animation_rate, resource_limit, resource_limit);
    }
}