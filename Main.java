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
    private final int NUM_ROWS_WORLD = 10000;
    private final int NUM_COLS_WORLD = 10000;
    private final int BGND_COLOR = color(220, 230, 245);

    private Background background = new Background("default_background");

    private WorldView view;

    private WorldModel world;


    public void setup()
    {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
        background(BGND_COLOR);
        world = new WorldModel(NUM_ROWS_WORLD,NUM_COLS_WORLD,background);
        init_test_world();
        view = new WorldView(num_cols, num_rows, this, world, TILE_WIDTH, TILE_HEIGHT);

        view.setup_view();
    }

    public void draw()
    {
        view.draw_background();
        view.draw_entities();
    }

    public void init_test_world()
    {

        Point[][] points = new Point[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int o = 0; o < 10; o++)
            {
                points[i][o] = new Point(i + 1, o + 1);
            }
        }

        Background back = new Background("back",0);
        Blacksmith smith = new Blacksmith("smith", 0, points[1][3]);
        Ore ore = new Ore("ore", 0, points[8][0]);
        Quake quake = new Quake("quake", points[3][1]);
        Obstacle obs = new Obstacle("obs", points[4][7]);
        Vein vein = new Vein("vein",  points[4][7]);
        OreBlob blob = new OreBlob("blob", points[1][2]);
        Miner miner = new Miner("miner", points[5][1], 10, 3, "miner test");
        MinerNotFull notfull = new MinerNotFull("notfull", 8, points[4][4]);
        MinerFull full = new MinerFull("full", 6, points[6][6]);

        world = new WorldModel(1000,1000,back);

        this.world.add_entity(smith);
        this.world.add_entity(ore);
        this.world.add_entity(obs);
        this.world.add_entity(quake);
        this.world.add_entity(vein);
        this.world.add_entity(blob);
        this.world.add_entity(notfull);
        this.world.add_entity(miner);
        this.world.add_entity(full);

    }

    public void keyPressed()
    {
        switch(key)
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
        }
        /*
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
                view.create_shifted_viewport(1,0);
                break;
            case RIGHT:
                view.create_shifted_viewport(-1,0);
                break;
        }*/
    }

    public static void main(String[] args)
    {
        PApplet.main("Main");
    }

}
