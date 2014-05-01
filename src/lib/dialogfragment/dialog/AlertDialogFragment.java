package lib.dialogfragment.dialog;

import lib.dialogfragment.R;
import lib.dialogfragment.defs.DialogDefines;
import lib.dialogfragment.dialog.listener.DismissListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Simple Alert Dialog.
 * It's just a title, message, confirm action. <br />
 * Confirmation callback of this dialog can be 
 * either user defined or a dismissive default
 * @author (SleeBee) Federico Mendez
 *
 */
public class AlertDialogFragment extends DialogFragment {
	private static DialogInterface.OnClickListener mListener;

	/**
	 * Mandatory empty constructor
	 */
	public AlertDialogFragment() {}

	/**
	 * Use this method to create a new instance of the alert dialog. <br />
	 * This signature lets you define almost every aspect of your dialog.
	 * @param title The title of the Dialog
	 * @param msg The body message of the Dialog
	 * @param yesButton Text to be displayed on the confirm button
	 * @param yesListener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static AlertDialogFragment newInstance(String title, String msg, String yesButton, DialogInterface.OnClickListener yesListener) {
		AlertDialogFragment ret = new AlertDialogFragment();

		Bundle args = new Bundle();
		args.putString(DialogDefines.TITLE_KEY, title);
		args.putString(DialogDefines.MESSAGE_KEY, msg);
		if(yesButton != null && yesButton.length() > 0) {
			args.putString(DialogDefines.YES_KEY, yesButton);
		}
		ret.setArguments(args);
		mListener = yesListener;

		return ret;
	}

	/**
	 * Use this method to create a new instance of the confirm dialog 
	 * while using the Resources <i>yes</i> strings
	 * @param title The title of the Dialog
	 * @param msg The body message of the Dialog
	 * @param yesListener user-defined callback for the confirm button
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static AlertDialogFragment newInstance(String title, String msg, DialogInterface.OnClickListener yesListener) {
		return newInstance(title, msg, null, yesListener);
	}

	/**
	 * Use this method to create a new instance of the confirm dialog 
	 * while using the Resources <i>yes</i> string. <br />
	 * Confirmation of this dialog will simply dismiss it.
	 * @param title The title of the Dialog
	 * @param msg The body message of the Dialog
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static AlertDialogFragment newInstance(String title, String msg) {
		return newInstance(title, msg, null);
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
		}

		if(title != null) {
			builder.setTitle(title);
		}
		builder.setMessage(msg);
		builder.setPositiveButton(yes, mListener != null ? mListener : new DismissListener());

		return builder.create();
	}
}
