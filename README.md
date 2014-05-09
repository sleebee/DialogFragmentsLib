DialogFragmentsLib
==================

A collection of simple, yet customizable, DialogFragment implementations for Android

They come both in Android.app and Android.support.v4.app flavors.

Existing dialogs
----------------

* AlertDialogFragment: A simple Title-Message-Close dialog; mostly used to display errors and warnings
* ConfirmDialogFragment: A simple confirmation dialog. A DialogInterface.OnClickListener must be provided for the confirmation option
* EditDialogFragment: A dialog that allows you to edit a free-text field
* CalendarDialogFragment: Since I really, REALLY dislike Android's default date picker, and it's DatePickerDialog implementation is really small, I designed one with a larger CalendarView in it
 

Whishlist
---------

* FileChooserDialogFragment: If the dialog name is not indication enough, I want a dialog that helps me choose a file from the File System

Examples
--------

### AlertDialogFragment
```java
AlertDialogFragment dialog = AlertDialogFragment.newInstance("title", "message");
dialog.show(getActivity().getFragmentManager(), "my_alert");
```

### ConfirmDialogFragment
```java
/*
 * This is the listener for the confirmation action
 */
DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
  @Override
  public void onClick(DialogInterface dialog, int id) {
    // Do stuff here! 
  }
};
ConfirmDialogFragment dialog = ConfirmDialogFragment.newInstance("title", "message", "allow", "deny", listener);
dialog.show(getActivity().getFragmentManager(), "my_confirm");
```

### EditDialogFragment
```java
/*
 * This is the listener for the confirmation action
 */
DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
  @Override
  public void onClick(DialogInterface dialog, int id) {
    // example of stuff you can do here
    System.out.println(EditDialogFragment.getText());
  }
};
EditDialogFragment dialog = EditDialogFragment.newInstance("title", listener);
dialog.show(getActivity().getFragmentManager(), "my_edit");
```

### ConfirmDialogFragment
```java
/*
 * This is the listener for the confirmation action
 */
DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
  @Override
  public void onClick(DialogInterface dialog, int id) {
    // example of stuff you can do here
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      System.out.println(sdf.parse(CalendarDialogFragment.getDate()));
    }catch(ParseException pe) {
      Log.e(pe.getLocalizedMessage());
      pe.printStackTrace(System.err);
    }
  }
};
EditDialogFragment dialog = EditDialogFragment.newInstance("title", listener);
dialog.show(getActivity().getFragmentManager(), "my_date");
```
