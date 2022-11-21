
package com.application.rssreader.core.extension

fun String.Companion.empty() = ""
fun String.RemoveHTMLTags() =this.replace("\\<.*?\\>".toRegex(), "")

