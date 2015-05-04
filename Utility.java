import java.lang.Number;
import java.util.List;

public class Utility
{
	
	public static int distance_sq(Point p1, Point p2)
	{	
	        double dist_temp = Math.pow(p1.get_x() - p2.get_x(), 2) + 
                    Math.pow(p1.get_y() - p2.get_y(), 2);
		return (int) dist_temp;
	}

	public static int nearest_entity(List<Integer> distances)
	{
		int closest_index = 0;
		if (distances.size() > 0)
		{
			for(int i=0;i < distances.size();i++)
			{
			    if(distances.get(i) < distances.get(closest_index))
				{
					closest_index = i;
				}
			}
		}
		return closest_index;
	}

	public static int sign(int x)
	{
		if(x < 0)
		{
			return -1;
		}
		else if(x > 0)
		{
			return 1;
		}
		else
			return 0;
	}
}
