package com.example.littles;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Page1 extends Activity implements OnViewChangeListener {

	private MyScrollLayout mScrollLayout;
	private ImageView[] imgs;
	private int count;
	private int currentItem;
	private LinearLayout pointLLayout;
	private EditText searchEdit;
	private Gallery itemGallery, itemGallery2;
	private GridView itemGridView1;
	private ImageView searchButton;
  	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page1); 
//		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		initView();
		setListeners();
		Log.i("test", "page1oncreate");
	}

	private void setListeners() {
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Log.i("test", "onclickview");
//				Log.i("test", searchEdit.getText().toString()+"1");
				if(! searchEdit.getText().toString().isEmpty())
				{
					itemGridView1.setNumColumns(4);
					itemGridView1.setAdapter(new ImageAdapter4(Page1.this));					
				}
				else
				{
					itemGridView1.setNumColumns(2);
					itemGridView1.setAdapter(new ImageAdapter3(Page1.this));						
				}
				
				//关闭软键盘
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		});	
	}
	
	private void initView() {
		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout1);
		pointLLayout = (LinearLayout) findViewById(R.id.llayout2);
		searchButton = (ImageView) findViewById(R.id.searchbutton);
		searchEdit = (EditText) findViewById(R.id.editTextSearch);
		count = mScrollLayout.getChildCount();
		imgs = new ImageView[count];
		for (int i = 0; i < count; i++) {
			imgs[i] = (ImageView) pointLLayout.getChildAt(i);
			imgs[i].setEnabled(true);
			imgs[i].setTag(i);
		}
		currentItem = 0;
		imgs[currentItem].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);
		
//		itemGallery = (Gallery)findViewById(R.id.items_gallery);
//		itemGallery.setAdapter(new ImageAdapter3(this));
//		itemGallery.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		itemGallery.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		itemGallery2 = (Gallery)findViewById(R.id.items_gallery2);
//		itemGallery2.setAdapter(new ImageAdapter(this));
		
		itemGridView1 = (GridView)findViewById(R.id.itemGridView1);
		itemGridView1.setNumColumns(2);
		itemGridView1.setAdapter(new ImageAdapter3(Page1.this));
	}



	@Override
	public void OnViewChange(int position) {
		setcurrentPoint(position);
	}

	private void setcurrentPoint(int position) {
		if (position < 0 || position > count - 1 || currentItem == position) {
			return;
		}
		imgs[currentItem].setEnabled(true);
		imgs[position].setEnabled(false);
		currentItem = position;
	}	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list1, menu);
        return true;
    }

    public void onClickChangeActivity(View v)
    {
    	Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show();
//    	Intent intent = new Intent(this, SubForPage1.class);
//    	startActivity(intent);
    	
        Intent intent = new Intent(Page1.this, SubForPage1.class); 
        // 把Activity转换成一个Window，然后转换成View  
        Window w = Group1.group.getLocalActivityManager().startActivity("SubForPage1Activity",intent);  
        View view = w.getDecorView();  
         //设置要跳转的Activity显示为本ActivityGroup的内容  
        Group1.group.setContentView(view);      	
    }
    
    public class ImageAdapter extends BaseAdapter {
    	
    	private Integer[] mps = {
    	        R.drawable.itempic1,
    	        R.drawable.itempic2,
    	        R.drawable.itempic3,
    	        R.drawable.itempic4,
    	};
    	
    	private Context mContext;
    		public ImageAdapter(Context context) {
    		mContext = context;
    	}

    	public int getCount() { 
    		return mps.length;
    	}

    	public Object getItem(int position) {
    		return position;
    	}

    	public long getItemId(int position) {
    		return position;
    	}

    	public View getView(int position, View convertView, ViewGroup parent) {
    		ImageView image = new ImageView(mContext);
    		image.setImageResource(mps[position]);
    		image.setAdjustViewBounds(true);
//    		image.setLayoutParams(new Gallery.LayoutParams(
//    			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		image.setScaleType(ImageView.ScaleType.MATRIX);
    		image.setLayoutParams(new Gallery.LayoutParams(200,400));
    	    return image;
    	}

    }
    
    
    public class ImageAdapter2 extends BaseAdapter {
        private static final int ITEM_WIDTH = 136;
        private static final int ITEM_HEIGHT = 88;
        private final Context mContext;

        private final Integer[] mImageIds = {
    	        R.drawable.itempic1,
    	        R.drawable.itempic2,
    	        R.drawable.itempic3,
    	        R.drawable.itempic4,
        };

        private  float mDensity;

        public ImageAdapter2(Context c) {
            mContext = c;
            mDensity = c.getResources().getDisplayMetrics().density;
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                convertView = new ImageView(mContext);

                imageView = (ImageView) convertView;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new Gallery.LayoutParams(
                        (int) (ITEM_WIDTH * mDensity + 0.5f),
                        (int) (ITEM_HEIGHT * mDensity + 0.5f)));
            
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mImageIds[position]);

            return imageView;
        }
    }

    public class ImageAdapter3 extends BaseAdapter {
        public ImageAdapter3(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//                imageView.setLayoutParams(new GridView.LayoutParams(100,100));
                imageView.setAdjustViewBounds(false);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0, 0, 10);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);

            return imageView;
        }

        private Context mContext;  

        private Integer[] mThumbIds = {
    	        R.drawable.pagehot_1,
    	        R.drawable.pagehot_2,
    	        R.drawable.pagehot_3,
    	        R.drawable.pagehot_4,
        	};         

        }
    
    public class ImageAdapter4 extends BaseAdapter {
        public ImageAdapter4(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                imageView.setLayoutParams(new GridView.LayoutParams(130,130));
//                imageView.setAdjustViewBounds(false);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0, 0, 10);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);

            return imageView;
        }

        private Context mContext;  


        private Integer[] mThumbIds = {
    	        R.drawable.search1,
    	        R.drawable.search2,
    	        R.drawable.search3,
    	        R.drawable.search4,
    	        R.drawable.search5,
    	        R.drawable.search6,
    	        R.drawable.search7,
    	        R.drawable.search8,
        	};       
        }
}    

