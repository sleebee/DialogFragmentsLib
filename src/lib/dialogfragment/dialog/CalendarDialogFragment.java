package lib.dialogfragment.dialog;

import java.util.Calendar;
import java.util.Date;

import lib.dialogfragment.R;
import lib.dialogfragment.defs.DialogDefines;
import lib.dialogfragment.dialog.listener.DismissListener;
import lib.dialogfragment.dialog.listener.interfaces.OnDateConfirmListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;

/**
 * Date Picker Dialog that presents a Calendar View. <br />
 * I really dislike Android's default {@link DatePicker}. I find it really cumbersome; 
 * plus, {@link DatePickerDialog} looks kinda small. <br />
 * So I just went ahead and designed one myself. <br />
 * I try not to meddle with those pesky CallBacks that have to be implemented by our
 * activities, so there're a few methods to get the date out of our dialog
 * @author SleeBee (Federico Mendez)
 *
 */
public class CalendarDialogFragment extends DialogFragment {
	private static Calendar mCal;
	private DialogInterface.OnClickListener mListener;
	private OnDateConfirmListener mCallback;

	/**
	 * Mandatory empty constructor
	 */
	public CalendarDialogFragment() {}

	/**
	 * Use this method to create a new instance of the confirm dialog. <br />
	 * This signature lets you define almost every aspect of your dialog.
	 * @param title The title of the Dialog
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param listener user-defined callback for the confirm button
	 * @param date calendar's starting date
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(String title, String yesButton, String noButton, DialogInterface.OnClickListener listener, Date date) {
		CalendarDialogFragment ret = new CalendarDialogFragment();

		mCal = Calendar.getInstance();

		Bundle args = new Bundle();
		if(title != null && yesButton.length() > 0) {
			args.putString(DialogDefines.TITLE_KEY, title);
		}
		if(yesButton != null && yesButton.length() > 0) {
			args.putString(DialogDefines.YES_KEY, yesButton);
		}
		if(noButton != null && noButton.length() > 0) {
			args.putString(DialogDefines.NO_KEY, noButton);
		}
		if(listener != null) {
			ret.mListener = listener;
		}
		if(date != null) {
			mCal.setTime(date);
		}
		ret.setArguments(args);

		return ret;
	}
	/**
	 * Use this method to create a new instance of the confirm dialog. <br />
	 * This signature lets you define almost every aspect of your dialog.
	 * @param title The title of the Dialog
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param listener user-defined callback for the confirm button
	 * @param date calendar's starting date
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(String title, String yesButton, String noButton, DialogInterface.OnClickListener listener) {
		return newInstance(title, yesButton, noButton, listener, null);
	}

	/**
	 * Use this method to create a new instance of the edit dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param title The title of the Dialog
	 * @param listener user-defined callback for the confirm button
	 * @param date calendar's starting date
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(String title, DialogInterface.OnClickListener listener, Date date) {
		return newInstance(title, null, null, listener, date);
	}
	/**
	 * Use this method to create a new instance of the edit dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param title The title of the Dialog
	 * @param listener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(String title, DialogInterface.OnClickListener listener) {
		return newInstance(title, null, null, listener, null);
	}

	/**
	 * Use this method to create a new instance of an untitled edit dialog.
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param listener user-defined callback for the confirm button
	 * @param date calendar's starting date
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(String yesButton, String noButton, DialogInterface.OnClickListener listener, Date date) {
		return newInstance(null, yesButton, noButton, listener, date);
	}
	/**
	 * Use this method to create a new instance of an untitled edit dialog.
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param listener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(String yesButton, String noButton, DialogInterface.OnClickListener listener) {
		return newInstance(null, yesButton, noButton, listener, null);
	}
	/**
	 * Use this method to create a new instance of the edit dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param listener user-defined callback for the confirm button
	 * @param date calendar's starting date
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(DialogInterface.OnClickListener listener, Date date) {
		return newInstance(null, null, null, listener, date);
	}
	/**
	 * Use this method to create a new instance of the edit dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param listener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static CalendarDialogFragment newInstance(DialogInterface.OnClickListener listener) {
		return newInstance(null, null, null, listener, null);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		String title = null;
		String yes = null;
		String no = null;
		Bundle args = getArguments();
		if(args != null) {
			if(args.containsKey(DialogDefines.TITLE_KEY)){
				title = args.getString(DialogDefines.TITLE_KEY);
			}
			if(args.containsKey(DialogDefines.YES_KEY)) {
				yes = args.getString(DialogDefines.YES_KEY);
			} else {
				yes = getString(R.string.yes);
			}
			if(args.containsKey(DialogDefines.NO_KEY)) {
				no = args.getString(DialogDefines.NO_KEY);
			} else {
				no = getString(R.string.no);
			}
		}

		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.calendar_dialog_fragment, null);
		CalendarView cal = (CalendarView) view.findViewById(R.id.cal_calendar_dialog);
		cal.setDate(mCal.getTimeInMillis());
		cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				mCal.set(Calendar.YEAR, year);
				mCal.set(Calendar.MONTH, month);
				mCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			}
		});

		if(title != null) {
			builder.setTitle(title);
		}
		builder.setView(view);
		builder.setNegativeButton(no, new DismissListener());

		DialogInterface.OnClickListener listener = null;
		if(mCallback == null) {
			if(mListener == null) {
				listener = new DismissListener();
			} else {
				listener = mListener;
			}
		} else {
			listener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mCallback.onConfirm(mCal);
				}
			};
		}
		builder.setPositiveButton(yes, listener);

		return builder.create();
	}

	/**
	 * This method returns the selected date in a {@link Calendar} format for easier end-user manipulation.
	 * @return the selected date; or the current date if none was selected
	 */
	public static Calendar getDateCalendar() {
		return mCal;
	}

	/**
	 * This method returns the selected date in a {@link java.util.Date} format.
	 * @return the selected date; or the current date if none was selected
	 */
	public static Date getDate() {
		return new Date(mCal.getTimeInMillis());
	}

	/**
	 * This method returns the selected date in milliseconds, so it can be converted to any type you need.
	 * @return the milliseconds value of the selected date; or of the current date if none was selected
	 */
	public static long getDateMilis() {
		return mCal.getTimeInMillis();
	}

	@Override
	public void onAttach(Activity a) {
		super.onAttach(a);
		if(a instanceof OnDateConfirmListener) {
			mCallback = (OnDateConfirmListener) a;
		}
	}
}