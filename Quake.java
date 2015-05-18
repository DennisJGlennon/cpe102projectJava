public class Quake
    extends AnimatedActor
{
    public Quake(String name, int current_img, Point position, int rate, int animation_rate)
    {
        super(name, current_img, position, rate, animation_rate);
        this.imgs = 6;
    }
}