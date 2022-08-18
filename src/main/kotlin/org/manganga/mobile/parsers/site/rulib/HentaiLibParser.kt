package org.manganga.mobile.parsers.site.rulib

import org.jsoup.nodes.Document
import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.MangaSource

@MangaSourceParser("HENTAILIB", "HentaiLib", "ru")
internal class HentaiLibParser(context: MangaLoaderContext) : MangaLibParser(context, MangaSource.HENTAILIB) {

	override val configKeyDomain = ConfigKey.Domain("hentailib.me", null)
	override fun isNsfw(doc: Document) = true
}