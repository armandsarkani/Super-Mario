package com.example.supermario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
// Commit on Saturday, June 8

public class Mario {
    String type;
    Bitmap frames[];
    Context context;
    public Mario(Context context) {
        frames = new Bitmap[12];
        this.context = context;
        this.frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior1);
        this.frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior1);
        this.frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior3);
        this.frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior3);
        this.frames[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior4);
        this.frames[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior4);
        this.frames[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol1);
        this.frames[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol1);
        this.frames[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol3);
        this.frames[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol3);
        this.frames[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol4);
        this.frames[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol4);
        type = "regular";
    }
    public void changeType(String type) {
        if (type.equals("regular")) {
            this.frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior1);
            this.frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior1);
            this.frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior3);
            this.frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior3);
            this.frames[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior4);
            this.frames[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marior4);
            this.frames[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol1);
            this.frames[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol1);
            this.frames[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol3);
            this.frames[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol3);
            this.frames[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol4);
            this.frames[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mariol4);
        }
        else if (type.equals("super"))
        {
            this.frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermarior1);
            this.frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermarior1);
            this.frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermarior3);
            this.frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermarior3);
            this.frames[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermarior4);
            this.frames[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermarior4);
            this.frames[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermariol1);
            this.frames[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermariol1);
            this.frames[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermariol3);
            this.frames[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermariol3);
            this.frames[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermariol4);
            this.frames[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.supermariol4);
        }
        else if (type.equals("invincible"))
        {
            this.frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemarior1);
            this.frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemarior1);
            this.frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemarior3);
            this.frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemarior3);
            this.frames[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemarior4);
            this.frames[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemarior4);
            this.frames[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemariol1);
            this.frames[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemariol1);
            this.frames[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemariol3);
            this.frames[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemariol3);
            this.frames[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemariol4);
            this.frames[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.invinciblemariol4);
        }
    }

}


