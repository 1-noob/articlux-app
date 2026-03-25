package com.mecharium.articlux_1.ui.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import retrofit2.http.Url


fun copyToClipboard(context: Context, url: String) {

    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("Copied URL", url)

    clipboard.setPrimaryClip(clip)
}


fun copyPromptToClipboard(context: Context) {

    val prompt = """
        **Prompt for NotebookLM**

        Read the full content from the provided URL carefully and generate a **concise, high-quality summary specifically tailored for UPSC Civil Services Examination preparation**.

        Follow these rules strictly:

        1. **Output Format**

           * Present the summary in **clear bullet points only**.
           * Keep points **short, precise, and information-dense**.
           * Avoid long paragraphs.

        2. **Topic Separation**

           * If the article contains **multiple topics or subtopics**, separate them clearly.
           * Each topic must have a **clear, bold title heading** before its bullet points.

        3. **UPSC Relevance**
           Focus on extracting information useful for:

           * **Prelims (facts, data, definitions, locations, institutions, reports, schemes, treaties, numbers, dates).**
           * **Mains (concepts, causes, impacts, challenges, government initiatives, solutions, global context).**

        4. **Do Not Miss Important Information**
           Ensure the summary captures:

           * Key **facts and figures**
           * Important **concepts and definitions**
           * Relevant **laws, policies, committees, or reports**
           * Important **organizations or institutions**
           * **Geographical references**
           * **International agreements or conventions**
           * **Scientific or technological concepts** if present

        5. **Highlight Important Terms**

           * Bold **important keywords, institutions, schemes, and acts**.

        6. **Avoid**

           1. Opinions or speculation
           2. Unnecessary data
           3. Redundant information
           4. Repetition

        7) **Goal**
           The final summary should be **compact, exam-ready revision notes** that a UPSC aspirant can revise quickly

    """.trimIndent()


    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("Copied prompt", prompt)

    clipboard.setPrimaryClip(clip)
}