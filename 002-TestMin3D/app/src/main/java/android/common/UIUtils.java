package android.common;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by stark on 12/02/2017.
 */

public class UIUtils {

    public static void showSimpleDialog(Activity activity, String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(activity.getBaseContext()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static void showSimpleErrorDialog(Activity activity, String title, Exception ex ){
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        String message =  errors.toString();

        showSimpleDialog(activity,title,message);
    }

}
