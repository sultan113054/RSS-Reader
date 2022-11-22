package com.application.rssreader.data

import com.application.rssreader.data.model.RSSFeedModel

object Fakes {

    fun getRSSFeeds(): List<RSSFeedModel> {
        return listOf(
            RSSFeedModel(
                "Richarlison brani Neymarov potez: To su glupi ljudi! Osvojit ćemo naslov, htjeli oni to ili ne",
                "Neymar je na Instagramu objavio fotografiju sebe u zrakoplovu, u kratkim hlačama s izvezenim grbom Brazila s pet zvjezdica, kojem je dodao i šestu zvjezdicu. Njemački Bild opisao je ovaj događaj kao \"provalu arogancije",
                "ttps://img.24sata.hr/bpTkAHQO12uMskNgxZXexHdth7I=/800x450/smart/media/images/2022-47/images-2022-47-pxl-reu-211122-97005094-mxn6zwm.jpg",
                "ttps://www.24sata.hr/sport/zubac-zasvirao-i-jazz-ostvario-vec-sedmi-double-double-874951",
            )
        )

    }
}