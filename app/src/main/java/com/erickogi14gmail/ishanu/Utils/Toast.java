package com.erickogi14gmail.ishanu.Utils;

import android.content.Context;

import com.erickogi14gmail.ishanu.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * Created by Eric on 11/23/2017.
 */

public class Toast {
    public static void toast(String msg, Context context, int icon) {
        StyleableToast st = new StyleableToast(context, msg, android.widget.Toast.LENGTH_SHORT);
        st.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        st.setTextColor(context.getResources().getColor(R.color.white));
        try {
            st.setIcon(icon);


            st.setMaxAlpha();
            st.show();
        } catch (Exception m) {
            android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT).show();
        }
    }
}
