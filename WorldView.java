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
    private List<PImage> obstacle_image = new ArrayList<>();


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
                Background b = this.world.get_background(curr_pt);
                screen.image(background_image.get(b.get_current_img()), (x-ref_pt.get_x()) * 32, (y-ref_pt.get_y()) * 32);
            }
        }
    }

    public void draw_entities()
    {
        for(int x=ref_pt.get_x(); x < (num_cols+ref_pt.get_x()); x++)
        {
            for(int y=ref_pt.get_y();y < (num_rows+ref_pt.get_y());y++)
            {
                Background b = this.world.get_background(new Point(x, y));

                Point curr_world_pt = new Point(x, y);
                Point curr_view_pt = world_to_viewport(curr_world_pt);
                screen.image(background_image.get(b.get_current_img()),
                        curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
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
                    else if(e instanceof Obstacle){
                        screen.image(obstacle_image.get(0),
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
                    } 
					/*else {
                        screen.image(background_image.get(e.get_current_img()),
						curr_view_pt.get_x() * 32, curr_view_pt.get_y() * 32);
						} */
				}
			}
		}
	}
               
            
    public boolean update_on_time(long start, WorldModel world)
	{
        boolean updated = false;
        for (Entity e : world.get_entities())
		{
            if (e instanceof AnimatedActor)
			{

                if (!update_each(start, (AnimatedActor) e))
				{
                    updated = false;
				}
                else { updated = true; }
			}
		}
        return updated;

	}

    public boolean update_each(long start, AnimatedActor e)
	{
        if (start + e.get_animation_rate() < System.currentTimeMillis())
		{
            if (e.get_current_img() == e.imgs - 1)
			{
                e.to_img(0);
			}
            else 
			{
                e.to_img(e.get_current_img() + 1);
			}
            return true;
		}
        return false;
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

    private static final int COLOR_MASK = 0xffffff;

    // Called with color for which alpha should be set and alpha value.
    //PImage img = setAlpha(loadImage("wyvern1.bmp"), color(255, 255, 255), 0));
    private static PImage setAlpha(PImage img, int maskColor, int alpha)
    {
       int alphaValue = alpha << 24;
       int nonAlpha = maskColor & COLOR_MASK;
       img.format = PApplet.ARGB;
       img.loadPixels();
       for (int i = 0; i < img.pixels.length; i++)
       {
          if ((img.pixels[i] & COLOR_MASK) == nonAlpha)
          {
             img.pixels[i] = alphaValue | nonAlpha;
          }
       }
       img.updatePixels();
       return img;
    }


    public void image_init() {
       
        obstacle_image.add(loadImage("obstacle.bmp"));

        background_image.add(loadImage("grass.bmp"));
        background_image.add(loadImage("rock.bmp"));

        miner_images.add(setAlpha(loadImage("miner1.bmp"), 
            color(252, 252, 252), 0));
        miner_images.add(setAlpha(loadImage("miner2.bmp"), 
            color(252, 252, 252), 0));
        miner_images.add(setAlpha(loadImage("miner3.bmp"), 
            color(252, 252, 252), 0));
        miner_images.add(setAlpha(loadImage("miner4.bmp"), 
            color(252, 252, 252), 0));
        miner_images.add(setAlpha(loadImage("miner5.bmp"), 
            color(252, 252, 252), 0));

        vein_image.add(setAlpha(loadImage("vein.bmp"),
								color(201, 26, 26), 0));

        oreblob_images.add(setAlpha(loadImage("blob1.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob1.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob2.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob3.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob4.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob5.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob6.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob7.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob8.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob9.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob10.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob11.bmp"), 
            color(255, 255, 255), 0));
        oreblob_images.add(setAlpha(loadImage("blob12.bmp"), 
            color(255, 255, 255), 0));


        ore_image.add(setAlpha(loadImage("ore.bmp"), color(201, 26, 26), 0));

        quake_images.add(setAlpha(loadImage("quake1.bmp"), 
            color(255, 255, 255), 0));
        quake_images.add(setAlpha(loadImage("quake2.bmp"), 
            color(255, 255, 255), 0));
        quake_images.add(setAlpha(loadImage("quake3.bmp"), 
            color(255, 255, 255), 0));
        quake_images.add(setAlpha(loadImage("quake4.bmp"), 
            color(255, 255, 255), 0));
        quake_images.add(setAlpha(loadImage("quake5.bmp"), 
            color(255, 255, 255), 0));
        quake_images.add(setAlpha(loadImage("quake6.bmp"), 
            color(255, 255, 255), 0));


        blacksmith_image.add(loadImage("blacksmith.bmp"));
    }



}
