public class Grid
{
    Entity[][] cells;
    
    public Grid(int width, int height, Entity occupancy_value)
    {
        cells = new Entity[height][width];
        //this.width = width;
        //this.height = height;
        
        // initialize grid with given entity
        for(int curr_height=0; curr_height < height; curr_height++)
        {
            for(int curr_width=0; curr_width < width; curr_width++)
            {
                this.cells[curr_height][curr_width] = 
                                    occupancy_value;
            }
        }
    }
    
    public void set_cell(Point p, Entity e)
    {
        cells[p.get_y()][p.get_x()] = e;
    }
    
    public Entity get_cell(Point p)
    {
        return cells[p.get_y()][p.get_x()];
    }
}
