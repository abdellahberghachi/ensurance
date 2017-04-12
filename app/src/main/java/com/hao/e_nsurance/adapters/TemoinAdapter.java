package com.hao.e_nsurance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Temoin;

import java.util.List;

public class TemoinAdapter extends ArrayAdapter<Temoin> {

	private int resource;
	private LayoutInflater inflater;
	private Context context;
	private OnButtonClickListener onButtonClickListener;

	public TemoinAdapter(Context ctx, int resourceId, List<Temoin> objects, OnButtonClickListener onButtonClickListener) {
		super( ctx, resourceId, objects );
		resource = resourceId;
		inflater = LayoutInflater.from( ctx );
		this.context = ctx;
		this.onButtonClickListener = onButtonClickListener;
	}
   
	@Override
	public View getView (final int position, View view, ViewGroup parent ) {

		if (view == null){
			view = inflater.inflate(resource, null );
		}

		Temoin t = getItem( position );

		TextView nom = (TextView) view.findViewById(R.id.nom);
		nom.setText(t.getNom() + " " + t.getPrenom());
		ImageView modifier = (ImageView) view.findViewById(R.id.modifier);
		modifier.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onButtonClickListener.onModify(position);
			}
		});

		ImageView supprimer = (ImageView) view.findViewById(R.id.supprimer);
		supprimer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onButtonClickListener.onDelete(position);
			}
		});

		return view;
       
	}

	public interface OnButtonClickListener {
		void onModify(int position);
		void onDelete(int position);
	}
}