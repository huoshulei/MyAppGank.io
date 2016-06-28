package com.example.huo.myappgankio.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huo.myappgankio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huo on 28/06/16.
 */

public class MenuIetm {
    String menuTitle;
    int menuIcon;

    public MenuIetm(String menuTitle, int menuIcon) {
        this.menuTitle = menuTitle;
        this.menuIcon = menuIcon;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }
}
