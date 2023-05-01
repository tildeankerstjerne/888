package com.example.test4;

import android.content.Context;
import java.util.Locale;

import android.content.res.Configuration;
import android.content.res.Resources;

public class LanguageManager {
    // Creating a Context class, which signifies the current state pf the application/object. It lets newly-created objects understand what has been going on
    // Context is typically used to load a resource, launch a new activity etc...
    private Context context;

    // Creating a constructor for LanguageManager
    public LanguageManager(Context ctx)
    {
        context = ctx;
    }

    public void updateResource(String code)
    {
        // Using the Locale from Android, since we are dealing with different languages (different country codes, and generally information that is locale-sensitive, and therefore require Locale to tailor the information for the user.
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}

