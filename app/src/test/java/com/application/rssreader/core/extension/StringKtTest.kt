package com.application.rssreader.core.extension

import com.google.common.truth.Truth
import org.junit.Test

class StringKtTest {

    @Test
    fun removeHTMLTagsWithHtmlTag() {
        Truth.assertThat("<?xml version=\"1.0\" encoding=\"utf-8\"?>Sultan".removeHTMLTags().trim())
            .isEqualTo("Sultan")
    }

    @Test
    fun removeHTMLTagsWithOutHtmlTag() {

        Truth.assertThat("Sultan".removeHTMLTags().trim())
            .isEqualTo("Sultan")
    }
}