package com.example.littles;

import java.io.Serializable;
import android.graphics.Bitmap;

public class DetailInfo implements Serializable{
	
	public int img_id;
	public String name;
	public String brand;
	public String intro;
	public String size;
	public String times_num;
	public String rank;
	public String price;
	public int rec_num;
	
	public DetailInfo( int img_id, String name, String brand, String intro,
			String size, String times_num, String rank, String price, int rec_num  )
	{
		this.img_id = img_id;
		this.name = name;
		this.brand = brand;
		this.intro = intro;
		this.size = size;
		this.times_num = times_num;
		this.rank = rank;
		this.price = price;
		this.rec_num = rec_num;
	}
}
