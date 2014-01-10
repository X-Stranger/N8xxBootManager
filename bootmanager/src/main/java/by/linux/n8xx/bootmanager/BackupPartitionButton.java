package by.linux.n8xx.bootmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BackupPartitionButton extends Button {

    public BackupPartitionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        final String id = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "key");

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                String dir = prefs.getString("images_path", "/mnt/extSdCard/images") + "/" + id + "/backup.img";
                boolean result = false;

                if ("boot".equals(id)) {
                    result = ExecuteAsRootBase.writeImage(prefs.getString("boot_partition", "/dev/block/mmcblk0p5"), dir);
                }
                if ("recovery".equals(id)) {
                    result = ExecuteAsRootBase.writeImage(prefs.getString("recovery_partition", "/dev/block/mmcblk0p6"), dir);
                }

                if (result) {
                    Toast.makeText(view.getContext(), "OK! The backup saved as " + dir, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "FAILED! Unable to backup " + id + " partition to " + dir, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
