package lib.dialogfragment.dialog;

import lib.dialogfragment.R;
import lib.dialogfragment.defs.DialogDefines;
import lib.dialogfragment.dialog.listener.DismissListener;
import lib.dialogfragment.dialog.listener.interfaces.OnEditConfirmListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Support (v4) Dialog with an editable text box embedded. <br />
 * While mostly customizable, I didn't want to enter the world of callbacks, 
 * so I just added a method to get the text straight out of the dialog.<br />
 * Not the cleanest or prettiest way, but still, I didn't want to start adding callbacks.<br />
 * Still, <b>MUST</b> call the {@link getText} method after using this dialog 
 * in order to use the entered text. 
 * @author SleeBee (Federico Mendez)
 *
 */
public class EditDialogFragment extends DialogFragment {
	private static EditText mEditText;
	private DialogInterface.OnClickListener mListener = null;
	private OnEditConfirmListener mCallback;

	/**
	 * Mandatory empty constructor
	 */
	public EditDialogFragment() {}

	/**
	 * Use this method to create a new instance of the edit dialog. <br />
	 * This signature lets you define almost every aspect of your dialog.
	 * @param title The title of the Dialog
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param listener Your listener/callback reference for the confirmation of the dialog
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static EditDialogFragment newInstance(String title, String yesButton, String noButton, DialogInterface.OnClickListener listener) {
		EditDialogFragment ret = new EditDialogFragment();

		mEditText = null;
		Bundle args = new Bundle();
		if(title != null && title.length() > 0) {
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
		ret.setArguments(args);

		return ret;
	}

	/**
	 * Use this method to create a new instance of the edit dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param title The title of the Dialog
	 * @param listener Your listener/callback reference for the confirmation of the dialog
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static EditDialogFragment newInstance(String title, DialogInterface.OnClickListener listener) {
		return newInstance(title, null, null, listener);
	}

	/**
	 * Use this method to create a new instance of an untitled edit dialog.
	 * @param yesButton Text to be displayed on the confirm button
	 * @param noButton Text to be displayed on the deny button
	 * @param listener Your listener/callback reference for the confirmation of the dialog
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static EditDialogFragment newInstance(String yesButton, String noButton, DialogInterface.OnClickListener listener) {
		return newInstance(null, yesButton, noButton, listener);
	}

	/**
	 * Use this method to create a new instance of the edit dialog 
	 * while using the Resources <i>yes</i> and <i>no</i> strings
	 * @param listener Your listener/callback reference for the confirmation of the dialog
	 * @return a ready to {@link DialogFragment.show} dialog
	 */
	public static EditDialogFragment newInstance(DialogInterface.OnClickListener listener) {
		return newInstance(null, null, null);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		Bundle args = getArguments();
		String title = null;
		String yes = null;
		String no = null;
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
		View v = inflater.inflate(R.layout.edit_dialog_fragment, null);
		mEditText = (EditText) v.findViewById(R.id.edt_edit_dialog);

		builder.setView(v);
		if(title != null) {
			builder.setTitle(title);
		}
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
					mCallback.onConfirm(mEditText.getText().toString());
				}
			};
		}
		builder.setPositiveButton(yes, listener);
		builder.setNegativeButton(no, new DismissListener());

		return builder.create();
	}

	/**
	 * Probably the most important method of the class. 
	 * This static method lets you obtain the imputed text
	 * @return The text that was entered on the dialog
	 */
	public static String getText() {
		String ret = "";
		if(mEditText != null && mEditText.getText() != null) {
			ret = mEditText.getText().toString();
		}
		return ret;
	}

	@Override
	public void onAttach(Activity a) {
		super.onAttach(a);
		if(a instanceof OnEditConfirmListener) {
			mCallback = (OnEditConfirmListener) a;
		}
	}
}