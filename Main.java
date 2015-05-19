import processing.core.PApplet;

/**
 * Created by David Ritter offline on 5/16/2015.
 */
public class Main
extends PApplet
{
    public final int TEMP_RATE = 1000;
    public final int TEMP_ANIMATE_RATE = 100;

    public final int SCREEN_WIDTH = 640;
    public final int SCREEN_HEIGHT = 480;
    public final int TILE_WIDTH = 32;
    public final int TILE_HEIGHT = 32;
    private int num_rows = SCREEN_HEIGHT/TILE_HEIGHT;
    private int num_cols = SCREEN_WIDTH/TILE_WIDTH;
    private final int NUM_ROWS_WORLD = 31;
    private final int NUM_COLS_WORLD = 41;
    private final int BGND_COLOR = color(220, 230, 245);
    private final int MIN_DELAY = 150;
    private final int MINER_ANIMATION_RATE = 300;
    private final int BLOB_ANIMATION_RATE = 250;
    private final int QUAKE_ANIMATION_RATE = 200;

    private Background background = new Background("default_background", 0);

    private WorldView view;

    private WorldModel world;
 
    public long t;


    public void setup()
    {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
        background(BGND_COLOR);
        world = new WorldModel(NUM_ROWS_WORLD,NUM_COLS_WORLD,background);
        init_test_world();
        view = new WorldView(num_cols, num_rows, this, world, TILE_WIDTH, TILE_HEIGHT);


        view.setup_view();
        t = System.currentTimeMillis();
    }

    public void draw()
    {
        
        view.draw_background();
        view.draw_entities();
        world.update_on_time(System.currentTimeMillis());
        //p(world);
        //System.out.println("" + world.get_entities().size());
        /*if (t + MIN_DELAY < System.currentTimeMillis())
		{
            if (view.update_on_time(t, world))
			{
                t = System.currentTimeMillis();
			}
			}*/
    }

    public void p(WorldModel world)
	{
        for (Entity e : world.get_entities())
		{
            CoordEntity c = (CoordEntity) e;
            if (c instanceof Miner)
			{
                System.out.println("" + c.get_position().get_x() + " " + c.get_position().get_y());
                break;
			}
		}
	}

    public void init_test_world()
    {
        long t = System.currentTimeMillis();
        Actor a;
        Action act;
        Reader r = new Reader(this.world);
        int type;
        AnimatedActor ani;
        for (Entity e : this.world.get_entities())
		{
            if (e instanceof Actor)
			{
                type = 0;
                if (e instanceof MinerFull)
				{
                    type = 1;
				}
                if (e instanceof Vein)
				{
                    type = 2;
                }
                if (e instanceof Ore)
				{
                    type = 4;
				}
                a = (Actor) e;
                act = new Action(this.world, a, t, type);
                a.schedule_action(this.world, act, t + a.get_rate());
                if (e instanceof AnimatedActor)
				{
                    ani = (AnimatedActor) e;
                    act = new Action(this.world, a, t, 3);
                    ani.schedule_animation(world);
				}
			}
		}
        this.world.add_back();
    }

    public void keyPressed()
    {
		/* switch(key)
        {
            case 'w':
                view.create_shifted_viewport(0,-1);
                break;
            case 's':
                view.create_shifted_viewport(0,1);
                break;
            case 'a':
                view.create_shifted_viewport(-1,0);
                break;
            case 'd':
                view.create_shifted_viewport(1,0);
                break;
        }*/
        
        if(key == CODED)
        switch(keyCode)
        {
            case UP:
                view.create_shifted_viewport(0,-1);
                break;
            case DOWN:
                view.create_shifted_viewport(0,1);
                break;
            case LEFT:
                view.create_shifted_viewport(-1,0);
                break;
            case RIGHT:
                view.create_shifted_viewport(1,0);
                break;
        }
    }

    public static void main(String[] args)
    {
        PApplet.main("Main");
    }

}
