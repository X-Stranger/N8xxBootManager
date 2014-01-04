package by.linux.n8xx.bootmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckRootButton extends Button {

    public CheckRootButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ExecuteAsRootBase.canRunRootCommands()) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                    alert.setTitle("Oops...");
                    alert.setMessage("Sorry, the application was unable to gain root\n"
                            + "permissions and will not be able to write images.\n"
                            + "You might need to root your device or check SU settings");

                    alert.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();
                } else {
                    Toast.makeText(view.getContext(), "It's All OK", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
