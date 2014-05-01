package lib.dialogfragment.dialog.listener;
import android.content.DialogInterface;

public class DismissListener implements DialogInterface.OnClickListener {
	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();
	}

}
