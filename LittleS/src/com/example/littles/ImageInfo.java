package com.example.littles;

import android.graphics.Bitmap;

public class ImageInfo {
	
	public MyPoint center;	//ͼƬ����
	public float width;	//ͼƬ���
	public float height;	//ͼƬ�߶�
	public int resID;	//��ԴID
	public Bitmap img;
	
	public ImageInfo( MyPoint p, float w, float h, int ID, Bitmap img ){
		this.center = p;
		this.width = w;
		this.height = h;
		this.resID = ID;
		this.img = img;
	}
	
}
