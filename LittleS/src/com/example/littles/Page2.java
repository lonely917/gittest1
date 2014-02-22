package com.example.littles;

import java.util.LinkedList;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Element;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.YuvImage;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import android.support.v4.app.NavUtils;
import android.widget.Toast;

public class Page2 extends Activity {

	private LinearLayout mainLayout;
	private Resources res;
	private Canvas canvas;
	private Paint paint;
	private Bitmap bmp;
	private Bitmap backBitmap;
	private float screenWidth;
	private float screenHeight;
	private int centerX;
	private int centerY;
	private MyPoint centerPoint;
	private int smallR;
	private int largeR;
	private int[] resIDs={R.drawable.person_1,R.drawable.person_2,R.drawable.person_3,R.drawable.person_4,
			R.drawable.person_5,R.drawable.person_6,R.drawable.person_7,R.drawable.person_8};
	private LinkedList<ImageInfo> imgInfos;
	private LinkedList<ImageInfo> imgInfos_2;
	private final int img_size = 128;
	
	Handler handler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        
        mainLayout = (LinearLayout)findViewById(R.id.sel_gift_layout);
        handler = new Handler();
        init();
        drawCircleInView();
        
        initImageInfo(2,resIDs.length,largeR);
        drawImgs(2);
    }
    
    private void onClick( int X, int Y ){
    	
    	Intent intent = new Intent(Page2.this,Page2_2.class);
    	int ID = 0;
    	for( ImageInfo info:imgInfos_2 ){
    		float left_X = info.center.x - info.width/2;
    		float right_X = info.center.x + info.width/2;
    		
    		float up_Y = info.center.y - info.height/2;
    		float down_Y = info.center.y + info.height/2;
    		if( X >= left_X && X <= right_X && 
    				Y >= up_Y && Y <= down_Y )
    		{
    			ID = info.resID;
    			break;
    		}
    	}
    	if( ID != 0 )
    	{
    		intent.putExtra("imgid", ID);
    		
            // 把Activity转换成一个Window，然后转换成View  
            Window w = Group2.group.getLocalActivityManager().startActivity("Page2_2Activity",intent);  
            View view = w.getDecorView();  
             //设置要跳转的Activity显示为本ActivityGroup的内容  
            Group2.group.setContentView(view);       		
//    		startActivity(intent);
    	}
    	
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if( event.getAction() == MotionEvent.ACTION_DOWN )
    	{
    		int curX = (int)event.getX();
        	int curY = (int)event.getY();
        	onClick(curX, curY);
    	}
    	
    	return true;
    }
    
    private void init(){
    	
    	res = getResources();
    	screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    	screenHeight = getWindowManager().getDefaultDisplay().getHeight();
    	
    	backBitmap = BitmapFactory.decodeResource(res, R.drawable.w01);
    	bmp = Bitmap.createBitmap((int)screenWidth, (int)screenHeight, Config.ARGB_8888);
    	canvas = new Canvas(bmp);
    	paint = new Paint();
    }
    
    private void drawCircleInView(){
    	
    	centerX = (int)screenWidth/2;
    	centerY = (int)(screenHeight * 0.85)/2;
    	centerPoint = new MyPoint(centerX, centerY);
    	smallR = centerX / 2;
    	//画背景
    	canvas.drawColor(getResources().getColor(R.color.mainclolor));
//    	canvas.drawBitmap(backBitmap, 0, 0, paint);
    	//画圆圈
    	paint.setColor(getResources().getColor(R.color.LogMainColor));
    	paint.setStrokeWidth(10);
    	paint.setStyle(Style.STROKE);
    	canvas.drawCircle(centerX, centerY, smallR, paint);
    	largeR = (int)screenWidth * 3 / 8;
    	paint.setColor(getResources().getColor(R.color.PaintColor));
    	canvas.drawCircle(centerX, centerY, largeR, paint);
    	Bitmap img = BitmapFactory.decodeResource(res, R.drawable.whom);
    	drawImageOnCircle(img, centerPoint);
    	Drawable drawable = new BitmapDrawable(bmp);
    	mainLayout.setBackgroundDrawable(drawable);
    }

    //以指定的位置为中心画图片
    private void drawImageOnCircle( Bitmap img, MyPoint point ){
    	
    	int img_w = img.getWidth();
    	int img_h = img.getHeight();
    	int img_x = (int)point.x - img_w/2;
    	int img_y = (int)point.y - img_h/2;
    	canvas.drawBitmap(img, img_x, img_y, paint);
    	
    }
    
    private MyPoint countPos( MyPoint center, double angle, int radius ){
    	float X = (float)( center.x + ( radius * Math.cos(angle) ));
    	float Y = (float)( center.y - ( radius * Math.sin(angle) ));
    	return new MyPoint(X, Y);
    }
    
    private void initImageInfo( int num, int count, int radius ){
    	if( imgInfos == null )
    		imgInfos = new LinkedList<ImageInfo>();
    	if( imgInfos_2 == null )
    		imgInfos_2 = new LinkedList<ImageInfo>();
    	double angle = (2 * Math.PI) / count;
    	int resID = 0;
    	for( int i = 1; i <= count; i++ )
    	{
    		MyPoint point = countPos(centerPoint, angle * i, radius);
    		resID = resIDs[i-1];
    		Bitmap img = BitmapFactory.decodeResource(res, resID);
    		img = Bitmap.createScaledBitmap(img, img_size, img_size, true);
    		ImageInfo info = new ImageInfo(point, 
    				img.getWidth(), img.getHeight(), resID,img );
    		if( num == 1 )
    			imgInfos.add(info);
    		else if( num == 2 )
    			imgInfos_2.add(info);
    	}
    }
    
    private void drawImgs( int num ){
    	if( num == 1 )
    	{
    		if( imgInfos == null || imgInfos.size() == 0 )
    			return;
    		for( ImageInfo info : imgInfos ){
        		drawImageOnCircle(info.img, info.center);
        	}
    	}
    	else if( num == 2 )
    	{
    		if( imgInfos_2 == null || imgInfos_2.size() == 0 )
    			return;
    		for( ImageInfo info : imgInfos_2 ){
        		drawImageOnCircle(info.img, info.center);
        	}
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_page2, menu);
        return true;
    }

    
}
