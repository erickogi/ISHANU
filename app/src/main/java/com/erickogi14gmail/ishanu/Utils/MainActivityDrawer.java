package com.erickogi14gmail.ishanu.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.Toolbar;

import com.erickogi14gmail.ishanu.Interfaces.DrawerItemListener;
import com.erickogi14gmail.ishanu.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialize.holder.ColorHolder;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

/**
 * Created by Eric on 11/20/2017.
 */

public class MainActivityDrawer {
    private static Drawer result;
    private Activity activity;


    private static Bitmap getBitmap(Activity activity, String img) {


        Bitmap thumnail = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_person_black_24dp);
        try {
            File filepath = activity.getFileStreamPath(img);
            FileInputStream fi = new FileInputStream(filepath);
            thumnail = BitmapFactory.decodeStream(fi);

        } catch (Exception m) {
            m.printStackTrace();
        }
        return thumnail;

    }

    public static void getDrawer(final Activity activity, Toolbar toolbar, int id, HashMap<String, String> details, String img, DrawerItemListener itemListener) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        Bitmap image = getBitmap(activity, img);
        PrimaryDrawerItem drawerEmptyItem = new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);


        PrimaryDrawerItem sell = new PrimaryDrawerItem().withIdentifier(1)
                .withName("Sell").withIcon(R.drawable.sell);
        PrimaryDrawerItem reports = new PrimaryDrawerItem().withIdentifier(2)
                .withName("Reports").withIcon(R.drawable.report);
        PrimaryDrawerItem notifications = new PrimaryDrawerItem().withIdentifier(3)
                .withName("Notifications").withIcon(R.drawable.notifications)
                 .withBadge("3")
                ;


        PrimaryDrawerItem account = new PrimaryDrawerItem().withIdentifier(4)
                .withName("My Account").withIcon(R.drawable.useraccount);
        PrimaryDrawerItem settingd = new PrimaryDrawerItem().withIdentifier(5)
                .withIcon(R.drawable.setting)
                .withName("Settings");



        SecondaryDrawerItem logout = new SecondaryDrawerItem().withIdentifier(6)
                .withName("Log Out");
        SecondaryDrawerItem help = new SecondaryDrawerItem().withIdentifier(7)
                .withName("Support");
        SecondaryDrawerItem about = new SecondaryDrawerItem().withIdentifier(8)
                .withName("About");

//

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withOnProfileClickDrawerCloseDelay(2)
                .withTextColorRes(R.color.primary_dark)

                //.withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(details.get("name")).withEmail(details.get("email"))
                                .withTextColor(ColorHolder.fromColorRes(R.color.colorPrimaryDark).getColorInt())
                                .withTextColor(activity.getResources().getColor(R.color.primary_dark))
                                .withSelectedTextColorRes(R.color.colorPrimaryDark)
                                //.withSelectedTextColorRes(R.color.colorPrimaryDark)
                                .withIcon(R.drawable.p)


                                .withDisabledTextColor(activity.getResources().getColor(R.color.colorPrimaryDark))


                )
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                .build();


        result = new DrawerBuilder()

                .withActivity(activity)
               // .withFooter(R.layout.footer)

                //.withGenerateMiniDrawer(true)
                .withFooterDivider(false)


                .withToolbar(toolbar)

                .withAccountHeader(headerResult)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        // favorites,
                        sell, reports, notifications,  new DividerDrawerItem(), account,
                         settingd,
                        new DividerDrawerItem(),
                        logout,
                        help, about


                )
                .withFooterClickable(true)
                //.withStickyFooter(R.layout.footer)


                .withOnDrawerItemClickListener((view, position, drawerItem) -> {

                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    itemListener.sellClicked();
                                    result.closeDrawer();
                                    break;
                                case 2:
                                    itemListener.reportsClicked();
                                    result.closeDrawer();
                                    break;
                                case 3:
                                    itemListener.notificationsClicked();
                                    result.closeDrawer();
                                    break;
                                case 4:
                                    itemListener.accountClicked();
                                    result.closeDrawer();
                                    break;
                                case 5:
                                    itemListener.settingsClicked();
                                    result.closeDrawer();
                                    break;
                                case 6:
                                    itemListener.logoutClicked();
                                    result.closeDrawer();
                                    break;
                                case 7:
                                    itemListener.helpClicked();
                                    result.closeDrawer();
                                    break;
                                case 8:
                                    itemListener.aboutClicked();
                                    result.closeDrawer();
                                    break;


                            }
                            return true;
                        }
                )

                .build();
    }

}
