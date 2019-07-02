package com.example.supermario;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.Pipe;
import java.util.concurrent.TimeUnit;
// Commit on Saturday, June 8

@SuppressLint("ClickableViewAccessibility")


public class MarioView extends SurfaceView implements SurfaceHolder.Callback
{
    Level level;
    Rect s;
    boolean intersectflag;
    Goomba goomba;
    Item check;
    FloatingActionButton jB;
    boolean goombarun = true;
    Piranha copy;
    String lastmario;
    Item[][] grid;
    boolean allowkill = true;
    long invincibleTime = 0;
    int goombalow, goombahigh, goombax;
    boolean jumped;
    int goombadirection = -1;
    int sign = 1;
    int floor;
    int currentlevel = 1;
    int lives = 3;
    Rect g;
    int currentmario;
    boolean jump;
    boolean firstRun = true;
    //boolean level2 = true, level3 = true;
    boolean changeLevel = true;
    int offset, counter;
    int currentscore = 0;
    boolean piranhaflag;
    AttributeSet attrs;
    Piranha piranha, temppiranha;
    int coins;
    Canvas c;
    boolean isMovingLeft, isMovingRight, isStraightJump;
    Mario mario;
    MarioView mv;
    int x = 500;
    int y = 0;
    private SurfaceHolder sh;
    public MarioView(Context context) {
        super(context);
        sh = getHolder();
        sh.addCallback(this);
        setFocusable(true);
    }
    public MarioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setZOrderOnTop(true);
        sh = getHolder();
        sh.addCallback(this);
        setFocusable(true);
        this.attrs = attrs;
    }
    public MarioView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        sh = getHolder();
        sh.addCallback(this);
        setFocusable(true);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        mv = this;
        mario = new Mario(getContext());
        updateScore(0);
        jB = GetActivity().findViewById(R.id.jump);
        //MarioThread mt = new MarioThread(this);
        //mt.start();
    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        jB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(jumping())
                {
                    jump = true;
                }
            }
        });
        floor = getHeight() - 370;
        c = canvas;
        setLevel(currentlevel);
        grid = level.getGrid();
        JumpThread j = new JumpThread(this);
        j.start();
        Rect r = new Rect();
        r.set(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(level.getBackground(), null, r, null);
        if(firstRun)
        {
            y = floor;
            firstRun = false;
        }
        level.drawGrid(offset);
        if(level.getGrid() !=null)
        {
            goomba = searchGoombas();
            piranha = searchPiranhas();
            if(piranha != null)
            {
                piranha = new Piranha(getContext(), piranha.x, piranha.y, piranha.xIndex, piranha.yIndex);
            }
            else if(piranha == null && temppiranha != null)
            {
                piranha = temppiranha;
                temppiranha = null;
            }

        }
        s = new Rect();
        if(mario != null && (mario.type.equals("super") || mario.type.equals("invincible")))
        {
            if(x >= getWidth()/2)
            {
                s.set(100+getWidth()/2, y-50,200+getWidth()/2, y+100);
            }
            else
            {
                s.set(100+x, y-50,200+x, y+100);

            }
        }
        else
        {
            if(x >= getWidth()/2)
            {
                s.set(100+getWidth()/2, y,200+getWidth()/2, y+100);

            }
            else
            {
                s.set(100+x, y,200+x, y+100);

            }
        }
        if(mario.type.equals("invincible") && System.currentTimeMillis() >= invincibleTime + 15000 && lastmario != null)
        {
            invincibleTime = 0;
            mario.type = lastmario;
            mario.changeType(lastmario);
        }
        if(piranha != null)
        {
            if(piranha.x - (x+offset) > 500 || (x+offset) - (piranha.x) > 500 || jumped)
            {
                level.addItem(piranha.xIndex, piranha.yIndex, piranha);
                piranha.drawItem(canvas, piranha.x-offset, piranha.y);
                grid = level.getGrid();
                Rect p = new Rect();
                p.set(piranha.x, piranha.y, piranha.x+100, piranha.y+100);
                Rect m = new Rect();
                m.set(s.left+offset, s.top, s.right+offset, s.bottom);
                if(s!= null && p.intersect(m) && allowkill)
                {
                    if(mario.type.equals("invincible"))
                    {
                        level.eraseItem(piranha.x, piranha.y);
                        GetActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateScore(currentscore+100);
                                Toast g = Toast.makeText(getContext(), "You have killed a piranha!", Toast.LENGTH_SHORT);
                                g.show();
                            }
                        });
                        grid = level.getGrid();

                    }
                    else if(mario.type.equals("super"))
                    {
                        mario.type = "regular";
                        mario.changeType("regular");
                    }
                    else
                    {
                        lives--;
                        gameDecision();
                        allowkill = false;
                    }
                }
            }
            else
            {
                temppiranha = new Piranha(getContext(), piranha.x, piranha.y, piranha.xIndex, piranha.yIndex);
                level.eraseItem(piranha.x, piranha.y);
                grid = level.getGrid();
            }
        }
        g = new Rect();
        if(goomba != null && goombarun)
        {
            goombalow = goomba.x;
            goombahigh = goomba.upperConstraint;
            goombax = goombahigh;
            goombarun = false;
        }
        g.set(goombax-offset, floor,100+goombax-offset, floor+100);
        if(isMovingRight)
        {
            jB.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.arrowupright));
        }
        if(isMovingLeft)
        {
            jB.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.arrowupleft));
        }
        canvas.drawBitmap(mario.frames[currentmario], null, s, null);
        if(goomba != null && c != null)
        {
            canvas.drawBitmap(goomba.image, null, g, null);
            if(goombax > goombahigh || goombax < goombalow)
            {
                goombadirection = -goombadirection;
            }
            if(goombax-offset < 0)
            {
                goombarun = true;
            }
            goombax +=(10*goombadirection);

        }
        if(goomba != null && goombax == x+100+offset && y == floor && !jump)
        {
            if(mario.type.equals("regular"))
            {
                lives--;
                gameDecision();
                grid = level.getGrid();
            }
            else if(mario.type.equals("super"))
            {
                mario.type = "regular";
                mario.changeType("regular");
            }
            else if(mario.type.equals("invincible"))
            {
                level.eraseItem(goomba.getX(), goomba.getY());
                goomba = null;
                GetActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateScore(currentscore+100);
                        Toast g = Toast.makeText(getContext(), "You have stomped a goomba!", Toast.LENGTH_SHORT);
                        g.show();
                    }
                });
                grid = level.getGrid();
            }
        }
        jumped = false;
        if(isMovingRight)
        {
           rightwardsMotion();
           allowkill = true;
           if(check != null)
           {
               level.eraseItem(check.getX(), check.getY()-100);
           }
        }
        if(isMovingLeft)
        {
            leftwardsMotion();
            allowkill = true;
            if(check != null)
            {
                level.eraseItem(check.getX(), check.getY()-100);
            }
        }
        if(x+offset >= getWidth()*3)
        {
            if(currentlevel == 3)
            {
                canvas.drawBitmap(level.getBackground(), null, r, null);
                endGame();
            }
            currentlevel++;
            changeLevel = true;
            counter = 0;
            offset = 0;
            x = 500;
            jumped = false;
            goomba = null;
            grid = null;
            goombarun = true;
            firstRun = true;
        }
        System.gc();
        invalidate();
    }
    public void rightwardsMotion()
    {
        if(currentmario > 5)
        {
            currentmario = 0;
        }
        else
        {
            currentmario++;
            if(currentmario > 5)
            {
                currentmario = 0;
            }
        }
        if(x+offset+200 < getWidth()*3)
        {
            if(!(level.checkItemBelow(x+offset+200, y)) && !jump)
            {
                y = floor;
            }
        }
        if((x+offset+200)/100 >= (getWidth()*3)/100)
        {
            if(x+100 >= getWidth()/2)
            {
                counter++;
                offset = 100*counter;
            }
            else
            {
                x+=100;
            }
        }
        else
        {
            Item check = level.checkMarioPosition((x+offset)+100, y);
            if (check != null && !(check instanceof Goomba))
            {
                x+=0;
            }
            else
            {
                if(x+100 >= getWidth()/2)
                {
                    counter++;
                    offset = 100*counter;
                }
                else
                {
                    x+=100;
                }
            }
        }
        try
        {
            Thread.sleep(30);
        }
        catch(InterruptedException e)
        {

        }
    }
    public void leftwardsMotion()
    {
        if(currentmario == 11)
        {
            currentmario = 6;
        }
        if(currentmario < 6)
        {
            currentmario = 6;
        }
        else
        {
            if(currentmario < 11)
            {
                currentmario++;
            }
        }
        if(x+offset < getWidth()*3 && !(level.checkItemBelow(x+offset, y)) && !jump)
        {
            y = floor;
        }
        if(x+offset < 200 + offset)
        {
            x-=0;
        }
        else
        {
            Item check = level.checkMarioPosition((x+offset-200), y);
            if (check != null && !(check instanceof Goomba))
            {
                x+=0;
            }
            else
            {
                x-=100;
            }
        }
        try
        {
            Thread.sleep(30);
        }
        catch(InterruptedException e)
        {

        }
    }
    public Piranha searchPiranhas()
    {
        Item[][] grid = level.getGrid();
        for(int i = 0; i < (getWidth()*3)/100; i++)
        {
            for(int j = 0; j <  getHeight()/100; j++)
            {
                if(grid[i][j] != null && grid[i][j] instanceof Piranha && grid[i][j].getX()-offset >= 0 && grid[i][j].getX()-offset <= getWidth())
                {
                    return (Piranha) grid[i][j];
                }
            }
        }
        return null;
    }
    public Goomba searchGoombas()
    {
        Item[][] grid = level.getGrid();
        for(int i = 0; i < (getWidth()*3)/100; i++)
        {
            for(int j = 0; j <  getHeight()/100; j++)
            {
                if(grid[i][j] != null && grid[i][j] instanceof Goomba && grid[i][j].getX()-offset >= 0 && grid[i][j].getX()-offset <= getWidth())
                {
                    return (Goomba)grid[i][j];
                }
            }
        }
        return null;
    }
    public void gameDecision()
    {
        if(lives > 0)
        {
            updateLives(lives);
            String text = "Mario has lost a life! " + lives + " remaining!";
            Toast life = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
            life.show();
        }
        else
        {
            updateLives(0);
            endGame();
        }
    }
    public void setLevel(int currentlevel)
    {
        if(c == null)
        {
            return;
        }
        if(currentlevel == 1 && changeLevel)
        {
            level = new Level1(c, getContext());
            level.initializeGrid();
            changeLevel = false;
        }
        else if(currentlevel == 2 && changeLevel)
        {
            level = new Level2(c, getContext());
            level.initializeGrid();
            changeLevel = false;
        }
        else if(currentlevel == 3 && changeLevel)
        {
            level = new Level3(c, getContext());
            level.initializeGrid();
            changeLevel = false;
        }
        if(grid != null)
        {
            level.setGrid(grid);
        }
        if(currentlevel !=4)
        {
            updateLevel(currentlevel);
        }

    }
    public void updateCoins(int coins) {
        final int x = coins;
        GetActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView c = GetActivity().findViewById(R.id.coins);
                c.setVisibility(View.VISIBLE);
                String num = Integer.toString(x);
                c.setText(num);
            }
        });
    }
    public void updateLevel(int level) {
        final int x = level;
        GetActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView c = GetActivity().findViewById(R.id.level);
                c.setVisibility(View.VISIBLE);
                String num = Integer.toString(x);
                c.setText(num);
            }
        });
    }
    public void updateScore(int score)
    {
        final int x = score;
        GetActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView c = GetActivity().findViewById(R.id.score);
                c.setVisibility(View.VISIBLE);
                String num = Integer.toString(x);
                c.setText(num);
            }
        });
        TextView s = GetActivity().findViewById(R.id.score);
        String num = Integer.toString(score);
        s.setText(num);
    }
    public void updateLives(int lives)
    {
        final int x = lives;
        GetActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView c = GetActivity().findViewById(R.id.lives);
                String num = Integer.toString(x);
                c.setText(num);
            }
        });
    }
    public void endGame()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext(), R.style.MyAlert);
        builder1.setTitle("Game over!");
        builder1.setMessage("Your score was " + currentscore + ". Tap restart to play again.");
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "Restart",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = GetActivity().getIntent();
                        MainActivity ma = (MainActivity) GetActivity();
                        GetActivity().overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        GetActivity().finish();
                        GetActivity().overridePendingTransition(0, 0);
                        GetActivity().startActivity(intent);
                    }
                });
        builder1.setNegativeButton(
                "Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GetActivity().finish();
                        System.exit(10);
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        int w = e.getActionMasked();
        switch(w) {
            case (MotionEvent.ACTION_DOWN):
                if (e.getX() >= getWidth()/2 && e.getY() > getHeight()/2) // rightwards motion
                {
                    isMovingRight = true;
                }
                else if (e.getX() < getWidth()/2 && e.getY() > getHeight()/2)
                {
                    isMovingLeft = true;
                }
                if(e.getY() < getHeight()/2)
                {
                    if(jump || isStraightJump)
                    {
                        return false;
                    }
                    isStraightJump = true;
                    if(y-200 < 0)
                    {
                        return false;
                    }
                    if((x+offset)/100 > (getWidth()*3)/100)
                    {
                        return false;
                    }
                    y-=200;
                    check = level.checkMarioPosition((x+offset)+100, y);
                    if (check != null)
                    {
                        if (check instanceof Question)
                        {
                            ((Question) check).image = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.solidblock);
                            addSpawn(((Question) check).returnSpawn(), (Question) check);
                            ((Question) check).spawn = "null";
                        }
                        else
                        {
                            if (check instanceof Brick && (mario.type.equals("super") || mario.type.equals("invincible")))
                            {
                                level.eraseItem(check.getX(), check.getY());
                            }
                        }
                        if (check instanceof Brick && (mario.type.equals("super") || mario.type.equals("invincible")))
                        {
                            currentscore+=10;
                            updateScore(currentscore);
                        }
                        grid = level.getGrid();
                    }
                    jump = true;
                    return true;
                }
                return true;
            case(MotionEvent.ACTION_POINTER_DOWN):
                if(jumping())
                {
                    jump = true;
                }
                return true;
            case(MotionEvent.ACTION_UP):
                if(isMovingRight)
                {
                    isMovingRight = false;
                }
                if(isMovingLeft)
                {
                    isMovingLeft = false;
                }
                return true;
            case(MotionEvent.ACTION_POINTER_UP):
                if(jump)
                {
                    jump = false;
                }
                return true;
            default:
                return super.onTouchEvent(e);
        }
    }
    public boolean jumping()
    {
        if(jump)
        {
            return false;
        }
        if(y-200 < 0)
        {
            return false;
        }
        if((x+offset+100)/100 > (getWidth()*3)/100)
        {
            return false;
        }
        y-= getLandingSurface();
        Item check = level.checkMarioPosition((x+offset)+100, y);
        if (check != null)
        {
            if (check instanceof Question)
            {
                ((Question) check).image = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.solidblock);
                addSpawn(((Question) check).returnSpawn(), (Question) check);
                ((Question) check).spawn = "null";
            }
            else
            {
                if (check instanceof Brick && mario.type.equals("super") || mario.type.equals("invincible"))
                {
                    level.eraseItem(check.getX(), check.getY());
                }
            }
            if(check instanceof Brick && mario.type.equals("super") || mario.type.equals("invincible"))
            {
                currentscore+=10;
                updateScore(currentscore);
            }
            grid = level.getGrid();
        }
        return true;
    }
    public void addSpawn(String spawn, Question check)
    {
        if(spawn.equals("coin"))
        {
            level.addItem(check.getX()/100, (check.getY()-100)/100, new Coin(getContext(), (check.getX()), check.getY()-100));
            coins++;
            updateCoins(coins);
            currentscore+= 200;
            updateScore(currentscore);
        }
        else if(spawn.equals("starman"))
        {
            level.addItem(check.getX()/100, (check.getY()-100)/100, new Starman(getContext(), (check.getX()), check.getY()-100));
            lastmario = mario.type;
            mario.type = "invincible";
            mario.changeType("invincible");
            invincibleTime = System.currentTimeMillis();
            currentscore+= 1000;
            updateScore(currentscore);
        }
        else if(spawn.equals("mushroom"))
        {
            level.addItem(check.getX()/100, (check.getY()-100)/100, new SuperMushroom(getContext(), (check.getX()), check.getY()-100));
            mario.type = "super";
            mario.changeType("super");
            currentscore+= 1000;
            updateScore(currentscore);
        }
    }
    public int getLandingSurface()
    {
        Surface[] surfaces = level.getSurfaces();
        for(int i = 0; i < surfaces.length; i++)
        {
            if(surfaces[i] == null)
            {
                break;
            }
            if(currentmario <= 5)
            {
                if((surfaces[i].xMin - (x+offset)) <= 200 && (surfaces[i].xMin - (x+offset)) > 0)
                {
                    return 100+ y - surfaces[i].y;
                }
            }
            else
            {
                if(((x+offset)- surfaces[i].xMax) <= 200 && ((x+offset) - surfaces[i].xMax) > 0)
                {
                    return 100+ y - surfaces[i].y;
                }
            }
        }
        return 200;
    }
    public Activity GetActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity)
            {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
    class JumpThread extends Thread
    {
        MarioView mv;
        JumpThread(MarioView mv)
        {
            this.mv = mv;
        }
        @Override
        public void run()
        {
            synchronized(mv)
            {
                if(jump)
                {
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        // do nothing
                    }
                    if(!isStraightJump)
                    {
                        if(currentmario <= 5)
                        {
                            if(level.checkItemBelow(x+offset+300, y))
                            {
                                y+=0;
                            }
                            else
                            {
                                y = floor;

                            }
                            x+=200;
                        }
                        else
                        {
                            if(level.checkItemBelow(x+offset-100, y))
                            {
                                y+=0;
                            }
                            else
                            {
                                y = floor;

                            }
                            x-=200;
                        }
                    }
                    else
                    {
                        if(level.checkItemBelow(x+offset+100, y))
                        {
                            y+=0;
                        }
                        y += 200;
                    }
                    if(goomba != null && (goombax - (x+offset+100) <= 200) && y == floor)
                    {
                        level.eraseItem(goomba.getX(), goomba.getY());
                        goomba = null;
                        GetActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateScore(currentscore+100);
                                Toast g = Toast.makeText(getContext(), "You have stomped a goomba!", Toast.LENGTH_SHORT);
                                g.show();
                            }
                        });
                        grid = level.getGrid();
                    }
                    jumped = true;
                    piranhaflag = true;
                    isStraightJump = false;
                    jump = false;
                    run();
                }
            }
        }
    }
}
