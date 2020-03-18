package ru.bazalikova.lint_check

import com.android.tools.lint.detector.api.CURRENT_API

class CustomIssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val api = CURRENT_API

    override val issues get() = listOf(
        XmlSpacingDetector.ISSUE
    )
}