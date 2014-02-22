package com.example.littles;

import java.util.LinkedList;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Element;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.ComponentName;
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
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
//import android.support.v4.app.NavUtils;
import android.widget.Toast;

public class Page2_2 extends Activity {

	//弹出窗口
	PopupWindow m_window; 
	
	private LinearLayout mainLayout;
	private ImageView basketImageView;
	public static TextView numTextView;
	private VerticalSeekBar seekBar;
	private TextView curSeekTextView;
	private boolean hasChanged_1 = false;
	private boolean hasChanged_2 = false;
	private int lastCur = 0;
	
	//弹出窗口信息
	private LinearLayout popup_sel_layout;
	private ImageView popup_img;
	private TextView popup_name;
	private TextView popup_brand;
	private TextView popup_intro;
	private TextView popup_size;
	private TextView popup_times_num;
	private TextView popup_rank;
	private TextView popup_price;
	private ImageView[] popup_recommends;
	public static DetailInfo detailInfo;
	private TextView popup_details;
	
	
	private Resources res;
	private Canvas canvas;
	private Paint paint;
	private Bitmap bmp;
	private Bitmap backBitmap;
	private int centerImgID;
	private float screenWidth;
	private float screenHeight;
	private int centerX;
	private int centerY;
	private MyPoint centerPoint;
	private int smallR;
	private int largeR;
	private int[] resIDs_1 = {R.drawable.gift_1,R.drawable.gift_2,R.drawable.gift_3,
			R.drawable.gift_4,R.drawable.gift_5};
	private int[] resIDs_2 = {R.drawable.gift_6,R.drawable.gift_7,R.drawable.gift_8,
			R.drawable.gift_9,R.drawable.gift_10,R.drawable.gift_11,R.drawable.gift_12,
			R.drawable.gift_13,R.drawable.gift_14,R.drawable.gift_15,R.drawable.gift_16};
	
	
	private LinkedList<ImageInfo> imgInfos;
	private LinkedList<ImageInfo> imgInfos_2;
	private final int img_size = 96;
	
	private long lastDown;
	private final static long DOUBLE_TIME = 500;
	private boolean isDoubleClicked = false;
	Handler handler;
	
	//返回按钮
	private ImageView btnReturnImageView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_2);
        
        Intent intent = getIntent();
        centerImgID = intent.getIntExtra("imgid", 0);
        mainLayout = (LinearLayout)findViewById(R.id.sel_gift_layout_2);
        numTextView = (TextView)findViewById(R.id.Page2_2_basket_num);
        basketImageView = (ImageView)findViewById(R.id.Page2_2_basket);
        seekBar = (VerticalSeekBar)findViewById(R.id.Page2_2_seekbar);
        curSeekTextView = (TextView)findViewById(R.id.Page2_2_cur_seek);
        
        btnReturnImageView = (ImageView) findViewById(R.id.btnre_page2_2);
        btnReturnImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), Page2.class);
				Window w = Group2.group.getLocalActivityManager().startActivity("Page2Activity",intent);
				View view = w.getDecorView();
				//设置要跳转的Activity显示为本ActivityGroup的内容
				Group2.group.setContentView(view);
				Group2.group.getLocalActivityManager().destroyActivity("Page2_2Activity", true);
			}
		});
        
        handler = new Handler();
        lastDown = -1;
        init();
        drawCircleInView();
        initImageInfo(1,resIDs_1.length,smallR);
        drawImgs(1);
        
        initImageInfo(2,resIDs_2.length,largeR);
        drawImgs(2);
        
        basketImageView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent intent = new Intent(Page2_2.this, Page3.class);
//				startActivity(intent);
				HomeActivity.tabHost.setCurrentTab(2);
				
				HomeActivity.tabImageViews[1].setImageDrawable(getResources().getDrawable(R.drawable.tab2));
			    HomeActivity.currentIndex = 2;
			    HomeActivity.tabImageViews[2].setImageDrawable(getResources().getDrawable(R.drawable.tab_3));
			}
		});
        
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {		
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {				
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				float pro = seekBar.getProgress();
				float num = seekBar.getMax();
				float result = (300* pro / num);
				int cur = (int)result;
				if( cur > 0 )
				{
					String curString = curSeekTextView.getText().toString().substring(0, 1);
					curString += cur;
					curSeekTextView.setVisibility(View.VISIBLE);
					curSeekTextView.setText(curString);
					
					if( cur > 100 && (!hasChanged_1) )
					{
						imgInfos_2.remove(0);
						imgInfos_2.remove(2);
						imgInfos_2.remove(5);
						imgInfos_2.remove(6);
						hasChanged_1 = true;
						if( cur >= 200 && hasChanged_2 )
						{
							imgInfos.remove(0);
							//imgInfos.remove(1);
							//imgInfos.remove(2);
							hasChanged_2 = true;
						}
						
					}
					else if( lastCur > 100 && cur < 100 )
					{
						hasChanged_1 = false;
						hasChanged_2 = false;
						initImageInfo(1,resIDs_1.length,smallR);
						initImageInfo(2,resIDs_2.length,largeR);
					}
					lastCur = cur;
        			bmp.eraseColor(Color.TRANSPARENT);
    				canvas.drawBitmap(bmp, 0, 0, paint);
    				drawCircleInView();
					drawImgs(1);
					drawImgs(2);
				}
				
			}
		});
    }
    
	public void onBackPressed() {
		Intent intent = new Intent(Page2_2.this, Page2.class);
		// 把Activity转换成一个Window，然后转换成View
		Window w = Group2.group.getLocalActivityManager().startActivity("Page2Activity", intent);
		View view = w.getDecorView();
		// 设置要跳转的Activity显示为本ActivityGroup的内容
		Group2.group.setContentView(view);
		//清楚旧的activity组件 否则下一次有启动这一个而不会启动新的
		Group2.group.getLocalActivityManager().destroyActivity("Page2_2Activity", true);
	} 
	
    private void OnDoubleClick( int X, int Y ){
    	//Toast.makeText(Page2.this, "double clicked", Toast.LENGTH_SHORT).show();
    }
    
    private void setTextInfo( TextView view, String str )
    {
    	String preString = view.getText().toString();
    	String cursString = preString + str;
    	view.setText(cursString);
    }
    
    private void setAllInfo( DetailInfo detailInfo )
    {
    	Bitmap img = BitmapFactory.decodeResource(getResources(), detailInfo.img_id);
		img = Bitmap.createScaledBitmap(img, img_size, img_size, true);
		
    	popup_img.setImageBitmap(img);
		setTextInfo(popup_name, detailInfo.name);
		setTextInfo(popup_brand, detailInfo.brand);
		setTextInfo(popup_intro, detailInfo.intro);
		setTextInfo(popup_size, detailInfo.size);
		setTextInfo(popup_times_num, detailInfo.times_num);
		setTextInfo(popup_rank, detailInfo.rank);
		setTextInfo(popup_price, detailInfo.price);
		int rec_num = detailInfo.rec_num;
		if( rec_num > 5 )
			rec_num = 5;
		for( int i = 0; i < rec_num; i++ )
		{
			popup_recommends[i].setVisibility(View.VISIBLE);
		}
    }
    
    private void popUp( ImageInfo info ){
    	
		MyPoint point = info.center;
		LayoutInflater inflater = getLayoutInflater();
		popup_sel_layout = (LinearLayout)inflater.inflate(R.layout.popup_sel_gift, null);
		popup_img = (ImageView)popup_sel_layout.findViewById(R.id.Page2_2_popup_img);
        popup_name = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_name);
        popup_brand = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_brand);
        popup_intro = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_intro);
        popup_size = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_size);
    	popup_times_num = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_times_num);
    	popup_rank = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_rank);
    	popup_price = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_price);
    	popup_recommends = new ImageView[5];
    	for( int i = 0; i < 5; i++ )
    	{
    		popup_recommends[i] = null;
    	}
        popup_recommends[0] = (ImageView)popup_sel_layout.findViewById(
        		R.id.Page2_2_popup_recommend_1);
        popup_recommends[1] = (ImageView)popup_sel_layout.findViewById(
        		R.id.Page2_2_popup_recommend_2);
        popup_recommends[2] = (ImageView)popup_sel_layout.findViewById(
        		R.id.Page2_2_popup_recommend_3);
        popup_recommends[3] = (ImageView)popup_sel_layout.findViewById(
        		R.id.Page2_2_popup_recommend_4);
        popup_recommends[4] = (ImageView)popup_sel_layout.findViewById(
        		R.id.Page2_2_popup_recommend_5);
        
        detailInfo = new DetailInfo(info.resID,
        		"银手镯（纯银）大理石之恋", "大理白族银手镯",
        		"大理石白族的传统手工艺品", "直径6厘米", "1031次", "精品", "75元", 4);
        
        setAllInfo(detailInfo);
		//final Bitmap img = info.img;
		popup_details = (TextView)popup_sel_layout.findViewById(R.id.Page2_2_popup_details);
		popup_details.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		popup_details.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Page2_2.this,DetailsActivity.class);
				if( detailInfo != null )
				{
					intent.putExtra("detail", detailInfo);
				}
				
	            // 把Activity转换成一个Window，然后转换成View  
	            Window w = Group2.group.getLocalActivityManager().startActivity("DetailsActivity",intent);  
	            View view = w.getDecorView();  
	             //设置要跳转的Activity显示为本ActivityGroup的内容  
	            Group2.group.setContentView(view);  
	            
//				startActivity(intent);
	            
	            m_window.dismiss();
			}
		});
		
		
		m_window = new PopupWindow(popup_sel_layout, 
				LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,false);
		
		//ColorDrawable cd = new ColorDrawable(0xB00000);
		m_window.setBackgroundDrawable( new BitmapDrawable() );
		m_window.setOutsideTouchable(true);
		m_window.setFocusable(true);
		//window.setAnimationStyle(R.anim.zoom_out_enter);
		m_window.showAtLocation(mainLayout, Gravity.TOP|Gravity.LEFT, 
				(int)point.x, (int)point.y);
		
		
    }
    
    public static void addToBasket(){
    	int pre = Integer.parseInt(numTextView.getText().toString());
		pre++;
		if( pre != 0 && numTextView.getVisibility() == View.INVISIBLE )
		{
			numTextView.setVisibility(View.VISIBLE);
		}
		String textString = pre + "";
		numTextView.setText(textString);
    }
    
    private void onClick( int X, int Y ){
    	int sel = -1;
    	int num = 0;
    	if( imgInfos != null )
    	{
    		sel = dealImg( new MyPoint(X, Y), 1 );
    		if( sel != -1 )
    			num = 1;
    	}
    	if( sel == -1 && imgInfos_2 != null )
    	{
    		sel = dealImg( new MyPoint(X, Y), 2 );
    		if( sel != -1 )
    			num = 2;
    	}
    	if( sel != -1 )
    	{
    		ImageInfo info = null;
    		if( num == 1 )
    		{
    			info = imgInfos.get(sel);
    		}
    		else
    		{
    			info = imgInfos_2.get(sel);
			}
    		
    		popUp(info);
    		
    		//addToBasket();
    	}
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if( event.getAction() == MotionEvent.ACTION_DOWN )
    	{
    		int curX = (int)event.getX();
        	int curY = (int)event.getY();
        	
        	long nowDown = System.currentTimeMillis();
        	//双击
        	if( nowDown - lastDown < DOUBLE_TIME )
        	{
        		isDoubleClicked = true;
        		OnDoubleClick(curX, curX);
        	}
        	else
        	{
        		lastDown = nowDown;
        		onClick(curX, curY);
        	}
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
    	//如何设置画布的背景颜色
    	
    	//
    	paint = new Paint();
    }
    
    private void drawCircleInView(){
    	
    	centerX = (int)screenWidth/2;
    	centerY = (int)(screenHeight * 0.85)/2;
    	centerPoint = new MyPoint(centerX, centerY);
    	smallR = centerX / 3;
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
    	
    	if( centerImgID != 0 )
        {
        	Bitmap img = BitmapFactory.decodeResource(res, centerImgID);
        	img = Bitmap.createScaledBitmap(img, img_size, img_size, true);
        	drawImageOnCircle(img, centerPoint);
        }
    	
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
    		if( count == 1 )
    		{
    			resID = resIDs_1[i-1];
    		}
    		else
    		{
    			resID = resIDs_2[i-1];
			}
    		
    		MyPoint point = countPos(centerPoint, angle * i, radius);
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
    //判断指定位置是否有图片，有的话删除并返回true，否则返回fals
    private int dealImg( MyPoint point, int num ){
    	
    	if( num == 1 )
    	{
    		for( int i = 0; i < imgInfos.size(); i++ )
    		{
    			ImageInfo info = imgInfos.get(i);
        		float left_X = info.center.x - info.width/2;
        		float right_X = info.center.x + info.width/2;
        		
        		float up_Y = info.center.y - info.height/2;
        		float down_Y = info.center.y + info.height/2;
        		if( point.x >= left_X && point.x <= right_X && 
        				point.y >= up_Y && point.y <= down_Y )
        		{
        			/*
        			imgInfos.remove(info);
        			bmp.eraseColor(Color.TRANSPARENT);
    				canvas.drawBitmap(bmp, 0, 0, paint);
    				drawCircleInView();
    				drawImgs(1);
    				drawImgs(2);
    				*/
        			return i;
        		}
        	}
    	}
    	else if( num == 2 )
    	{
    		for( int i = 0; i < imgInfos_2.size(); i++ )
    		{
    			ImageInfo info = imgInfos_2.get(i);
        		float left_X = info.center.x - info.width/2;
        		float right_X = info.center.x + info.width/2;
        		
        		float up_Y = info.center.y - info.height/2;
        		float down_Y = info.center.y + info.height/2;
        		if( point.x >= left_X && point.x <= right_X && 
        				point.y >= up_Y && point.y <= down_Y )
        		{
        			/*
        			imgInfos_2.remove(info);
        			bmp.eraseColor(Color.TRANSPARENT);
    				canvas.drawBitmap(bmp, 0, 0, paint);
    				drawCircleInView();
    				drawImgs(1);
    				drawImgs(2);
    				*/
        			return i;
        		}
        	}
    	}
    	
    	return -1;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_page2, menu);
        return true;
    }

}
