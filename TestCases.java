import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;

public class TestCases
{
    Point[][] points = make_points();


    Entity ent = new Entity("abstract", "ent test");
    Background back = new Background("back");
    CoordEntity cent = new CoordEntity("cent", points[0][0], "coord test");
    Blacksmith smith = new Blacksmith("smith", points[1][2], 3);
    Ore ore = new Ore("ore", points[8][0]);
    Quake quake = new Quake("quake", points[3][1]);
    Obstacle obs = new Obstacle("obs", points[4][7]);
    Vein vein = new Vein("vein",  points[4][7]);
    OreBlob blob = new OreBlob("blob", points[1][2]);
    Miner miner = new Miner("miner", points[5][1], 10, 3, "miner test");
    MinerNotFull notfull = new MinerNotFull("notfull", 8, points[4][4]); 
    MinerFull full = new MinerFull("full", 6, points[6][6]);

    WorldModel world = new WorldModel(10, 10, back);
    Grid grid = new Grid(10, 10, ent);


    //Grid grid = new Grid(10, 10, ent);
     
    private boolean comp_pts(Point p1, Point p2)
    {
        return p1.get_x() == p2.get_x() && p1.get_y() == p2.get_y();
    }

    private boolean comp_ents(Entity e1, Entity e2)
    {
        return e1.get_name() == e2.get_name();
    }

 

    private Point[][]  make_points()
    {
        Point[][] b = new Point[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int o = 0; o < 10; o++)
            {
                b[i][o] = new Point(i + 1, o + 1);
	    }
        }
        return b;
    }

    
    
    @Test
    public void testUtility()
    {
        int dist = Utility.distance_sq(points[1][1], points[3][3]);
        assertEquals(dist, 8);
        List<Integer> dists = Arrays.asList(5, 2, 6, 3, 1, 4);
        assertEquals(Utility.nearest_entity(dists), 4);
        assertEquals(Utility.sign(-12), -1);
    }
    @Test
    public void testPoint()
    {
        assertTrue(points[0][0].adjacent(points[0][1]));
        assertEquals(points[3][7].get_x(), 4);
        assertEquals(points[2][1].get_y(), 2);
    }

    @Test
    public void testEntity()
    {
        assertEquals(ent.get_name(), "abstract");
        assertEquals(ent.entity_string(), "ent test");
    }

    @Test
    public void testBackground()
    {
        assertEquals(back.get_name(), "back");
        assertEquals(back.entity_string(), "unknown");
    }

    @Test
    public void testCoordEntity()
    {
        assertEquals(cent.get_name(), "cent");
        assertEquals(cent.entity_string(), "coord test");
        Point p1 = new Point(1, 1);
        assertTrue(comp_pts(cent.get_position(), p1));
        Point p2 = new Point(3, 5);
        cent.set_position(p2);
        assertTrue(comp_pts(cent.get_position(), p2));
    }

    @Test
    public void testBlacksmith()
    {
        assertEquals(smith.get_name(), "smith");
        assertEquals(smith.entity_string(), "blacksmith");
        Point p1 = new Point(2, 3);
        assertTrue(comp_pts(smith.get_position(), p1));
        Point p2 = new Point(3, 5);
        smith.set_position(p2);
        assertTrue(comp_pts(smith.get_position(), p2));
    }

    @Test
    public void testOre()
    {
        assertEquals(ore.get_name(), "ore");
        assertEquals(ore.entity_string(), "ore");
        Point p1 = new Point(9, 1);
        assertTrue(comp_pts(ore.get_position(), p1));
        Point p2 = new Point(3, 5);
        ore.set_position(p2);
        assertTrue(comp_pts(ore.get_position(), p2));
    }

    @Test
    public void testQuake()
    {
        assertEquals(quake.get_name(), "quake");
        assertEquals(quake.entity_string(), "unknown");
        Point p1 = new Point(4, 2);
        assertTrue(comp_pts(quake.get_position(), p1));
        Point p2 = new Point(1, 6);
        quake.set_position(p2);
        assertTrue(comp_pts(quake.get_position(), p2));
    }

    @Test
    public void testObstacle()
    {
        assertEquals(obs.get_name(), "obs");
        assertEquals(obs.entity_string(), "obstacle");
        Point p1 = new Point(5, 8);
        assertTrue(comp_pts(obs.get_position(), p1));
        Point p2 = new Point(3, 5);
        obs.set_position(p2);
        assertTrue(comp_pts(obs.get_position(), p2));
    }

    @Test
    public void testVein()
    {
        assertEquals(vein.get_name(), "vein");
        assertEquals(vein.entity_string(), "vein");
        Point p1 = new Point(5, 8);
        assertTrue(comp_pts(vein.get_position(), p1));
        Point p2 = new Point(3, 5);
        vein.set_position(p2);
        assertTrue(comp_pts(vein.get_position(), p2));
    }

    @Test
    public void testOreBlob()
    {
        assertEquals(blob.get_name(), "blob");
        assertEquals(blob.entity_string(), "unknown");
        Point p1 = new Point(2, 3);
        assertTrue(comp_pts(blob.get_position(), p1));
        Point p2 = new Point(3, 5);
        blob.set_position(p2);
        assertTrue(comp_pts(blob.get_position(), p2));
    }

    @Test
    public void testMiner()
    {
        assertEquals(miner.get_name(), "miner");
        assertEquals(miner.entity_string(), "miner test");
        Point p1 = new Point(6, 2);
        assertTrue(comp_pts(miner.get_position(), p1));
        Point p2 = new Point(3, 5);
        miner.set_position(p2);
        assertTrue(comp_pts(miner.get_position(), p2));
	//("miner", points[5][1], 10, 3, "unknwown"); 
        assertEquals(miner.get_resource_limit(), 10);
        assertEquals(miner.get_resource_count(), 3);
        miner.set_resource_count(5);
        assertEquals(miner.get_resource_count(), 5);        
    }

    @Test
    public void testMinerNotFull()
    {
        assertEquals(notfull.get_name(), "notfull");
        assertEquals(notfull.entity_string(), "miner");
        Point p1 = new Point(5, 5);
        assertTrue(comp_pts(notfull.get_position(), p1));
        Point p2 = new Point(3, 5);
        notfull.set_position(p2);
        assertTrue(comp_pts(notfull.get_position(), p2));
	//("miner", points[5][1], 10, 3, "unknwown"); 
        assertEquals(notfull.get_resource_limit(), 8);
        assertEquals(notfull.get_resource_count(), 0);
        notfull.set_resource_count(5);
        assertEquals(notfull.get_resource_count(), 5);        
    }

    @Test
    public void testMinerFull()
    {
        assertEquals(full.get_name(), "full");
        assertEquals(full.entity_string(), "unknown");
        Point p1 = new Point(7, 7);
        assertTrue(comp_pts(full.get_position(), p1));
        Point p2 = new Point(3, 5);
        full.set_position(p2);
        assertTrue(comp_pts(full.get_position(), p2)); 
        assertEquals(full.get_resource_limit(), 6);
        assertEquals(full.get_resource_count(), 6);
        full.set_resource_count(5);
        assertEquals(full.get_resource_count(), 5);        
    }

    @Test
    public void testGrid()
    {
        assertTrue(comp_ents(grid.get_cell(points[2][3]), ent));
        grid.set_cell(points[4][5], miner);
        assertTrue(comp_ents(grid.get_cell(points[4][5]), miner));
    }

    @Test
    public void testWorldModel()
    {
        assertTrue(world.within_bounds(points[3][3]));
        world.add_entity(obs);
        assertTrue(comp_ents(world.get_tile_occupant(points[4][7]), obs));
        assertTrue(world.is_occupied(points[4][7]));
        Point pos = obs.get_position();
        Point [] change = world.move_entity(obs, points[5][7]);
        assertTrue(pos == change[0] && obs.get_position() == points[5][7]);
        world.remove_entity(obs);
        assertTrue(!world.is_occupied(points[5][7]));
        assertTrue(comp_ents(world.get_background(points[2][1]), back));
        Background new_back = new Background("new back");
        world.set_background(points[6][8], new_back);
        assertTrue(comp_ents(world.get_background(points[6][8]), new_back));
        world.add_entity(full);
        world.add_entity(miner);
        world.add_entity(blob);
        List<Entity> entities = world.get_entities();
        List<Entity> e = Arrays.asList(full,  miner, blob);  
        assertEquals(entities, e); 
    }

    @Test
    public void test_find_open_around()
    {
        WorldModel w = new WorldModel(10, 10, back);
        for (int i = 0; i < 3; i++)
	{
            for (int o = 0; o < 3; o++)
	    {
                Obstacle ob = new Obstacle("obs", points[o + 3][i + 3]);
                w.add_entity(ob);
	    }
	}
        w.remove_entity_at(points[4][4]);
        Vein v = new Vein("vain", points[4][4]);
        w.add_entity(v);
        Point null_pt = new Point(-1, -1);
        assertTrue(comp_pts(v.find_open_around(w, 1), null_pt));
        w.remove_entity_at(points[4][3]);
        assertTrue(comp_pts(v.find_open_around(w, 1), points[4][3]));
    }

    @Test
    public void test_blob_next_position()
    {
        WorldModel w = new WorldModel(10, 10, back);
        OreBlob b = new OreBlob("blob", points[4][0]);
        w.add_entity(b);
        assertTrue(comp_pts(b.blob_next_position(w, points[5][8]), points[5][0]));
    }

    @Test
    public void test_next_position()
    {
        WorldModel w = new WorldModel(10, 10, back);
        Miner m = new Miner("miner", points[0][4], 10, 3, "ding");
        w.add_entity(m);
        assertTrue(comp_pts(m.next_position(w, points[8][5]), points[1][4]));
    }

    @Test
    public void test1_find_nearest()
    {
        WorldModel w = new WorldModel(10,10,back);
        CoordEntity e1 = new MinerNotFull("miner1", 120, new Point(2,3));
        CoordEntity e2 = new MinerNotFull("miner2", 120, new Point(1,9));
        CoordEntity e3 = new Vein("vein1", new Point(1,2));

        w.add_entity(e1);
        w.add_entity(e2);
        w.add_entity(e3);

        Point check_point = new Point(1,1);

        Entity miner_check = w.find_nearest(check_point,e1.getClass()); //e1.get_class is a minernotfull

        assertTrue(miner_check == e1); //exact same object
    }

    @Test
    public void test2_find_nearest()
    {
        WorldModel w = new WorldModel(10,10,back);
        CoordEntity e1 = new MinerFull("miner1", 120, new Point(8,9));
        CoordEntity e2 = new MinerFull("miner2", 90, new Point(1,9));
        CoordEntity e3 = new MinerNotFull("miner3", 130, new Point(9,9));

        w.add_entity(e1);
        w.add_entity(e2);
        w.add_entity(e3);

        Point check_point = new Point(9,9);

        Entity miner_check = w.find_nearest(check_point,e1.getClass());

        assertTrue(miner_check == e1); //exact same object
    }

    @Test
    public void test3_find_nearest()
    {
        WorldModel w = new WorldModel(10,10,back);
        CoordEntity e2 = new OreBlob("oreblob1", new Point(8,9));
        CoordEntity e1 = new MinerFull("miner2", 90, new Point(1,9));
        CoordEntity e3 = new MinerNotFull("miner2", 130, new Point(9,9));

        w.add_entity(e1);
        w.add_entity(e2);
        w.add_entity(e3);

        Point check_point = new Point(5,5);

        Entity miner_check = w.find_nearest(check_point,e2.getClass());

        assertTrue(miner_check == e2); //exact same object
    }
}
