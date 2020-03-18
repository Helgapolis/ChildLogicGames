package ru.bazalikova.lint_check

import com.android.ide.common.blame.SourcePosition
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Document

class XmlSpacingDetector : ResourceXmlDetector() {

    companion object{
        val ISSUE = Issue.create("XmlSpacing",
            "XML файлы не должны содержать лишних пустых линий.",
            "Наличие пустых линий в xml-файле усложняет его чтение и должны быть удалены, допускаются пустые линии перед комментариями и открытием нового тэга.",
            Category.CORRECTNESS, 8, Severity.WARNING,
            Implementation(XmlSpacingDetector::class.java, Scope.RESOURCE_FILE_SCOPE)
        )
    }

    override fun visitDocument(context: XmlContext, document: Document) {
        val contents = context.client.readFile(context.file).toString().split("\n")

        contents
            .withIndex()
            .windowed(2)
            .filter { it[0].value.isBlank() && it.getOrNull(1)?.value?.trim()?.startsWith("<!--") == false}
            .filterNot { val nextLine = it.getOrNull(1)?.value?.trim()
                nextLine?.startsWith("<") == true && nextLine.startsWith("</") == false
            }
            .map { it[0] }
            .forEach {
                val location = Location.create(context.file, SourcePosition(it.index, 0, it.value.length))
                val fix = fix()
                    .name("Удалить пустую линию")
                    .replace()
                    .range(location)
                    .all()
                    .autoFix(true, false)
                    .build()

                context.report(ISSUE, location, "Ненужная пустая линия на строке ${it.index + 1}.", fix)
            }
    }
}