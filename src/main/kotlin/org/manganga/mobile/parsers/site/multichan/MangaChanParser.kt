package org.manganga.mobile.parsers.site.multichan

import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.MangaSource

@MangaSourceParser("MANGACHAN", "Манга-тян", "ru")
internal class MangaChanParser(override val context: MangaLoaderContext) : ChanParser(MangaSource.MANGACHAN) {

	override val configKeyDomain = ConfigKey.Domain("manga-chan.me", null)
}