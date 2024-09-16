package beukmm.util

import io.kamel.core.config.KamelConfig
import io.kamel.core.config.fileFetcher
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.stringMapper
import io.kamel.core.config.uriMapper
import io.kamel.core.config.urlMapper
import io.kamel.image.config.imageBitmapDecoder
import io.kamel.image.config.imageVectorDecoder
import io.kamel.image.config.svgDecoder

val customKamelConfig = KamelConfig {
    imageBitmapCacheSize = 1000
    imageVectorCacheSize = 1000

    imageBitmapDecoder()
    imageVectorDecoder()
    svgDecoder()
    stringMapper()
    urlMapper()
    uriMapper()
    fileFetcher()

    httpFetcher {
        httpCache(24 * 1024 * 1024)
    }
}
