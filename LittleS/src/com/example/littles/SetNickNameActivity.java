package com.example.littles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SetNickNameActivity extends Activity{

	TextView nicknameTextView;
	Button affirmBtn;
	static boolean affirm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_nickname);
        
        affirm = false;
        nicknameTextView = (TextView)findViewById(R.id.setting_nickname_edittext);
        
        affirmBtn = (Button)findViewById(R.id.setting_nickname_affirm_btn);
        affirmBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if( nicknameTextView.getText().toString().equals("") )
					Toast.makeText(SetNickNameActivity.this, "不能为空哦,亲", Toast.LENGTH_SHORT).show();
				else
				{
					affirm = true;
					Toast.makeText(SetNickNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	//返回键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent data = new Intent();
			if( affirm )
			{
				setResult(RESULT_OK, data);
				data.putExtra("nickname", nicknameTextView.getText().toString());
			}
			else
				data.putExtra("nickname", "");
			SetNickNameActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
    public void onClickForBtnReturn(View v)
    {
    	finish();
    }	
}
