package com.example.dialogtesting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lib.dialogfragment.dialog.support.CalendarDialogFragment;
import lib.dialogfragment.dialog.support.EditDialogFragment;
import lib.dialogfragment.dialog.support.AlertDialogFragment;
import lib.dialogfragment.dialog.support.ConfirmDialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			final TextView testo = (TextView) rootView.findViewById(R.id.testo);
			Button alerta = (Button) rootView.findViewById(R.id.alerta);
			Button confirma = (Button) rootView.findViewById(R.id.confirma);
			Button edita = (Button) rootView.findViewById(R.id.edita);
			Button fecha = (Button) rootView.findViewById(R.id.fecha);

			alerta.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AlertDialogFragment dialog = AlertDialogFragment.newInstance("titulo", "esto es una alerta", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							testo.setText("alertado");
							dialog.dismiss();
						}
					});
					dialog.show(getFragmentManager(), "alert_dialog");
				}
			});

			confirma.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ConfirmDialogFragment dialog = ConfirmDialogFragment.newInstance("titulo", "necesito que confirmes", "confirmar", "negar", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							testo.setText("confirmado");
							dialog.dismiss();
						}
					});
					dialog.show(getFragmentManager(), "confirm_dialog");
				}
			});

			edita.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final EditDialogFragment dialogo = EditDialogFragment.newInstance("titulo", "setear", "cancelar", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							testo.setText(EditDialogFragment.getText());
						}
					});
					dialogo.show(getFragmentManager(), "edit_dialog");
				}
			});

			fecha.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date start;
					try{
						start = sdf.parse("14/09/1982");
					}catch(ParseException pe){
						pe.printStackTrace(System.err);
						start = new Date();
					}
					CalendarDialogFragment dialog = CalendarDialogFragment.newInstance("titulo", "setear", "cancelar", 
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									testo.setText(sdf.format(CalendarDialogFragment.getDate()));
								}
							}, start);
					dialog.show(getFragmentManager(), "edit_dialog");
					
				}
			});

			return rootView;
		}
	}

}
