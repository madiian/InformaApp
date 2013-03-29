package org.witness.iwitness.utils.adapters;

import java.util.List;

import org.json.JSONException;
import org.witness.informacam.models.IMedia;
import org.witness.informacam.utils.Constants.App;
import org.witness.informacam.utils.Constants.Models;
import org.witness.iwitness.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GalleryGridAdapter extends BaseAdapter {
	List<IMedia> media;
	LayoutInflater li;
	Activity a;
	
	private final static String LOG = App.LOG;
	
	public GalleryGridAdapter(Activity a, List<IMedia> media) throws NullPointerException {
		this.media = media;
		this.a = a;
		li = LayoutInflater.from(a);
	}
	
	@Override
	public int getCount() {
		return media.size();
	}

	@Override
	public Object getItem(int position) {
		return media.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = li.inflate(R.layout.adapter_gallery_grid, null);
		
		ImageView iv = (ImageView) view.findViewById(R.id.gallery_thumb);
		LinearLayout iv_holder = (LinearLayout) view.findViewById(R.id.gallery_thumb_holder);
		if(media.get(position).isNew) {
			iv_holder.setBackgroundDrawable(a.getResources().getDrawable(R.drawable.extras_is_new_background));
		}
		
		Bitmap bitmap = media.get(position).getBitmap(media.get(position).bitmapThumb);
		iv.setImageBitmap(bitmap);
		
		try {
			if(!media.get(position).getBoolean(Models.IMediaManifest.Sort.IS_SHOWING)) {
				view.setVisibility(View.GONE);
			}
		} catch (JSONException e) {
			Log.e(LOG, e.toString());
			e.printStackTrace();
		}
		
		return view;
	}

}