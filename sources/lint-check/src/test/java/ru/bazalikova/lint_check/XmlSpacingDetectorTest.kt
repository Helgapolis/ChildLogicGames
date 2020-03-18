package ru.bazalikova.lint_check

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.xml
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class XmlSpacingDetectorTest {
  @Test
  fun layoutXmlFileWithoutAnyNewLines() {
    lint()
        .allowMissingSdk()
        .files(xml("res/layout/activity_menu.xml", """
          <merge xmlns:android="http://schemas.android.com/apk/res/android">
          
            <TextView
                android:layout_width="wrap_content"/>
          </merge>""").indented())
        .issues(XmlSpacingDetector.ISSUE)
        .run()
        .expectClean()
  }

  @Test fun emptyFile() {
    lint()
        .allowMissingSdk()
        .files(xml("res/layout/activity_menu.xml", ""))
        .issues(XmlSpacingDetector.ISSUE)
        .run()
        .expectClean()
  }

  @Test fun allowNewLineBeforeComment() {
    lint()
        .allowMissingSdk()
        .files(xml("res/values/themes.xml", """
          <resources>
            <style name="Theme.YourApp.Light" parent="Theme.MaterialComponents.Light"/>

            <!-- Comment. -->
          </resources>
          """).indented())
        .issues(XmlSpacingDetector.ISSUE)
        .run()
        .expectClean()
  }

  @Test fun layoutXmlFileWithNewLines() {
    lint()
        .allowMissingSdk()
        .files(xml("res/layout/activity_menu.xml",
          """<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android">

            <TextView
                xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="wrap_content"

                />

          </LinearLayout>""").indented())
        .issues(XmlSpacingDetector.ISSUE)
        .run()
        .expect("""
          |res/layout/activity_menu.xml:6: Warning: Ненужная пустая линия на строке 6. [XmlSpacing]
          |
          |^
          |res/layout/activity_menu.xml:8: Warning: Ненужная пустая линия на строке 8. [XmlSpacing]
          |
          |^
          |0 errors, 2 warnings""".trimMargin())
        .expectFixDiffs("""
          |Fix for res/layout/activity_menu.xml line 6: Удалить пустую линию:
          |@@ -1 +1
          |- <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android">
          |Fix for res/layout/activity_menu.xml line 8: Удалить пустую линию:
          |@@ -1 +1
          |- <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android">
          |""".trimMargin())
  }
}