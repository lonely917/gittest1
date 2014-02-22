package com.example.littles;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends Activity{
	
	private ImageView detail_img;
	private TextView detail_name;
	private TextView detail_brand;
	private TextView detail_intro;
	private TextView detail_size;
	private TextView detail_times_num;
	private TextView detail_rank;
	private TextView detail_price;
	private TextView add_to_basket;
	//private ImageView[] detail_recommends;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        detail_img = (ImageView)findViewById(R.id.Page2_2_detail_img);
        detail_name = (TextView)findViewById(R.id.Page2_2_detail_name);
        detail_brand = (TextView)findViewById(R.id.Page2_2_detail_brand);
        detail_intro = (TextView)findViewById(R.id.Page2_2_detail_intro);
        detail_size = (TextView)findViewById(R.id.Page2_2_detail_size);
    	detail_times_num = (TextView)findViewById(R.id.Page2_2_detail_times_num);
    	detail_rank = (TextView)findViewById(R.id.Page2_2_detail_rank);
    	detail_price = (TextView)findViewById(R.id.Page2_2_detail_price);
    	add_to_basket = (TextView)findViewById(R.id.Page2_2_detail_add_to_basket);
    	add_to_basket.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Page2_2.addToBasket();
				Toast.makeText(DetailsActivity.this, "加入购物车成功！", Toast.LENGTH_SHORT).show();
				
			}
		});
    	/*
    	detail_recommends = new ImageView[5];
    	for( int i = 0; i < 5; i++ )
    	{
    		detail_recommends[i] = null;
    	}
        detail_recommends[0] = (ImageView)findViewById(
        		R.id.Page2_2_detail_recommend_1);
        detail_recommends[1] = (ImageView)findViewById(
        		R.id.Page2_2_detail_recommend_2);
        detail_recommends[2] = (ImageView)findViewById(
        		R.id.Page2_2_detail_recommend_3);
        detail_recommends[3] = (ImageView)findViewById(
        		R.id.Page2_2_detail_recommend_4);
        detail_recommends[4] = (ImageView)findViewById(
        		R.id.Page2_2_detail_recommend_5);
        */
    	Intent intent = getIntent();
    	DetailInfo detailInfo = (DetailInfo)intent.getSerializableExtra("detail");
    	setAllInfo(detailInfo);
    	
	}
	
	public void onBackPressed() {
		Intent intent = new Intent(DetailsActivity.this, Page2_2.class);
		// 把Activity转换成一个Window，然后转换成View
		Window w = Group2.group.getLocalActivityManager().startActivity("Page2_2Activity", intent);
		View view = w.getDecorView();
		// 设置要跳转的Activity显示为本ActivityGroup的内容
		Group2.group.setContentView(view);
	} 
	
	private void setTextInfo( TextView view, String str )
    {
    	String preString = view.getText().toString();
    	String cursString = preString + str;
    	view.setText(cursString);
    }
	
	private void setAllInfo( DetailInfo detailInfo )
	{
    	detail_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),
    			detailInfo.img_id));
		setTextInfo(detail_name, detailInfo.name);
		setTextInfo(detail_brand, detailInfo.brand);
		setTextInfo(detail_intro, detailInfo.intro);
		setTextInfo(detail_size, detailInfo.size);
		setTextInfo(detail_times_num, detailInfo.times_num);
		//setTextInfo(detail_rank, detailInfo.rank);
		setTextInfo(detail_price, detailInfo.price);
		/*
		int rec_num = detailInfo.rec_num;
		if( rec_num > 5 )
			rec_num = 5;
		for( int i = 0; i < rec_num; i++ )
		{
			detail_recommends[i].setVisibility(View.VISIBLE);
		}
		*/
	}
	 
}
