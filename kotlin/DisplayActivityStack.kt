fun printActivityStack(context:Context) {
    val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
    val tasks = activityManager.appTasks
    for(task in tasks) {
        val activityName = task.taskInfo?.className
        Log.d("ActivityTask", "Activity Name: $activityName")
    }
}