package by.linux.n8xx.bootmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The content id this fragment is presenting.
     */
    private String id;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            ItemList.Item item = ItemList.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            id = item != null ? item.id : null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        String[] images = new String[] {};

        // Show the content
        if (id != null) {
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
            File dir = new File(prefs.getString("images_path", "/mnt/extSdCard"));
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    String name = file.getName().toLowerCase();
                    return name.startsWith(id) && name.endsWith(".img");
                }
            };
            File[] files = dir.listFiles(filter);
            if (files != null) {
                images = new String[files.length];
                for (int i = 0; i < images.length; i++) { images[i] = files[i].getName(); }
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(
                rootView.getContext(), android.R.layout.simple_list_item_1, images);
        ((ListView) rootView.findViewById(R.id.item_detail))
                .setEmptyView(rootView.findViewById(R.id.item_detail_empty));
        ((ListView) rootView.findViewById(R.id.item_detail)).setAdapter(adapter);
// TODO: fix empty view when no images found
// TODO: check what happens when items are there
// TODO: how to get root permissions and run dd (check if command is available first?)
        return rootView;
    }
}
