package com.example.littles;


import java.util.zip.Inflater;

import com.example.littles.R.drawable;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Page3 extends Activity {

	private RelativeLayout[] itemLayout = new RelativeLayout[6];
	private ImageView[] itemImagedelete = new ImageView[6];
	private ImageView[] itemImageshare = new ImageView[6];
	private ImageView[] itemImagefav = new ImageView[6];
	private ImageView[] itemImagedec = new ImageView[6];
	private ImageView[] itemImageinc = new ImageView[6];
	private TextView[] itemTextnums = new TextView[6];
	private LinearLayout[] shareLayout = new LinearLayout[4];
	private RelativeLayout layout4page3;
	private int width,height;
	private Button button4buy, button4send;
	int mask = 0x111111;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
		Log.i("test", "page3oncreate");
        initview();
        setListeners();
        drawBackground();
    }


	private void drawBackground() {
		
        width = getWindowManager().getDefaultDisplay().getWidth();   
        height = getWindowManager().getDefaultDisplay().getHeight();
//        MyView myview = new MyView(this);
//        RelativeLayout.LayoutParams myview_layout = new RelativeLayout.LayoutParams(200,200);
//        myview_layout.setMargins(20, 20, 0, 0);
//        myview.setLayoutParams(myview_layout);
//        
//        layout4page3.addView(myview);
        
//        Bitmap bmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);//只读,不能直接在bmp上画
        Bitmap newb = Bitmap.createBitmap( width, height, Config.ARGB_8888 );
        
        Canvas canvasTemp = new Canvas( newb );
        canvasTemp.drawColor(getResources().getColor(R.color.mainclolor));
        
        Paint p = new Paint();
        String familyName ="宋体";
        Typeface font = Typeface.create(familyName,Typeface.BOLD);
        p.setColor(getResources().getColor(R.color.comcolor));
        p.setTypeface(font);
        p.setTextSize(22);
        p.setStyle(Style.STROKE);
        p.setStrokeWidth(5);
//        canvasTemp.drawText("写字。。。",100,100,p);
//        canvasTemp.drawRect(new Rect(100, 100, 200, 200), p); 
//        canvasTemp.drawBitmap(bmp, 50, 50, p);//画图        
        RectF oval = new RectF(-1*width/3-10, 60, width/3, height-30);
        canvasTemp.drawArc(oval, 270, 180, true, p);
        Drawable drawable = new BitmapDrawable(newb);
        layout4page3.setBackgroundDrawable(drawable);
		
	}


	private void setListeners() {
		for(int i=0;i<6;i++)
		{
			itemImagedelete[i].setOnClickListener(new DeleteOnClickListener(i));
			itemImagefav[i].setOnClickListener(new FavOnClickListener(i));
			itemImageinc[i].setOnClickListener(new IncOnClickListener(i));
			itemImagedec[i].setOnClickListener(new DecOnClickListener(i));
		}

		button4buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Page3.this, RouteActivity.class);
				startActivity(intent);	
//		        Intent intent = new Intent(Page3.this, SubForPage1.class); 
//		        // 把Activity转换成一个Window，然后转换成View  
//		        Window w = Group1.group.getLocalActivityManager().startActivity("SubForPage1Activity",intent);  
//		        View view = w.getDecorView();  
//		         //设置要跳转的Activity显示为本ActivityGroup的内容  
//		        Group1.group.setContentView(view); 				
			}
		});
		
		button4send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Page3.this, OrderActivity.class);
				startActivity(intent);
			}
		});
	}


	private void initview() {
        itemLayout[0] = (RelativeLayout) findViewById(R.id.goods1);
        itemLayout[1] = (RelativeLayout) findViewById(R.id.goods2);
        itemLayout[2] = (RelativeLayout) findViewById(R.id.goods3);
        itemLayout[3] = (RelativeLayout) findViewById(R.id.goods4);
        itemLayout[4] = (RelativeLayout) findViewById(R.id.goods5);
        itemLayout[5] = (RelativeLayout) findViewById(R.id.goods6);
        
        itemImagedelete[0] = (ImageView) findViewById(R.id.delete1);
        itemImagedelete[1] = (ImageView) findViewById(R.id.delete2);
        itemImagedelete[2] = (ImageView) findViewById(R.id.delete3);
        itemImagedelete[3] = (ImageView) findViewById(R.id.delete4);
        itemImagedelete[4] = (ImageView) findViewById(R.id.delete5);
        itemImagedelete[5] = (ImageView) findViewById(R.id.delete6);
		
        layout4page3 = (RelativeLayout) findViewById(R.id.layout4page3);
        
        itemImageshare[0] = (ImageView) findViewById(R.id.share1);
        itemImageshare[1] = (ImageView) findViewById(R.id.share2);
        itemImageshare[2] = (ImageView) findViewById(R.id.share3);
        itemImageshare[3] = (ImageView) findViewById(R.id.share4);
        itemImageshare[4] = (ImageView) findViewById(R.id.share5);
        itemImageshare[5] = (ImageView) findViewById(R.id.share6);
 
        itemImagefav[0] = (ImageView) findViewById(R.id.fav1);
        itemImagefav[1] = (ImageView) findViewById(R.id.fav2);
        itemImagefav[2] = (ImageView) findViewById(R.id.fav3);
        itemImagefav[3] = (ImageView) findViewById(R.id.fav4);
        itemImagefav[4] = (ImageView) findViewById(R.id.fav5);
        itemImagefav[5] = (ImageView) findViewById(R.id.fav6);
        
        itemImagedec[0] = (ImageView) findViewById(R.id.decrease1);
        itemImagedec[1] = (ImageView) findViewById(R.id.decrease2);
        itemImagedec[2] = (ImageView) findViewById(R.id.decrease3);
        itemImagedec[3] = (ImageView) findViewById(R.id.decrease4);
        itemImagedec[4] = (ImageView) findViewById(R.id.decrease5);
        itemImagedec[5] = (ImageView) findViewById(R.id.decrease6);
        
        itemImageinc[0] = (ImageView) findViewById(R.id.increase1);
        itemImageinc[1] = (ImageView) findViewById(R.id.increase2);
        itemImageinc[2] = (ImageView) findViewById(R.id.increase3);
        itemImageinc[3] = (ImageView) findViewById(R.id.increase4);
        itemImageinc[4] = (ImageView) findViewById(R.id.increase5);
        itemImageinc[5] = (ImageView) findViewById(R.id.increase6);
        
        itemTextnums[0] = (TextView) findViewById(R.id.num1);
        itemTextnums[1] = (TextView) findViewById(R.id.num2);
        itemTextnums[2] = (TextView) findViewById(R.id.num3);
        itemTextnums[3] = (TextView) findViewById(R.id.num4);
        itemTextnums[4] = (TextView) findViewById(R.id.num5);
        itemTextnums[5] = (TextView) findViewById(R.id.num6);
        
        button4buy = (Button) findViewById(R.id.buy_button);
        button4send = (Button) findViewById(R.id.send_button);
        
//      View fv = LayoutInflater.from(this).inflate(R.layout.share, null);  
//      shareLayout[0] = (LinearLayout)fv.findViewById(R.id.l1_share);
//      shareLayout[1] = (LinearLayout)fv.findViewById(R.id.l2_share);
//      shareLayout[2] = (LinearLayout)fv.findViewById(R.id.l3_share);
//      
//      shareLayout[0].setClickable(true);
//      shareLayout[0].setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Log.i("test", "onclick");
//				setContentView(R.layout.share);
//			}
//		});        
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_page3, menu);
        return true;
    }

	public void onClickForShareLayout(View v)
	{
		setContentView(R.layout.share);
	}
	
	public void OnClickForShare(View v)
	{
		final Dialog dialog = new Dialog(this);	
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.share);
	
//        View fv = LayoutInflater.from(this).inflate(R.layout.share, null);
//        dialog.setContentView(fv);
		
		shareLayout[0] = (LinearLayout) dialog.findViewById(R.id.l1_share);
		shareLayout[1] = (LinearLayout) dialog.findViewById(R.id.l2_share);
		shareLayout[2] = (LinearLayout) dialog.findViewById(R.id.l3_share);
		shareLayout[3] = (LinearLayout) dialog.findViewById(R.id.l4_share);
		
		shareLayout[0].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("test", "onclick");
				dialog.cancel();
				Intent intent1 = new Intent(Page3.this,ShareActivity.class);
				startActivity(intent1);
			}
		});

		shareLayout[1].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("test", "onclick");
				dialog.cancel();
				Intent intent1 = new Intent(Page3.this,ShareActivity.class);
				startActivity(intent1);
			}
		});
		
		shareLayout[3].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("test", "onclick");
				dialog.cancel();
			}
		});
				
//		shareLayout[0].setOnClickListener(new ShareOnClickListener());
//		shareLayout[1].setOnClickListener(new ShareOnClickListener());
		
		dialog.show();
	}
	
	public class ShareOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Log.i("test", "onclick");
//			dialog.cancel();
			Intent intent1 = new Intent(Page3.this,ShareActivity.class);
			startActivity(intent1);			
			
		}		
	}
	
	public class DeleteOnClickListener implements OnClickListener
	{
		int index;
		public DeleteOnClickListener(int i)
		{
			index = i;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			itemLayout[index].setVisibility(8);
//			RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) itemLayout[5].getLayoutParams();
//			lp.setMargins(100, 100, 0, 0);
//			itemLayout[5].setLayoutParams(lp);
//			
//			RelativeLayout.LayoutParams lp2 = (android.widget.RelativeLayout.LayoutParams) itemLayout[4].getLayoutParams();
//			itemLayout[4].setLayoutParams(lp2);
//			lp2.setMargins(300, 300, 0, 0);
			
		}
		
	}
	
	public class FavOnClickListener implements OnClickListener
	{
		int index;
		public FavOnClickListener(int i)
		{
			index = i;
		}
		@Override
		public void onClick(View v) {
			mask = mask ^ (1<<index);
			
			int result = mask & (1<<index);
			if(result != 0)
			{
				itemImagefav[index].setImageDrawable(getResources().getDrawable(drawable.icon_fav1));
			}
			else
			{
				itemImagefav[index].setImageDrawable(getResources().getDrawable(drawable.icon_fav2));
			}
		}
		
	}
	
	public class IncOnClickListener implements OnClickListener
	{
		int index;
		public IncOnClickListener(int i)
		{
			index = i;
		}
		@Override
		public void onClick(View v) {
			int num = Integer.parseInt(itemTextnums[index].getText().toString());
			if(num <10)
			{
				num++;
				itemTextnums[index].setText(""+num);
			}
		}
		
	}	

	public class DecOnClickListener implements OnClickListener
	{
		int index;
		public DecOnClickListener(int i)
		{
			index = i;
		}
		@Override
		public void onClick(View v) {
			int num = Integer.parseInt(itemTextnums[index].getText().toString());
			if(num >1)
			{
				num--;
				itemTextnums[index].setText(""+num);
				if(num == 0)
				{
					itemLayout[index].setVisibility(8);
				}
			}
			
		}
		
	}
	
	private class MyView extends View
	{
	     public MyView(Context context){
	        super(context) ;
	     }
	     /*重写onDraw（）*/
	     @Override
	     protected void onDraw(Canvas canvas)
	     {   
	         super.onDraw(canvas);
	         /*设置背景为白色*/
//	         canvas.drawColor(Color.WHITE);
	          Paint paint=new Paint();
	          /*去锯齿*/
	          paint.setAntiAlias(true);
	          /*设置paint的颜色*/
	          paint.setColor(Color.RED);
	          /*设置paint的 style 为STROKE：空心*/
	          paint.setStyle(Paint.Style.STROKE);
	          /*设置paint的外框宽度*/
	          paint.setStrokeWidth(3);
	          /*画一个空心圆形*/
	          canvas.drawCircle(40, 40, 30, paint);
	          /*画一个空心正方形*/
	          canvas.drawRect(10, 90, 70, 150, paint);
	          /*画一个空心长方形*/
	          canvas.drawRect(10, 170, 70,200, paint);
	          /*画一个空心椭圆形*/
	          RectF re=new RectF(10,220,70,250);
	          canvas.drawOval(re, paint);
	          /*画一个空心三角形*/
	          Path path=new Path();
	          path.moveTo(10, 330);
	          path.lineTo(70,330);
	          path.lineTo(40,270);
	          path.close();
	          canvas.drawPath(path, paint);
	          /*画一个空心梯形*/
	          Path path1=new Path();
	          path1.moveTo(10, 410);
	          path1.lineTo(70,410);
	          path1.lineTo(55,350);
	          path1.lineTo(25, 350);
	          path1.close();
	          canvas.drawPath(path1, paint);
	          
	          /*设置paint 的style为 FILL：实心*/
	          paint.setStyle(Paint.Style.FILL);
	          /*设置paint的颜色*/
	          paint.setColor(Color.BLUE);
	          /*画一个实心圆*/
	          canvas.drawCircle(120,40,30, paint);
	          /*画一个实心正方形*/
	          canvas.drawRect(90, 90, 150, 150, paint);
	          /*画一个实心长方形*/
	          canvas.drawRect(90, 170, 150,200, paint);
	          /*画一个实心椭圆*/
	          RectF re2=new RectF(90,220,150,250);
	          canvas.drawOval(re2, paint);
	          /*画一个实心三角形*/
	          Path path2=new Path();
	          path2.moveTo(90, 330);
	          path2.lineTo(150,330);
	          path2.lineTo(120,270);
	          path2.close();
	          canvas.drawPath(path2, paint);
	          /*画一个实心梯形*/
	          Path path3=new Path();
	          path3.moveTo(90, 410);
	          path3.lineTo(150,410);
	          path3.lineTo(135,350);
	          path3.lineTo(105, 350);
	          path3.close();
	          canvas.drawPath(path3, paint);
	          /*设置渐变色*/
	          Shader mShader=new LinearGradient(0,0,100,100,
	                  new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW},
	                  null,Shader.TileMode.REPEAT);
	          paint.setShader(mShader);
	        
	          /*画一个渐变色圆*/
	          canvas.drawCircle(200,40,30, paint);
	          /*画一个渐变色正方形*/
	          canvas.drawRect(170, 90, 230, 150, paint);
	          /*画一个渐变色长方形*/
	          canvas.drawRect(170, 170, 230,200, paint);
	          /*画一个渐变色椭圆*/
	          RectF re3=new RectF(170,220,230,250);
	          canvas.drawOval(re3, paint);
	          /*画一个渐变色三角形*/
	          Path path4=new Path();
	          path4.moveTo(170,330);
	          path4.lineTo(230,330);
	          path4.lineTo(200,270);
	          path4.close();
	          canvas.drawPath(path4, paint);
	          /*画一个渐变色梯形*/
	          Path path5=new Path();
	          path5.moveTo(170, 410);
	          path5.lineTo(230,410);
	          path5.lineTo(215,350);
	          path5.lineTo(185, 350);
	          path5.close();
	          canvas.drawPath(path5, paint);
	          
	          /*写字*/
	          paint.setTextSize(24);  
	     }
	}	
}
