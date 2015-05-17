import processing.core.*;
import java.util.ArrayList;
import java.util.List;


public class WorldView
    extends PApplet
{

    // rows-height-y;cols-width-x
    private WorldModel world;
    private PApplet screen;

    private int num_rows;
    private int num_cols;
    private int tile_width;
    private int tile_height;

    private final int BGND_COLOR = color(220, 230, 245);


    private List<PImage> background_image = new ArrayList<>();
    private List<PImage> miner_images = new ArrayList<>();
    private List<PImage> vein_image = new ArrayList<>();
    private List<PImage> oreblob_images = new ArrayList<>();
    private List<PImage> ore_image = new ArrayList<>();
    private List<PImage> quake_images = new ArrayList<>();
    private List<PImage> blacksmith_image = new ArrayList<>();


    //initial background for testing purposes
    private static final int ANIMATION_TIME = 100;
    private long next_time;

    // current location of top left corner of worldview
    private Point ref_pt = new Point(0,0);

    public WorldView(int num_cols, int num_rows, PApplet screen, WorldModel world, int tile_width, int tile_height)
    {
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        this.screen = screen;
        this.tile_height = tile_height;
        this.tile_width = tile_width;
        this.world = world;
    }

    public void setup_view()
    {
        image_init();
        next_time = System.currentTimeMillis() + ANIMATION_TIME;
    }

    public void draw_background()
    {
        for(int x=ref_pt.get_x(); x < (num_cols+ref_pt.get_x()); x++)
        {
            for(int y=ref_pt.get_y();y < (num_rows+ref_pt.get_y());y++)
            {
                Point curr_pt = new Point(x, y);
                screen.image(background_image.get(0), (x-ref_pt.get_x()) * 32, (y-ref_pt.get_y()) * 32);
            }
        }
    }

    public void draw_entities()
    {
        for(int x=ref_pt.get_x(); x < (num_cols+ref_pt.get_x()); x++)
        {
            for(int y=ref_pt.get_y();y < (num_rows+ref_pt.get_y());y++)
            {
                Point curr_world_pt = new Point(x, y);
                Point curr_view_pt = world_to_viewport(curr_world_pt);
                if (world.is_occupied(curr_world_pt))
                {
                    Entity e = world.get_tile_occupant(curr_world_pt);
                    if(e instanceof Miner) {
                        screen.image(miner_images.get(e.get_current_img()),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
                    }
                    else if(e instanceof Vein) {
                        screen.image(vein_image.get(0),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
                    }
                    else if(e instanceof Blacksmith){
                        screen.image(blacksmith_image.get(0),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
                    }
                    else if(e instanceof OreBlob) {
                        screen.image(oreblob_images.get(e.get_current_img()),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
                    }
                    else if(e instanceof Ore) {
                        screen.image(ore_image.get(0),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
                    }
                    else if(e instanceof Quake) {
                        screen.image(quake_images.get(e.get_current_img()),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
                    } else
                        screen.image(background_image.get(0),
                                curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
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

        Point new_ref_point = new Point(ref_pt.get_x()+delta_x,ref_pt.get_y()+delta_y);
        Point new_bot_right = new Point(new_ref_point.get_x()+num_cols,new_ref_point.get_y()+num_rows);

        if(this.world.within_bounds(new_ref_point) &&
                this.world.within_bounds(new_bot_right))
        {
            this.ref_pt = new_ref_point;
        }
    }

    public void image_init() {
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

        blacksmith_image.add(loadImage("blacksmith.bmp"));
    }



}
