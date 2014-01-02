package by.linux.n8xx.bootmanager;

import android.content.Context;
import android.util.AttributeSet;

public class EditTextPreference extends android.preference.EditTextPreference {

    public EditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        setSummary(getSummary());
    }

    @Override
    public CharSequence getSummary() {
        if ("".equals(this.getText()) || (this.getText() == null)) {
            return super.getSummary();
        } else {
            return this.getText();
        }
    }
}