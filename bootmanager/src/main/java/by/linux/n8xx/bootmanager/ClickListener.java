package by.linux.n8xx.bootmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;

public class ClickListener implements AdapterView.OnItemClickListener {

    private String id;

    public ClickListener(String id) {
        super();
        this.id = id;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        final String image = adapterView.getAdapter().getItem(position).toString();

        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Are you sure?");
        alert.setMessage("You selected " + image + " to be written to " + id + " partition");

        alert.setPositiveButton("GO!", new DialogClickListener(view, image));

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    private class DialogClickListener implements DialogInterface.OnClickListener {
        private View view;
        private String image;

        public DialogClickListener(View view, String image) {
            this.view = view;
            this.image = image;
        }

        @Override
        public void onClick(DialogInterface dialog, int whichButton) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            String dir = prefs.getString("images_path", "/mnt/extSdCard");

            if ("boot".equals(id)) {
                if (ExecuteAsRootBase.writeImage(dir + "/" + image,
                        prefs.getString("boot_partition", "/dev/block/mmcblk0p5"))) {
                    ExecuteAsRootBase.reboot();
                }
            }
            if ("recovery".equals(id)) {
                if (ExecuteAsRootBase.writeImage(dir + "/" + image,
                        prefs.getString("recovery_partition", "/dev/block/mmcblk0p6"))) {
                    ExecuteAsRootBase.rebootRecovery();
                }
            }

            dialog.dismiss();
        }
    }
}
