package by.linux.n8xx.bootmanager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(view.getContext(), parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected (AdapterView<?> parent) {
        // do nothing
    }
}
