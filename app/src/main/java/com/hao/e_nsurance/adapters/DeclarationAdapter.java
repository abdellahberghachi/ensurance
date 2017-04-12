package com.hao.e_nsurance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Declaration;
import com.hao.e_nsurance.classes.Dossier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeclarationAdapter extends ArrayAdapter<Declaration> {

	private int resource;
	private LayoutInflater inflater;
	private Context context;

	public DeclarationAdapter(Context ctx, int resourceId, List<Declaration> objects) {
		super( ctx, resourceId, objects );
		resource = resourceId;
		inflater = LayoutInflater.from( ctx );
		this.context = ctx;
	}
   
	@Override
	public View getView (int position, View view, ViewGroup parent ) {

		if (view == null){
			view = inflater.inflate(resource, null );
		}

		Declaration d = getItem( position );

		TextView titre = (TextView) view.findViewById(R.id.titre);
		titre.setText(d.getStreet() + ", " + d.getCity() + ", " + d.getCountry());

		return view;
	}
}