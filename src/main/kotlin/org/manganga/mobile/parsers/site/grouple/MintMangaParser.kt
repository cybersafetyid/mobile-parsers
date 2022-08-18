package org.manganga.mobile.parsers.site.grouple

import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.model.MangaSource

@MangaSourceParser("MINTMANGA", "MintManga", "ru")
internal class MintMangaParser(
	override val context: MangaLoaderContext,
) : GroupleParser(MangaSource.MINTMANGA, "mintmangafun", 2) {

	override val configKeyDomain = ConfigKey.Domain(
		"mintmanga.live",
		arrayOf("mintmanga.live", "mintmanga.com"),
	)

	override fun getFaviconUrl(): String {
		return "https://resmm.rmr.rocks/static/apple-touch-icon-8fff291039c140493adb0c7ba81065ad.png"
	}
}