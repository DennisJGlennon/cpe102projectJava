import processing.core.*;
import java.util.ArrayList;
import java.util.List;


public class WorldView
extends PApplet
{

    // rows-height-y;cols-width-x
    private WorldModel world;
    public final int SCREEN_WIDTH = 640;
    public final int SCREEN_HEIGHT = 480;
    public final int TILE_WIDTH = 32;
    public final int TILE_HEIGHT = 32;
    private int num_rows = SCREEN_HEIGHT/TILE_HEIGHT;
    private int num_cols = SCREEN_WIDTH/TILE_WIDTH;
    private Background background;

    private List<PImage> background_image = new ArrayList<>();
    private List<PImage> miner_images = new ArrayList<>();
    private List<PImage> vein_image = new ArrayList<>();
    private List<PImage> oreblob_images = new ArrayList<>();
    private List<PImage> ore_image = new ArrayList<>();
    private List<PImage> quake_images = new ArrayList<>();


    //initial background for testing purposes
    private final int BGND_COLOR = color(220, 230, 245);
    private static final int ANIMATION_TIME = 100;
    private long next_time;

    // current location of top left corner of worldview
    private Point ref_pt = new Point(0,0);


    public void setup()
    {
        init_test_world();
        image_init();
        size(640,480);
        background(BGND_COLOR);
        next_time = System.currentTimeMillis() + ANIMATION_TIME;
    }

    public void draw()
    {
        draw_background();
        draw_entities();
    }

    private void draw_background()
    {
        for(int x=ref_pt.get_x(); x < num_cols+ref_pt.get_x(); x++)
        {
            for(int y=ref_pt.get_y();y < num_rows+ref_pt.get_y();y++)
            {
                Point curr_pt = new Point(x,y);
                image(background_image.get(0),x*32,y*32);
            }
        }
    }

    private void draw_entities()
    {
        for(int x=0; x < num_cols; x++)
        {
            for(int y=0;y < num_rows;y++)
            {
                Point curr_pt = new Point(x, y);
                if (world.is_occupied(curr_pt))
                {
                    Entity e = world.get_tile_occupant(curr_pt);
                    if(e instanceof Miner)
                    {
                        image(miner_images.get(e.get_current_img()), x * 32, y * 32);
                    }
                    else if(e instanceof Vein)
                    {
                        image(vein_image.get(0),x*32,y*32);
                    }
                    else if(e instanceof OreBlob)
                    {
                        image(oreblob_images.get(e.get_current_img()), x * 32, y * 32);
                    }
                    else if(e instanceof Ore)
                    {
                        image(ore_image.get(0), x * 32, y * 32);
                    }
                    else if(e instanceof Quake)
                    {
                        image(quake_images.get(e.get_current_img()),x*32,y*32);
                    }
                    else
                        image(background_image.get(0),x*32,y*32);
                }
            }
        }

    }

    public Point viewport_to_world(Point pt)
    {
        return new Point(pt.get_x() + ref_pt.get_x(),pt.get_y() + ref_pt.get_y());
    }

    public Point world_to_viewport(Point pt)
    {
        return new Point(pt.get_x() - ref_pt.get_x(),pt.get_y() - ref_pt.get_y());

    }

    public void create_shifted_viewport(int delta_x, int delta_y)
    {
        this.ref_pt = new Point(ref_pt.get_x()+delta_x,ref_pt.get_y()+delta_y);
    }

    private void image_init()
    {
        background_image.add(loadImage("grass.bmp"));

        miner_images.add(loadImage("miner1.bmp"));
        miner_images.add(loadImage("miner2.bmp"));
        miner_images.add(loadImage("miner3.bmp"));
        miner_images.add(loadImage("miner4.bmp"));
        miner_images.add(loadImage("miner5.bmp"));

        vein_image.add(loadImage("vein.bmp"));

        oreblob_images.add(loadImage("blob1.bmp"));
        oreblob_images.add(loadImage("blob2.bmp"));
        oreblob_images.add(loadImage("blob3.bmp"));
        oreblob_images.add(loadImage("blob4.bmp"));
        oreblob_images.add(loadImage("blob5.bmp"));
        oreblob_images.add(loadImage("blob6.bmp"));
        oreblob_images.add(loadImage("blob7.bmp"));
        oreblob_images.add(loadImage("blob8.bmp"));
        oreblob_images.add(loadImage("blob9.bmp"));
        oreblob_images.add(loadImage("blob10.bmp"));
        oreblob_images.add(loadImage("blob11.bmp"));
        oreblob_images.add(loadImage("blob12.bmp"));

        ore_image.add(loadImage("ore.bmp"));

        quake_images.add(loadImage("quake1.bmp"));
        quake_images.add(loadImage("quake2.bmp"));
        quake_images.add(loadImage("quake3.bmp"));
        quake_images.add(loadImage("quake4.bmp"));
        quake_images.add(loadImage("quake5.bmp"));
        quake_images.add(loadImage("quake6.bmp"));
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

        Background back = new Background("back");
        Blacksmith smith = new Blacksmith("smith", points[1][2], 3);
        Ore ore = new Ore("ore", points[8][0]);
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

    public static void main(String[] args)
    {
        PApplet.main("WorldView");
    }
}
