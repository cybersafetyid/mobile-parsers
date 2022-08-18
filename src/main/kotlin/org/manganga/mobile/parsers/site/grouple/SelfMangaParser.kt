package org.manganga.mobile.parsers.site.grouple

import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.MangaSource

@MangaSourceParser("SELFMANGA", "SelfManga", "ru")
internal class SelfMangaParser(
	override val context: MangaLoaderContext,
) : GroupleParser(MangaSource.SELFMANGA, "selfmangafun", 3) {

	override val configKeyDomain = ConfigKey.Domain("selfmanga.live", null)

	override fun getFaviconUrl(): String {
		return "https://ressm.rmr.rocks/static/apple-touch-icon-a769ea533d811b73ac3eedde658bb1d3.png"
	}
}