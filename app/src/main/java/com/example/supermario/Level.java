package com.example.supermario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
// Commit on Saturday, June 8
class Level
{
    Bitmap background;
    Item grid[][];
    Surface[] surfaces;
    public void initializeGrid()
    {

    }
    public Item checkMarioPosition(int x, int y)
    {
        return null;
    }
    public boolean checkItemBelow(int x, int y)
    {
        if(grid[x/100][(y+100)/100] != null)
        {
            return true;
        }
        return false;
    }
    public void setGrid(Item[][] grid)
    {

    }
    public void drawGrid(int x)
    {
    }
    public Item[][] getGrid()
    {
        return grid;
    }
    public Bitmap getBackground()
    {
        return background;
    }
    public void eraseItem(int x, int y)
    {
    }
    public void addItem(int x, int y, Item item)
    {

    }
    public Surface[] getSurfaces()
    {
        return surfaces;
    }
    public boolean rangeContains(int num, int lower, int upper)
    {
        if(num >= lower && num <= upper)
        {
            return true;
        }
        return false;
    }


}
class Surface
{
    int xMin;
    int xMax;
    int y;
    public Surface(int xMin, int xMax, int y)
    {
        this.xMin = xMin;
        this.xMax = xMax;
        this.y = y;
    }
}
class Level1 extends Level
{
    Canvas canvas;
    Bitmap background;
    int height, width;
    Context context;
    Item grid[][];
    Surface[] surfaces;
    int xSize, ySize;

    public Level1(Canvas canvas, Context context) {
        this.canvas = canvas;
        this.context = context;
        height = canvas.getHeight();
        width = canvas.getWidth();
        grid = new Item[(width*3)/100][height/100];
        xSize = (width*3)/100;
        ySize = height/100;
        this.background = BitmapFactory.decodeResource(context.getResources(), R.drawable.level1);
        surfaces = new Surface[20];
    }
    public void initializeGrid() {
        int currentsurface = 0;
        int startx = (width/3)/100;
        int starty = (height/2)/ 100;
        grid[startx][starty+1] = new Brick(context, (startx)*100, (starty+1)*100);
        grid[startx+1][starty+1] = new Question(context, (startx+1)*100, (starty+1)*100, "coin");
        grid[startx+2][starty+1] = new Brick(context, (startx+2)*100, (starty+1)*100);
        grid[startx+3][starty+1] = new Question(context, (startx+3)*100, (starty+1)*100, "mushroom");
        grid[startx+4][starty+1] = new Brick(context, (startx+4)*100, (starty+1)*100);
        surfaces[currentsurface] = new Surface(startx*100, (startx+4)*100, (starty+1)*100);
        currentsurface++;
        for (int i = startx+65; i < startx + 70; i++)
        {
            for(int j = starty+1; j < starty+2; j++)
            {
                grid[i][j] = new Brick(context, i*100, j*100);
            }
        }
        surfaces[currentsurface] = new Surface((startx+60)*100, (startx+64)*100, (starty+1)*100);
        currentsurface++;
        grid[startx][starty-2] = new Question(context, startx*100, (starty-2)*100, "coin");
        grid[startx+1][starty-2] = new Question(context, (startx+1)*100, (starty-2)*100, "coin");
        grid[startx][starty+3] = new Goomba(context, startx*100, canvas.getHeight()-370, 2400);
        grid[startx+10][starty+1] = new Question(context, (startx+10)*100, (starty+1)*100, "mushroom");
        surfaces[currentsurface] = new Surface((startx+10)*100, (startx+11)*100, (starty+1)*100);
        currentsurface++;
        grid[startx+35][starty+1] = new Question(context, (startx+35)*100, (starty+1)*100, "starman");
        surfaces[currentsurface] = new Surface((startx+35)*100, (startx+36)*100, (starty+1)*100);
        currentsurface++;
        grid[startx+31][starty+5] = new Piranha(context, (startx+31)*100, canvas.getHeight()-570, startx+31, starty+5);
        grid[startx+30][starty+3] = new SmallPipe(context, (startx+30)*100, canvas.getHeight()-370);
        grid[startx+54][starty+3] = new Goomba(context, (startx+54)*100, canvas.getHeight()-370, (startx+58)*100);
        surfaces[currentsurface] = new Surface((startx+30)*100, (startx+33)*100, (starty+3)*100);
        currentsurface++;
        grid[startx+50][starty+3] = new SmallPipe(context, (startx+50)*100, canvas.getHeight()-370);
        surfaces[currentsurface] = new Surface((startx+50)*100, (startx+53)*100, (starty+3)*100);
        currentsurface++;
       grid[startx+59][starty+5] = new Piranha(context, (startx+59)*100, canvas.getHeight()-570, startx+59, starty+5);
       grid[startx+58][starty+3] = new SmallPipe(context, (startx+58)*100, canvas.getHeight()-370);
       surfaces[currentsurface] = new Surface((startx+58)*100, (startx+61)*100, (starty+3)*100);
      currentsurface++;


    }
    public Item checkMarioPosition(int x, int y)
    {
        if(x >= width*3)
        {
            return null;
        }
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                if(grid[(x+i)/100][(y+j)/100] != null && !(grid[(x+i)/100][(y+j)/100] instanceof Goomba))
                {
                    return grid[(x+i)/100][(y+j)/100];
                }

            }
        }
        return null;
    }
    public boolean checkItemBelow(int x, int y)
    {
        if(y+100 >= height)
        {
            return true;
        }
        if(x/100 >= xSize || y/100 >= ySize)
        {
            return false;
        }
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j < height/100; j++)
            {
                if(grid[i][j] != null && !(grid[i][j] instanceof Goomba))
                {
                    int lX = grid[i][j].getLowerX();
                    int lY = grid[i][j].getLowerY();
                    int uX = grid[i][j].getUpperX();
                    int uY = grid[i][j].getUpperY();
                    if(super.rangeContains(x, lX, uX) && super.rangeContains(y+100, lY, uY))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setGrid(Item[][] grid)
    {
        this.grid = grid;
    }
    public void drawGrid(int x)
    {
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j < height/100; j++)
            {
                if(grid[i][j] != null && !(grid[i][j] instanceof Piranha))
                {
                    if(grid[i][j].getX()-x >= 0 && grid[i][j].getX()-x <= width)
                    {
                        grid[i][j].drawItem(canvas, grid[i][j].getX()-x, grid[i][j].getY());
                    }
                }
            }
        }
        Bitmap hill = BitmapFactory.decodeResource(context.getResources(), R.drawable.hill);
        Rect r = new Rect();
        r.set(0-x, canvas.getHeight()-600, 600-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r, null);
        Rect r2 = new Rect();
        r2.set(canvas.getWidth()-600-x, canvas.getHeight()-600, canvas.getWidth()-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r2, null);
        Rect r3 = new Rect();
        r3.set((2*canvas.getWidth()-600)-x, canvas.getHeight()-600, (2*canvas.getWidth())-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r3, null);
        Rect r4 = new Rect();
        r4.set((3*canvas.getWidth()-600)-x, canvas.getHeight()-600, (3*canvas.getWidth())-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r4, null);
    }
    public Bitmap getBackground()
    {
        return background;
    }
    public Item[][] getGrid()
    {
        return grid;
    }
    public void eraseItem(int x, int y)
    {
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j <  height/100; j++)
            {
                if(grid[i][j] != null)
                {
                    if(grid[i][j].getX() == x && grid[i][j].getY() == y)
                    {
                        grid[i][j] = null;
                    }
                }
            }
        }
    }
    public void addItem(int x, int y, Item item)
    {
        grid[x][y] = item;
    }
    public Surface[] getSurfaces()
    {
        return surfaces;
    }
}
class Level2 extends Level
{
    Canvas canvas;
    Bitmap background;
    int height, width, xSize, ySize;
    Context context;
    Item grid[][];
    Surface[] surfaces;
    public Level2(Canvas canvas, Context context) {
        this.canvas = canvas;
        this.context = context;
        height = canvas.getHeight();
        width = canvas.getWidth();
        grid = new Item[(width*3)/100][height/100];
        xSize = (width*3)/100;
        ySize = height/100;
        this.background = BitmapFactory.decodeResource(context.getResources(), R.drawable.level2);
        surfaces = new Surface[20];
    }
    public void initializeGrid() {
        int currentsurface = 0;
        int startx = (width/3)/100;
        int starty = (height/2)/ 100;
        grid[startx][starty+1] = new Question(context, (startx)*100, (starty+1)*100, "coin");
        grid[startx+1][starty+1] = new Question(context, (startx+1)*100, (starty+1)*100, "coin");
        grid[startx+2][starty+1] = new Brick(context, (startx+2)*100, (starty+1)*100);
        grid[startx+3][starty+1] = new Question(context, (startx+3)*100, (starty+1)*100, "coin");
        surfaces[currentsurface] = new Surface(startx*100, (startx+3)*100, (starty+1)*100);
        currentsurface++;
        for (int i = startx+60; i < startx + 65; i++)
        {
            for(int j = starty+1; j < starty+2; j++)
            {
                grid[i][j] = new Brick(context, i*100, j*100);
            }
        }
        surfaces[currentsurface] = new Surface((startx+60)*100, (startx+64)*100, (starty+1)*100);
        currentsurface++;
        grid[startx][starty-2] = new Question(context, startx*100, (starty-2)*100, "coin");
        grid[startx+1][starty-2] = new Question(context, (startx+1)*100, (starty-2)*100, "starman");
        grid[startx][starty+3] = new Goomba(context, startx*100, canvas.getHeight()-370, 2400);
        grid[startx+40][starty+3] = new Goomba(context, (startx+40)*100, canvas.getHeight()-370, (startx+50)*100);
        grid[startx+10][starty+1] = new Question(context, (startx+10)*100, (starty+1)*100, "coin");
        surfaces[currentsurface] = new Surface((startx+10)*100, (startx+11)*100, (starty+1)*100);
        currentsurface++;
        grid[startx+20][starty+3] = new SmallPipe(context, (startx+20)*100, canvas.getHeight()-370);
        surfaces[currentsurface] = new Surface((startx+20)*100, (startx+23)*100, (starty+3)*100);
        currentsurface++;
        grid[startx+35][starty+1] = new Question(context, (startx+35)*100, (starty+1)*100, "starman");
        grid[startx+36][starty+1] = new Question(context, (startx+36)*100, (starty+1)*100, "mushroom");
        surfaces[currentsurface] = new Surface((startx+35)*100, (startx+36)*100, (starty+1)*100);
        currentsurface++;
        grid[startx+30][starty+3] = new SmallPipe(context, (startx+30)*100, canvas.getHeight()-370);
        surfaces[currentsurface] = new Surface((startx+30)*100, (startx+33)*100, (starty+3)*100);
        currentsurface++;
        grid[startx+35][starty-2] = new Question(context, (startx+35)*100, (starty-2)*100, "coin");
        grid[startx+36][starty-2] = new Question(context, (startx+36)*100, (starty-2)*100, "coin");
        surfaces[currentsurface] = new Surface((startx+35)*100, (startx+36)*100, (starty+3)*100);
        currentsurface++;
        grid[startx+68][starty+5] = new Piranha(context, (startx+68)*100, canvas.getHeight()-570, startx+68, starty+5);
        grid[startx+67][starty+3] = new SmallPipe(context, (startx+67)*100, canvas.getHeight()-370);
        surfaces[currentsurface] = new Surface((startx+67)*100, (startx+70)*100, (starty+3)*100);
        currentsurface++;


    }
    public Item checkMarioPosition(int x, int y)
    {
        if(x >= width*3)
        {
            return null;
        }
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                if(grid[(x+i)/100][(y+j)/100] != null && !(grid[(x+i)/100][(y+j)/100] instanceof Goomba))
                {
                    return grid[(x+i)/100][(y+j)/100];
                }

            }
        }
        return null;
    }
    public boolean checkItemBelow(int x, int y)
    {
        if(y+100 >= height)
        {
            return true;
        }
        if(x/100 >= xSize || y/100 >= ySize)
        {
            return false;
        }
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j < height/100; j++)
            {
                if(grid[i][j] != null && !(grid[i][j] instanceof Goomba) && !(grid[i][j] instanceof Piranha))
                {
                    int lX = grid[i][j].getLowerX();
                    int lY = grid[i][j].getLowerY();
                    int uX = grid[i][j].getUpperX();
                    int uY = grid[i][j].getUpperY();
                    if(super.rangeContains(x, lX, uX) && super.rangeContains(y+100, lY, uY))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setGrid(Item[][] grid)
    {
        this.grid = grid;
    }
    public void drawGrid(int x)
    {
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j < height/100; j++)
            {
                if(grid[i][j] != null && !(grid[i][j] instanceof Piranha))
                {
                    if(grid[i][j].getX()-x >= 0 && grid[i][j].getX()-x <= width)
                    {
                        grid[i][j].drawItem(canvas, grid[i][j].getX()-x, grid[i][j].getY());
                    }
                }
            }
        }
        Bitmap hill = BitmapFactory.decodeResource(context.getResources(), R.drawable.hill);
        Rect r = new Rect();
        r.set(0-x, canvas.getHeight()-600, 600-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r, null);
        Rect r2 = new Rect();
        r2.set(canvas.getWidth()-600-x, canvas.getHeight()-600, canvas.getWidth()-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r2, null);
        Rect r3 = new Rect();
        r3.set((2*canvas.getWidth()-600)-x, canvas.getHeight()-600, (2*canvas.getWidth())-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r3, null);
    }
    public Bitmap getBackground()
    {
        return background;
    }
    public Item[][] getGrid()
    {
        return grid;
    }
    public void eraseItem(int x, int y)
    {
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j <  height/100; j++)
            {
                if(grid[i][j] != null)
                {
                    if(grid[i][j].getX() == x && grid[i][j].getY() == y)
                    {
                        grid[i][j] = null;
                    }
                }
            }
        }
    }
    public void addItem(int x, int y, Item item)
    {
        this.grid[x][y] = item;
    }
    public Surface[] getSurfaces()
    {
        return surfaces;
    }
}
class Level3 extends Level
{
    Canvas canvas;
    Bitmap background;
    int height, width, xSize, ySize;
    Context context;
    Item grid[][];
    Surface[] surfaces;
    public Level3(Canvas canvas, Context context) {
        this.canvas = canvas;
        this.context = context;
        height = canvas.getHeight();
        width = canvas.getWidth();
        grid = new Item[(width*3)/100][height/100];
        xSize = (width*3)/100;
        ySize = height/100;
        this.background = BitmapFactory.decodeResource(context.getResources(), R.drawable.level3);
        surfaces = new Surface[20];
    }
    public void initializeGrid() {
        int currentsurface = 0;
        int startx = (width/3)/100;
        int starty = (height/2)/ 100;
        grid[startx][starty+1] = new Brick(context, (startx)*100, (starty+1)*100);
        grid[startx+1][starty+1] = new Brick(context, (startx+1)*100, (starty+1)*100);
        grid[startx+2][starty+1] = new Brick(context, (startx+2)*100, (starty+1)*100);
        grid[startx+3][starty+1] = new Brick(context, (startx+3)*100, (starty+1)*100);
        grid[startx+4][starty+1] = new Question(context, (startx+4)*100, (starty+1)*100, "starman");
        surfaces[currentsurface] = new Surface(startx*100, (startx+4)*100, (starty+1)*100);
        currentsurface++;
        for (int i = startx+60; i < startx + 65; i++)
        {
            for(int j = starty+1; j < starty+2; j++)
            {
                grid[i][j] = new Brick(context, i*100, j*100);
            }
        }
        surfaces[currentsurface] = new Surface((startx+60)*100, (startx+64)*100, (starty+1)*100);
        currentsurface++;
        grid[startx][starty-2] = new Question(context, startx*100, (starty-2)*100, "coin");
        grid[startx+1][starty-2] = new Question(context, (startx+1)*100, (starty-2)*100, "coin");
        grid[startx+2][starty-2] = new Question(context, (startx+2)*100, (starty-2)*100, "coin");
        grid[startx+4][starty-2] = new Question(context, (startx+4)*100, (starty-2)*100, "mushroom");
        grid[startx+3][starty-2] = new Question(context, (startx+3)*100, (starty-2)*100, "coin");
        grid[startx][starty+3] = new Goomba(context, startx*100, canvas.getHeight()-370, 2400);
        grid[startx+40][starty+3] = new Goomba(context, (startx+40)*100, canvas.getHeight()-370, (startx+50)*100);
        grid[startx+10][starty+1] = new Question(context, (startx+10)*100, (starty+1)*100, "mushroom");
        surfaces[currentsurface] = new Surface((startx+10)*100, (startx+11)*100, (starty+1)*100);
        currentsurface++;
        grid[startx+35][starty+1] = new Question(context, (startx+35)*100, (starty+1)*100, "coin");
        surfaces[currentsurface] = new Surface((startx+35)*100, (startx+36)*100, (starty+1)*100);
        currentsurface++;
        grid[startx+31][starty+5] = new Piranha(context, (startx+31)*100, canvas.getHeight()-570, startx+31, starty+5);
        grid[startx+30][starty+3] = new SmallPipe(context, (startx+30)*100, canvas.getHeight()-370);
        surfaces[currentsurface] = new Surface((startx+30)*100, (startx+33)*100, (starty+3)*100);
        currentsurface++;
        grid[startx+68][starty+5] = new Piranha(context, (startx+68)*100, canvas.getHeight()-570, startx+68, starty+5);
        grid[startx+67][starty+3] = new SmallPipe(context, (startx+67)*100, canvas.getHeight()-370);
        surfaces[currentsurface] = new Surface((startx+67)*100, (startx+70)*100, (starty+3)*100);
        currentsurface++;


    }
    public Item checkMarioPosition(int x, int y)
    {
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                if(grid[(x+i)/100][(y+j)/100] != null)
                {
                    return grid[(x+i)/100][(y+j)/100];
                }

            }
        }
        return null;
    }
    public boolean checkItemBelow(int x, int y)
    {
        if(y+100 >= height)
        {
            return true;
        }
        if(x/100 >= xSize || y/100 >= ySize)
        {
            return false;
        }
        for(int i = 0; i < (width*3)/100; i++) {
            for (int j = 0; j < height / 100; j++) {
                if (grid[i][j] != null && !(grid[i][j] instanceof Goomba)) {
                    int lX = grid[i][j].getLowerX();
                    int lY = grid[i][j].getLowerY();
                    int uX = grid[i][j].getUpperX();
                    int uY = grid[i][j].getUpperY();
                    if (super.rangeContains(x, lX, uX) && super.rangeContains(y + 100, lY, uY)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setGrid(Item[][] grid)
    {
        this.grid = grid;
    }
    public void drawGrid(int x)
    {
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j < height/100; j++)
            {
                if(grid[i][j] != null && !(grid[i][j] instanceof Piranha))
                {
                    if(grid[i][j].getX()-x >= 0 && grid[i][j].getX()-x <= width)
                    {
                        grid[i][j].drawItem(canvas, grid[i][j].getX()-x, grid[i][j].getY());
                    }
                }
            }
        }
        Bitmap hill = BitmapFactory.decodeResource(context.getResources(), R.drawable.hill);
        Rect r2 = new Rect();
        r2.set(canvas.getWidth()-600-x, canvas.getHeight()-600, canvas.getWidth()-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r2, null);
        Rect r3 = new Rect();
        r3.set((2*canvas.getWidth()-600)-x, canvas.getHeight()-600, (2*canvas.getWidth())-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r3, null);
        Rect r4 = new Rect();
        r4.set((3*canvas.getWidth()-600)-x, canvas.getHeight()-600, (3*canvas.getWidth())-x,canvas.getHeight()-270);
        canvas.drawBitmap(hill, null, r4, null);
    }
    public Bitmap getBackground()
    {
        return background;
    }
    public Item[][] getGrid()
    {
        return grid;
    }
    public void eraseItem(int x, int y)
    {
        for(int i = 0; i < (width*3)/100; i++)
        {
            for(int j = 0; j <  height/100; j++)
            {
                if(grid[i][j] != null)
                {
                    if(grid[i][j].getX() == x && grid[i][j].getY() == y)
                    {
                        grid[i][j] = null;
                    }
                }
            }
        }
    }
    public void addItem(int x, int y, Item item)
    {
        grid[x][y] = item;
    }
    public Surface[] getSurfaces()
    {
        return surfaces;
    }
}
