package tasks

import org.gradle.api.Project

fun Project.registerTasks() {
    tasks.register("changeFolders", ChangeFoldersTask::class.java) {
        group = "custom"
        description = "Change folders for files in each module"
    }
}
