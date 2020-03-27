package ru.bazalikova.mygradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

class PipelinePlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.tasks.create("compile") {
            description = "Run build"
            group = "pipeline"
            project.rootProject.allprojects.forEach {
                if (it.name == "app") {
                    dependsOn("${it.path}:assemble")
                }
            }

            doLast {
                println("Successfully built.")

            }
        }

        project.tasks.create("code_review") {
            description = "Run lint check"
            group = "pipeline"

            project.rootProject.allprojects.forEach {
                if (it.childProjects.isEmpty() && !it.name.contains("lint")) {
                    dependsOn("${it.path}:lint")
                }
            }
            doLast {
                println("Successfully passed code style check")
            }
        }

        project.tasks.create("tests") {
            description = "Run tests"
            group = "pipeline"

            project.rootProject.allprojects.forEach {
                if (it.childProjects.isEmpty()) {
                    dependsOn("${it.path}:test")
                }
            }

            doLast {
                println("Successfully passed all tests")
            }
        }

        project.tasks.create("check_code") {
            description = "Run check quality"
            group = "pipeline"

            dependsOn("compile")

            val compileTask = project.tasks["compile"]
            val codeReviewTask = project.tasks["code_review"]
            val testsTask = project.tasks["tests"]

            compileTask.finalizedBy(codeReviewTask)
            codeReviewTask.finalizedBy(testsTask)

            doLast{
                println("Successfully passed checking code")
            }
        }
    }
}