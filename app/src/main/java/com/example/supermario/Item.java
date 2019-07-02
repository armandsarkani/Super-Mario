package com.example.supermario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
// Commit on Saturday, June 8

class Item
{
    int x = 0;
    int y = 0;
    Rect init;
    void drawItem(Canvas canvas, int x, int y)
    {
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }
    int getLowerX()
    {
        return x;
    }
    int getUpperX()
    {
        return x+100;
    }
    int getLowerY()
    {
        return y;
    }
    int getUpperY()
    {
        return y+100;
    }

}
class Brick extends Item
{
    Bitmap image;
    int x, y;
    Rect init;
    public Brick(Context context, int x, int y)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick);
        this.x = x;
        this.y = y;
        init = new Rect();
        init.set(x, y, x+100, y+100);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y, x+100, y+100);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getLowerX()
    {
        return x;
    }
    int getUpperX()
    {
        return x+100;
    }
    int getLowerY()
    {
        return y;
    }
    int getUpperY()
    {
        return y+100;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }

}
class Question extends Item
{
    Bitmap image;
    int x, y;
    String spawn;
    Rect init;
    public Question(Context context, int x, int y, String spawn)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.question);
        this.x = x;
        this.y = y;
        this.spawn = spawn;
        init = new Rect();
        init.set(x, y, x+100, y+100);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y, x+100, y+100);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getLowerX()
    {
        return x;
    }
    int getUpperX()
    {
        return x+100;
    }
    int getLowerY()
    {
        return y;
    }
    int getUpperY()
    {
        return y+100;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }
    String returnSpawn()
    {
        return spawn;
    }
}
class Coin extends Item
{
    Bitmap image;
    int x, y;
    Rect init;
    public Coin(Context context, int x, int y)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.coin);
        this.x = x;
        this.y = y;
        init = new Rect();
        init.set(x, y, x+80, y+80);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y, x+80, y+80);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }
}
class SuperMushroom extends Item
{
    Bitmap image;
    int x, y;
    Rect init;
    public SuperMushroom(Context context, int x, int y)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.mushroom);
        this.x = x;
        this.y = y;
        init = new Rect();
        init.set(x, y, x+80, y+80);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y, x+80, y+80);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getLowerX()
    {
        return x;
    }
    int getUpperX()
    {
        return x+80;
    }
    int getLowerY()
    {
        return y;
    }
    int getUpperY()
    {
        return y+80;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }

}
class Starman extends Item
{
    Bitmap image;
    int x, y;
    Rect init;
    public Starman(Context context, int x, int y)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);
        this.x = x;
        this.y = y;
        init = new Rect();
        init.set(x, y, x+80, y+80);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y, x+80, y+80);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getLowerX()
    {
        return x;
    }
    int getUpperX()
    {
        return x+80;
    }
    int getLowerY()
    {
        return y;
    }
    int getUpperY()
    {
        return y+80;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }
}
class BigPipe extends Item
{
    Bitmap image;
    int x, y;
    Rect init;
    public BigPipe(Context context, int x, int y)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.bigpipe);
        this.x = x;
        this.y = y;
        init = new Rect();
        init.set(x, y-300, x+400, y+100);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y-300, x+300, y+100);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }
}
class SmallPipe extends Item
{
    Bitmap image;
    int x, y;
    Rect init;
    public SmallPipe(Context context, int x, int y)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallpipe);
        this.x = x;
        this.y = y;
        init = new Rect();
        init.set(x, y-300, x+100, y+100);
    }
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y-100, x+300, y+100);
        canvas.drawBitmap(image, null, r, null);
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getLowerX()
    {
        return x;
    }
    int getUpperX()
    {
        return x+300;
    }
    int getLowerY()
    {
        return y-100;
    }
    int getUpperY()
    {
        return y+100;
    }
    void setX(int x)
    {
        this.x = x;
    }
    void setY(int y)
    {
        this.y = y;
    }
    Rect getRect()
    {
        return init;
    }
}
