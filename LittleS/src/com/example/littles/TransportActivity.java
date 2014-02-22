package com.example.littles;

import android.os.Bundle;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class TransportActivity extends Activity {

	private ImageView transChooseImage;
	private RelativeLayout layout4Activity;
	private int args, imgstatus=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getIntent().getExtras().getInt("ARGS");
        setContentView(R.layout.activity_transport);
        initView();
        setListener();
    }

    private void initView() {
		transChooseImage = (ImageView) findViewById(R.id.tranchoice);
		layout4Activity = (RelativeLayout) findViewById(R.id.layout4transport);
		switch (args) {
		case 1:
			layout4Activity.setBackgroundDrawable(getResources().getDrawable(R.drawable.map1));
			break;
		case 3:
			layout4Activity.setBackgroundDrawable(getResources().getDrawable(R.drawable.map2));
			break;
		default:
			break;
		}
	}

	private void setListener() {
		transChooseImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				final Dialog dialog = new Dialog(v.getContext());	
//				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//				dialog.setContentView(R.layout.share);
//		        View fv = LayoutInflater.from(v.getContext()).inflate(R.layout.share,null);
//		        dialog.setContentView(fv);				
//				dialog.show();
				if(imgstatus == 0)
				{
					transChooseImage.setImageResource(R.drawable.tran_choice_ex);
					imgstatus = 1;					
				}
				else
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
					builder.setTitle("保存地图?")
					.setPositiveButton("是", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getApplicationContext(), "地图保存至相册", Toast.LENGTH_SHORT)
							.show();	
							imgstatus = 0;			
							transChooseImage.setImageResource(R.drawable.tran_choice);
						}
					})
					.setNegativeButton("否", null)
					.show();
				}

			}
		});
		
		
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_transport, menu);
        return true;
    }

    public void onClickForBtnReturn(View v)
    {
    	finish();
    }
}
