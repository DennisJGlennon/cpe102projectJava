import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class WorldModel
{
	/*public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;	
	*/
	private Grid background; 
	private int num_rows;
	private int num_cols;
	private Grid occupancy;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public WorldModel(int num_rows, int num_cols, Background default_background)
	{
	    this.background = new Grid(num_cols,num_rows, (Entity) default_background);
		this.num_rows = num_rows;
		this.num_cols = num_cols;
		this.occupancy = new Grid(num_cols, num_rows,null);	
	}
	
	public boolean within_bounds(Point pt)
	{
	    return ((pt.get_x() >= 0 && pt.get_x() < this.num_cols) && 
		    (pt.get_y() >= 0 && pt.get_y() < this.num_rows));
	}
	
	public boolean is_occupied(Point pt)
	{
		return (this.within_bounds(pt) && this.occupancy.get_cell(pt) 
                       != null);
	}

    public Entity find_nearest(Point pt, Class type)
	{
		LinkedList<Entity> oftype = new LinkedList<>();
		LinkedList<Integer> distances = new LinkedList<>();

		for(int i=0;i < entities.size();i++)
		{
		    if (type.isInstance(entities.get(i)))
			{
			    oftype.add(entities.get(i));
                            CoordEntity coord_ent = (CoordEntity) 
                                entities.get(i);
			    distances.add(Utility.distance_sq(
                                coord_ent.get_position(), pt));
			}
		}
		int nearest_index = Utility.nearest_entity(distances);
		return oftype.get(nearest_index);
		}

	public void add_entity(CoordEntity e)
	{
		Entity old_entity;
		Point pt = e.get_position();
		if (this.within_bounds(pt))
		{
			old_entity = this.occupancy.get_cell(pt);
			//if(old_entity != null) {
				//old_entity.clear_pending_actions()
			//}
			this.occupancy.set_cell(pt,e);
			this.entities.add(e);
		}
	}

	// Return type is not used in this assignment
	public Point [] move_entity(CoordEntity e, Point pt)
	{
		Point old_pt = e.get_position();
		if(this.within_bounds(pt))
		{

			this.occupancy.set_cell(old_pt, null);
			this.occupancy.set_cell(pt, e);
			e.set_position(pt);
			Point [] tiles = {old_pt, pt};
                        return tiles;
		}
                Point [] tiles = {old_pt, old_pt};
                return tiles;
	}

	public void remove_entity(CoordEntity e)
	{
		this.remove_entity_at(e.get_position());
	}

	public void remove_entity_at(Point pt)
	{
		if(this.within_bounds(pt) && this.occupancy.get_cell(pt) != null)
		{
			Point out_of_bounds = new Point(-1,-1);
			// this is bad, fix this later. not robust, etc
			CoordEntity e = (CoordEntity) this.occupancy.get_cell(pt); 
			e.set_position(out_of_bounds);
			this.entities.remove(e);
			this.occupancy.set_cell(pt,null);
		}
	}

	public Background get_background(Point pt)
	{
		if(this.within_bounds(pt))
		{
                    //this might be bad for same reasons. not sure
		    return (Background) this.background.get_cell(pt);
		}
			return null;
	}

	public void set_background(Point pt, Background b)
	{
		if(this.within_bounds(pt))
		{
			this.background.set_cell(pt,b);
		}
	}

	public Entity get_tile_occupant(Point pt)
	{
		if(this.within_bounds(pt))
		{
			return this.occupancy.get_cell(pt);
		}
		return null;
	}

	public List<Entity> get_entities()
	{
		return this.entities;
	}
}
