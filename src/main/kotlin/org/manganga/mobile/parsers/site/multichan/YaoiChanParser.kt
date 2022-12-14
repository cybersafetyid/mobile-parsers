package org.manganga.mobile.parsers.site.multichan

import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.Manga
import org.manganga.mobile.parsers.model.MangaChapter
import org.manganga.mobile.parsers.model.MangaSource
import org.manganga.mobile.parsers.util.*

@MangaSourceParser("YAOICHAN", "Яой-тян", "ru")
internal class YaoiChanParser(override val context: MangaLoaderContext) : ChanParser(MangaSource.YAOICHAN) {

	override val configKeyDomain = ConfigKey.Domain("yaoi-chan.me", null)

	override suspend fun getDetails(manga: Manga): Manga {
		val doc = context.httpGet(manga.url.toAbsoluteUrl(getDomain())).parseHtml()
		val root = doc.body().requireElementById("dle-content")
		return manga.copy(
			description = root.getElementById("description")?.html()?.substringBeforeLast("<div"),
			largeCoverUrl = root.getElementById("cover")?.absUrl("src"),
			chapters = root.select("table.table_cha").flatMap { table ->
				table.select("div.manga")
			}.mapNotNull { it.selectFirst("a") }.reversed().mapChapters { i, a ->
				val href = a.attrAsRelativeUrl("href")
				MangaChapter(
					id = generateUid(href),
					name = a.text().trim(),
					number = i + 1,
					url = href,
					uploadDate = 0L,
					source = source,
					scanlator = null,
					branch = null,
				)
			},
		)
	}
}