import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Scanner;

public class Reader
{
    public Reader(WorldModel world)
	{
        Scanner s = null;
        PrintWriter outStream = null;

        try (BufferedReader b = new BufferedReader(new FileReader("gaia.sav")))
	    {
            String line;
            String [] words;

            while ((line = b.readLine()) != null)
		    {

                words = line.split(" ");

                if (words[0].equals("background"))
				{
			        if (words[1].equals("grass"))
					{
                        //world.add_entity_at(new Background(words[0], 0),                                                                 newpt(words));
					}
                    else 
				    {
                        world.add_entity_at(new Background(words[0], 1), 
													   newpt(words));
					}
				}
                if (words[0].equals("miner"))
			    {
                    world.add_entity_at(
                         new MinerNotFull(words[0], 0, newpt(words), 
                         Integer.parseInt(words[5]), Integer.parseInt(words[6]),                          Integer.parseInt(words[4])), newpt(words));
				}
					  
				if (words[0].equals("vein"))
				{
                    world.add_entity_at(
                         new Vein(words[0], 0, newpt(words), 
								  Integer.parseInt(words[4]), 
                                  Integer.parseInt(words[5])), newpt(words));
				}
                if (words[0].equals("blacksmith"))
				{
                    world.add_entity_at(new Blacksmith(words[0], 0,                                                    newpt(words)), newpt(words));
				}
                if (words[0].equals("obstacle"))
				{
                    world.add_entity_at(new Obstacle(words[0], 0,                                                    newpt(words)), newpt(words));
				}


					   
			}
		}
        catch (Exception e)
			{
                System.out.println("wah");
			}
	}


	public Point newpt(String [] w)
	{
        return new Point(Integer.parseInt(w[2]), Integer.parseInt(w[3]));
	}
}