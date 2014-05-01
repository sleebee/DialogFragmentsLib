package lib.dialogfragment.dialog.support;

import lib.dialogfragment.R;
import lib.dialogfragment.defs.DialogDefines;
import lib.dialogfragment.dialog.listener.DismissListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Simple Confirm Dialog for the SUPPORT LIBRARY V4.
 * It's just a title, message, yes/no action.
 * While pressing <b>NO</b> will simply dismiss the dialog, 
 * the yes-pressing callback is user defined 
 * @author Federico Mendez
 *
 */
public class ConfirmDialogFragment extends DialogFragment {
	private static DialogInterface.OnClickListener mListener = null;

	/**
	 * Mandatory empty constructor
	 */
	public ConfirmDialogFragment() {}

	/**
	 * Use this method to create a new instance of the confirm dialog. <br />
	 * This signature lets you define almost every aspect of your dialog.
	 * @param title The title of the Dialog
	 * @param msg The body message of the Dialog
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param yesListener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static ConfirmDialogFragment newInstance(String title, String msg, String yesButton, String noButton, DialogInterface.OnClickListener yesListener) {
		ConfirmDialogFragment ret = new ConfirmDialogFragment();

		Bundle args = new Bundle();
		args.putString(DialogDefines.TITLE_KEY, title);
		args.putString(DialogDefines.MESSAGE_KEY, msg);
		if(yesButton != null && yesButton.length() > 0) {
			args.putString(DialogDefines.YES_KEY, yesButton);
		}
		if(noButton != null && noButton.length() > 0) {
			args.putString(DialogDefines.NO_KEY, noButton);
		}
		ret.setArguments(args);
		mListener = yesListener;

		return ret;
	}

	/**
	 * Use this method to create a new instance of the confirm dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param title The title of the Dialog
	 * @param msg The body message of the Dialog
	 * @param yesListener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static ConfirmDialogFragment newInstance(String title, String msg, DialogInterface.OnClickListener yesListener) {
		return newInstance(title, msg, null, null, yesListener);
	}

	/**
	 * This method is called automatically by the 
	 * {@link DialogFragment}'s <i>show</i> method. <br />
	 * Here is where the magic occurs
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		Bundle args = getArguments();
		String title = null;
		String msg = "";
		String yes = null;
		String no = null;
		if(args != null) {
			if(args.containsKey(DialogDefines.TITLE_KEY)){
				title = args.getString(DialogDefines.TITLE_KEY);
			}
			if(args.containsKey(DialogDefines.MESSAGE_KEY)){
				msg = args.getString(DialogDefines.MESSAGE_KEY);
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

		if(title != null) {
			builder.setTitle(title);
		}
		builder.setMessage(msg);
		builder.setPositiveButton(yes, mListener != null ? mListener : new DismissListener());
		builder.setNegativeButton(no, new DismissListener());

		return builder.create();
	}
}
