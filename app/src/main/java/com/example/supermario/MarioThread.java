package com.example.supermario;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
// Commit on Saturday, June 8

public class MarioThread extends Thread
{
    MarioView mv;
    private Canvas c;
    public MarioThread(MarioView mv)
    {
        this.mv = mv;
    }
    public void run()
    {
        SurfaceHolder sh = mv.getHolder();
        while(!Thread.interrupted())
        {
            c = sh.lockCanvas(null);
            try
            {
                synchronized(sh)
                {
                    mv.draw(c);
                }

            }
            catch(Exception e)
            {

            }
            finally
            {
                if(c != null)
                {
                    sh.unlockCanvasAndPost(c);
                }
            }
            try
            {
                Thread.sleep(1);

            }
            catch(InterruptedException e)
            {
                return;
            }

        }
    }

}