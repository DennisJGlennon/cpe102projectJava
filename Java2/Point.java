    public class Point
    {
        private int x;
        private int y;
     
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
     
        //is the entity adjacent to the 
        public boolean adjacent(Point pt2)
        {
            return ((this.x == pt2.get_x() && Math.abs(this.y - pt2.get_y()) == 1) ||
                    (this.y == pt2.get_y() && Math.abs(this.x - pt2.get_x()) == 1));
        }
     
        public int get_x()
        {
            return this.x;
        }
     
        public int get_y()
        {
            return this.y;
        }
     
    }

