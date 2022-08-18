package org.manganga.mobile.parsers.site.madara

import org.jsoup.nodes.Element
import org.manganga.mobile.parsers.MangaLoaderContext
import org.manganga.mobile.parsers.MangaSourceParser
import org.manganga.mobile.parsers.model.Manga
import org.manganga.mobile.parsers.model.MangaChapter
import org.manganga.mobile.parsers.model.MangaSource
import org.manganga.mobile.parsers.model.MangaState
import org.manganga.mobile.parsers.util.attrAsAbsoluteUrlOrNull
import org.manganga.mobile.parsers.util.mapToSet
import org.manganga.mobile.parsers.util.selectFirstOrThrow
import java.util.*

@MangaSourceParser("MANGAS_ORIGINES", "Mangas Origines", "fr")
internal class MangasOriginesParser(context: MangaLoaderContext) :
	Madara6Parser(context, MangaSource.MANGAS_ORIGINES, "mangas-origines.fr") {

	override val tagPrefix = "catalogues-genre/"

	override fun getFaviconUrl(): String {
		return "https://${getDomain()}/wp-content/uploads/2020/11/Mangas-150x150.png"
	}

	override fun parseDetails(manga: Manga, body: Element, chapters: List<MangaChapter>): Manga {
		val root = body.selectFirstOrThrow(".site-content")
		val postContent = root.selectFirstOrThrow(".post-content")
		val tags = postContent.getElementsContainingOwnText("Genre")
			.firstOrNull()?.tableValue()
			?.getElementsByAttributeValueContaining("href", tagPrefix)
			?.mapToSet { a -> a.asMangaTag() } ?: manga.tags
		return manga.copy(
			largeCoverUrl = root.selectFirst("picture")
				?.selectFirst("img[data-src]")
				?.attrAsAbsoluteUrlOrNull("data-src"),
			description = (root.selectFirst(".detail-content")
				?: root.selectFirstOrThrow(".manga-excerpt")).html(),
			author = postContent.getElementsContainingOwnText("Auteur")
				.firstOrNull()?.tableValue()?.text()?.trim(),
			state = postContent.getElementsContainingOwnText("STATUS")
				.firstOrNull()?.tableValue()?.text()?.asMangaState(),
			tags = tags,
			isNsfw = body.hasClass("adult-content"),
			chapters = chapters,
		)
	}

	override fun String.asMangaState() = when (trim().lowercase(Locale.FRANCE)) {
		"en cours" -> MangaState.ONGOING
		"abandonné",
		"terminé",
		-> MangaState.FINISHED

		else -> null
	}
}