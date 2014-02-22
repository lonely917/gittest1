package com.example.littles;

import android.graphics.Bitmap;

public class ImageInfo {
	
	public MyPoint center;	//图片中心
	public float width;	//图片宽度
	public float height;	//图片高度
	public int resID;	//资源ID
	public Bitmap img;
	
	public ImageInfo( MyPoint p, float w, float h, int ID, Bitmap img ){
		this.center = p;
		this.width = w;
		this.height = h;
		this.resID = ID;
		this.img = img;
	}
	
}
