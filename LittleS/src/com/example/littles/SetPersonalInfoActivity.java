package com.example.littles;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SetPersonalInfoActivity extends Activity{
	
	LinearLayout setHeadLayout;
	LinearLayout setNicknameLayout;
	TextView nickNameTextView;
	ImageView headImageView;
	
	final int SetHeadFromGallery = 1;
	final int SetHeadFromCamera = 2;
	final int SetNickname_RequestCode = 4;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_set_personnalinfo);
        
        setHeadLayout = (LinearLayout)findViewById(R.id.set_head_layout);
        setNicknameLayout = (LinearLayout)findViewById(R.id.set_nickname_layout);
        nickNameTextView = (TextView)findViewById(R.id.set_nickname_textview);
        headImageView = (ImageView)findViewById(R.id.set_head_imageview);
        
        setNicknameLayout.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SetPersonalInfoActivity.this, SetNickNameActivity.class);
				startActivityForResult(intent, SetNickname_RequestCode);
			}
		});
        
        
        setHeadLayout.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(SetPersonalInfoActivity.this);
				builder.setTitle("设置头像");
				String[] items = {"从相册中选择","拍照"};
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if( which == 0 )
						{
							Intent intent = new Intent();
//							intent.setAction(Intent.ACTION_PICK);
							intent.setAction(Intent.ACTION_GET_CONTENT);
							intent.setType("image/*");
							
							intent.putExtra("crop", "true");
							intent.putExtra("aspectX", 1);
							intent.putExtra("aspectY", 1);
							intent.putExtra("outputX", 80);
							intent.putExtra("outputY", 80);
							intent.putExtra("return-data", true);
							startActivityForResult(intent, SetHeadFromGallery);
						}
						else
						{
//							Intent intent = new Intent();
//							intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//							intent.putExtra("crop", "true");
//							intent.putExtra("aspectX", 1);
//							intent.putExtra("aspectY", 1);
//							intent.putExtra("outputX", 80);
//							intent.putExtra("outputY", 80);
//							intent.putExtra("return-data", true);
//							startActivityForResult(intent,SetHeadFromCamera);
						}
					}
				});
				builder.create().show();
			}
		});

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode)
		{
			case SetNickname_RequestCode:
				if( resultCode == RESULT_OK )
				{
					String nicknameString = data.getStringExtra("nickname");
					nickNameTextView.setText(nicknameString);
//					Toast.makeText(this, nicknameString, Toast.LENGTH_SHORT).show();
				}
				break;
			case SetHeadFromGallery:
				if( resultCode == RESULT_OK )
				{	 
					Bitmap image = data.getParcelableExtra("data");
					 if( image == null )
						 return;
					 Bitmap newBitmap = ImageTool.toRoundCorner(image, 10);
					 if( newBitmap != null )
					 {
				        headImageView.setImageBitmap(newBitmap);
				        Intent img_data = new Intent();
				        setResult(RESULT_OK, img_data);
				        img_data.putExtra("img", newBitmap);
					 }
				}
				break;
			case SetHeadFromCamera:
				if( resultCode == RESULT_OK  )
				{
					 Bitmap image = data.getParcelableExtra("data");
					 if( image == null )
						 return;
					 Bitmap newBitmap = ImageTool.toRoundCorner(image, 10);
					 if( newBitmap != null )
					 {
						headImageView.setImageBitmap(newBitmap);
				        Intent img_data = new Intent();
				        setResult(RESULT_OK, img_data);
				        img_data.putExtra("img", newBitmap);
					 }
				}
				break;
		}
	}
	
    public void onClickForBtnReturn(View v)
    {
    	finish();
    }	
}



