package com.hao.e_nsurance.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Dossier;
import com.hao.e_nsurance.classes.Temoin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DossierAdapter extends ArrayAdapter<Dossier> {

	private int resource;
	private LayoutInflater inflater;
	private Context context;

	public DossierAdapter(Context ctx, int resourceId, List<Dossier> objects) {
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

		Dossier d = getItem( position );

		TextView titre = (TextView) view.findViewById(R.id.titre);
		titre.setText("Titre");

		TextView location = (TextView) view.findViewById(R.id.location);
		location.setText("Localisation");

		TextView date = (TextView) view.findViewById(R.id.date);
		assert d != null;
		date.setText(dateToString(d.getDate_ouverture()));

		TextView statut = (TextView) view.findViewById(R.id.statut);
		statut.setText(d.getStatut());

		return view;
	}

	public String dateToString(Date date){
		SimpleDateFormat dest = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String result = dest.format(date);
		return result;
	}
}