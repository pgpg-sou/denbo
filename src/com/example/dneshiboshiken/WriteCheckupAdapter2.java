package com.example.dneshiboshiken;

import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class BindData2 {
  String text;
  Bitmap imageResourceId;

  public BindData2(String text, Bitmap id) {
    this.text = text;
    this.imageResourceId = id;
  }
}

class ViewHolder2 {
  TextView textView;
  ImageView imageView;
}

public class WriteCheckupAdapter2 extends ArrayAdapter<BindData2> {
	private LayoutInflater inflater;

	  public WriteCheckupAdapter2(Context context, List<BindData2> objects) {
	    super(context, 0, objects);
	    this.inflater = (LayoutInflater) context
	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    ViewHolder2 holder;
	    if (convertView == null) {
	      convertView = inflater.inflate(R.layout.write_pregnancy_item,
	        parent, false);
	      holder = new ViewHolder2();
	      holder.textView = (TextView) convertView
	        .findViewById(R.id.textview);
	      holder.imageView = (ImageView) convertView
	        .findViewById(R.id.imageview);
	      convertView.setTag(holder);
	    }
	    else {
	      holder = (ViewHolder2) convertView.getTag();
	    }

	    BindData2 data = getItem(position);
	    holder.textView.setText(data.text);
	    holder.imageView.setImageBitmap(data.imageResourceId);
	    return convertView;
	  }

 }