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
        Read the complete content from the provided URL carefully and convert it into **detailed, information-dense UPSC preparation notes** intended primarily for **essay writing and GS answers**. The objective is to extract **as much useful knowledge, arguments, examples, context, and analytical material as possible**, so that after reading the notes a UPSC aspirant should **not need to consult the original article again**. Summarize the article using **clear bullet points with short but content-rich statements**, avoiding long narrative paragraphs while still ensuring that the notes are **substantive and comprehensive rather than brief summaries**. Do **not omit any important ideas, arguments, statistics, expert opinions, historical references, case studies, examples, policy discussions, or conceptual explanations** present in the article.
        If the URL contains **multiple topics or themes**, clearly separate them with **short titles for each topic**, and then list the detailed points under that section. For every topic, extract material useful for essays such as **background context, core arguments, causes, structural factors, impacts (social, economic, political, environmental, technological, ethical), debates or criticisms, policy implications, and possible solutions or way forward**. Include relevant **government policies, laws, constitutional provisions, schemes, committees, reports, institutions, international organizations, treaties, global comparisons, and best practices** whenever mentioned. Preserve **important data, statistics, dates, rankings, survey findings, and factual references** because they are valuable for strengthening UPSC answers.
        Emphasize **key terms, acts, institutions, schemes, and major concepts in bold** so they are easy to revise later. Avoid filler language, repetition, and unnecessary storytelling, but ensure the notes remain **analytically rich and sufficiently detailed to supply arguments, examples, multidimensional perspectives, and evidence for essay writing**. The final output should function as **comprehensive UPSC-ready notes that provide abundant material, viewpoints, and supporting information for essays and GS answers**, while still remaining organized, readable, and structured.
    """.trimIndent()


    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("Copied prompt", prompt)

    clipboard.setPrimaryClip(clip)
}