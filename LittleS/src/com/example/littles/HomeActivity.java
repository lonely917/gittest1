package com.example.littles;

import com.example.littles.R.drawable;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class HomeActivity extends TabActivity {

	private ImageView tab1ImageView,tab2ImageView,tab3ImageView,tab4ImageView;
	public static ImageView tabImageViews[] = new ImageView[4] ;
	static TabHost tabHost;
    public TabWidget tabWidget;
    private int tabDrawablesOff[] = {R.drawable.tab1, R.drawable.tab2,
    		R.drawable.tab3, R.drawable.tab4};
    private int tabDrawablesOn[] = {R.drawable.tab_1, R.drawable.tab_2,
    		R.drawable.tab_3, R.drawable.tab_4};
    public static int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       
        setContentView(R.layout.activity_home);
 
        tabHost = getTabHost();
        tabWidget=getTabWidget();    
        
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("",getResources().getDrawable(R.drawable.tab_weixin_normal))
                .setContent(new Intent(this, Group1.class)));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("",getResources().getDrawable(R.drawable.tab_address_normal))
                .setContent(new Intent(this, Group2.class)));
        
        // This tab sets the intent flag so that it is recreated each time
        // the tab is clicked.
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("",getResources().getDrawable(R.drawable.tab_find_frd_normal))
                .setContent(new Intent(this, Page3.class)));
//        tabHost.addTab(tabHost.newTabSpec("tab3")
//                .setIndicator("",getResources().getDrawable(R.drawable.tab_find_frd_normal))
//                .setContent(new Intent(this, Page3.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("",getResources().getDrawable(R.drawable.tab_settings_normal))
                .setContent(new Intent(this, Page4.class)));
        
//        for(int i=0;i<tabWidget.getChildCount();i++){
//            TextView tv=(TextView)tabWidget.getChildAt(i).findViewById(android.R.id.title);
//            ImageView iv=(ImageView)tabWidget.getChildAt(i).findViewById(android.R.id.icon);
//            iv.setPadding(0, -5, 0, -10);
////            tv.setPadding(0, 10, 0, 0);
//            tv.setTextColor(Color.BLACK);
//            
//        }        
        
        intial();
        setListeners();
        
    }

	private void intial() {
//		tab1ImageView = (ImageView)findViewById(R.id.img_tab1);
//		tab2ImageView = (ImageView)findViewById(R.id.img_tab2);
//		tab3ImageView = (ImageView)findViewById(R.id.img_tab3);
//		tab4ImageView = (ImageView)findViewById(R.id.img_tab4);

		tabImageViews[0] = (ImageView)findViewById(R.id.img_tab1);
		tabImageViews[1] = (ImageView)findViewById(R.id.img_tab2);
		tabImageViews[2] = (ImageView)findViewById(R.id.img_tab3);
		tabImageViews[3] = (ImageView)findViewById(R.id.img_tab4);		
	}
	
    
	private void setListeners() {
//		tab1ImageView.setOnClickListener(new TabViewOnClickListener(0));
//		tab2ImageView.setOnClickListener(new TabViewOnClickListener(1));
//		tab3ImageView.setOnClickListener(new TabViewOnClickListener(2));
//		tab4ImageView.setOnClickListener(new TabViewOnClickListener(3));

		tabImageViews[0].setOnClickListener(new TabViewOnClickListener(0));
		tabImageViews[1].setOnClickListener(new TabViewOnClickListener(1));
		tabImageViews[2].setOnClickListener(new TabViewOnClickListener(2));
		tabImageViews[3].setOnClickListener(new TabViewOnClickListener(3));
	}
	
	
	public class TabViewOnClickListener implements OnClickListener
	{

		private String[] s = {"tab1","tab2","tab3","tab4"};
		private int tabviewchoose = 1;
		public TabViewOnClickListener()
		{
			
		}
		
		public TabViewOnClickListener(int i)
		{
			tabviewchoose = i;
		}
		
		@Override
		public void onClick(View v) {
			if(tabviewchoose != currentIndex)
			{
//				tabHost.setCurrentTab(tabviewchoose);
				tabHost.setCurrentTabByTag(s[tabviewchoose]);
				tabImageViews[tabviewchoose].setImageDrawable(getResources().getDrawable(tabDrawablesOn[tabviewchoose]));
				tabImageViews[currentIndex].setImageDrawable(getResources().getDrawable(tabDrawablesOff[currentIndex]));
				currentIndex = tabviewchoose;	
			}
			
		}
		
	}
	
	
}
