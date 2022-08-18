package org.manganga.mobile.parsers.site.rulib

import org.jsoup.nodes.Document
import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.MangaSource

@MangaSourceParser("YAOILIB", "YaoiLib", "ru")
internal class YaoiLibParser(context: MangaLoaderContext) : MangaLibParser(context, MangaSource.YAOILIB) {

	override val configKeyDomain = ConfigKey.Domain("yaoilib.me", null)
	override fun isNsfw(doc: Document) = true
}