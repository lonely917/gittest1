package com.example.littles;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

//��һ����ǩҳ��ʾ��Activity���̳�ActivityGroup������������Activity
public class Group1 extends ActivityGroup {
	//���ڹ�����Group�е�����Activity	 
	public static ActivityGroup group;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		group = this;
	}

	@Override
	public void onBackPressed() {		
		//�Ѻ����¼�������Activity����
		group.getLocalActivityManager().getCurrentActivity().onBackPressed();
//		super.onBackPressed();
	}

	@Override
	protected void onStart() {
		super.onStart();
		//Ҫ��ת��Activity
		Intent intent = new Intent(this, Page1.class);
		// ��Activityת����һ��Window��Ȼ��ת����View
		Window w = group.getLocalActivityManager().startActivity("Page1Activity", intent);
		View view = w.getDecorView();
		//����Ҫ��ת��Activity��ʾΪ��ActivityGroup������
		group.setContentView(view);
	}
}