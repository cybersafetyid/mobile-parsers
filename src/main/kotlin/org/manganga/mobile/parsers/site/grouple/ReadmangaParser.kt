package org.manganga.mobile.parsers.site.grouple

import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.MangaSource

@MangaSourceParser("READMANGA_RU", "ReadManga", "ru")
internal class ReadmangaParser(
	override val context: MangaLoaderContext,
) : GroupleParser(MangaSource.READMANGA_RU, "readmangafun", 1) {

	override val configKeyDomain = ConfigKey.Domain(
		"readmanga.live",
		arrayOf("readmanga.io", "readmanga.live", "readmanga.me"),
	)

	override fun getFaviconUrl(): String {
		return "https://resrm.rmr.rocks/static/apple-touch-icon-3162037c9df9f28dca0f9a4092cb0f65.png"
	}
}