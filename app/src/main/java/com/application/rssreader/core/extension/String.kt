
package com.application.rssreader.core.extension

fun String.Companion.empty() = ""
fun String.removeHTMLTags() =this.replace("<.*?>".toRegex(), "")

