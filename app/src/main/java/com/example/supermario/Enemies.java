package com.example.supermario;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
// Commit on Saturday, June 8

class Goomba extends Item
{
    Bitmap image;
    int x, y;
    int lowerConstraint;
    int upperConstraint;
    public Goomba(Context context, int x, int y, int upperConstraint)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.goomba);
        this.x = x;
        this.y = y;
        addConstraints(x, upperConstraint);
    }
    public void addConstraints(int lowerConstraint, int upperConstraint)
    {
        this.lowerConstraint = lowerConstraint;
        this.upperConstraint = upperConstraint;
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

}
class Piranha extends Item
{
    Bitmap image;
    int x, y;
    boolean isEnabled = true;
    int xIndex, yIndex;
    public Piranha(Context context, int x, int y, int xIndex, int yIndex)
    {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.piranha);
        this.x = x;
        this.y = y;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
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
    void drawItem(Canvas canvas, int x, int y)
    {
        Rect r = new Rect();
        r.set(x, y, x+100, y+100);
        canvas.drawBitmap(image, null, r, null);
    }
}