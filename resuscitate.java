    /* helper method to allow others to start/update PttAppWidget */
    public static void resuscitate(Context context) {
        /* with reference to the following stackoverflow answer:
           https://stackoverflow.com/questions/3455123/programmatically-update-widget-from-activity-service-receiver
         */
        Intent intent = new Intent(context, PttAppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds((new ComponentName(context, PttAppWidget.class)));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }