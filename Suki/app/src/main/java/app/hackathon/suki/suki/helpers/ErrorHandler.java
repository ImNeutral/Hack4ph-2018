package app.hackathon.suki.suki.helpers;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
public class ErrorHandler {

    //EZZZZZZZZZZZZZZ Alert Dialog

    static AlertDialog alertDialog;

    public static void showThrowableDialogError(Throwable throwable, Activity mActivity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Error");
        builder.setMessage("Oopsy! Something's not right. :( Please try again.\n"+throwable.getMessage());
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        if(!mActivity.isFinishing()) {
            mActivity.runOnUiThread(new Runnable() {
                @Override

                public void run() {
                    try {
                        alertDialog = builder.create();
                        alertDialog.show();
                    } catch (WindowManager.BadTokenException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void showMessageDialogError(String message, Activity mActivity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        if(!mActivity.isFinishing()) {
            mActivity.runOnUiThread(new Runnable() {
                @Override

                public void run() {
                    try {
                        alertDialog = builder.create();
                        alertDialog.show();
                    } catch (WindowManager.BadTokenException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void showMessageDialogPrompt(String message, Activity mActivity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Suki");
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        if(!mActivity.isFinishing()) {
            mActivity.runOnUiThread(new Runnable() {
                @Override

                public void run() {
                    try {
                        alertDialog = builder.create();
                        alertDialog.show();
                    } catch (WindowManager.BadTokenException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
