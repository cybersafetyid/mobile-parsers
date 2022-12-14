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

@MangaSourceParser("PRISMA_SCANS", "Prisma Scans", "pt")
internal class PrismaScansParser(context: MangaLoaderContext) :
	Madara6Parser(context, MangaSource.PRISMA_SCANS, "prismascans.net") {

	override val tagPrefix = "manga-genre/"

	override fun getFaviconUrl(): String {
		return "https://${getDomain()}/wp-content/uploads/2022/07/cropped-branca-1-192x192.png"
	}

	override fun parseDetails(manga: Manga, body: Element, chapters: List<MangaChapter>): Manga {
		val root = body.selectFirstOrThrow(".site-content")
		val postContent = root.selectFirstOrThrow(".post-content")
		val tags = postContent.getElementsContainingOwnText("Gênero")
			.firstOrNull()?.tableValue()
			?.getElementsByAttributeValueContaining("href", tagPrefix)
			?.mapToSet { a -> a.asMangaTag() } ?: manga.tags
		return manga.copy(
			largeCoverUrl = root.selectFirst("picture")
				?.selectFirst("img[data-src]")
				?.attrAsAbsoluteUrlOrNull("data-src"),
			description = root.selectFirstOrThrow(".description-summary").firstElementChild()?.html(),
			author = postContent.getElementsContainingOwnText("Artista")
				.firstOrNull()?.tableValue()?.text()?.trim(),
			altTitle = postContent.getElementsContainingOwnText("Título Alternativo")
				.firstOrNull()?.tableValue()?.text()?.trim(),
			state = postContent.getElementsContainingOwnText("Status")
				.firstOrNull()?.tableValue()?.text()?.asMangaState(),
			tags = tags,
			isNsfw = body.hasClass("adult-content"),
			chapters = chapters,
		)
	}

	override fun String.asMangaState() = when (trim().lowercase(sourceLocale ?: Locale.ROOT)) {
		"em lançamento" -> MangaState.ONGOING
		"completo",
		"cancelado",
		-> MangaState.FINISHED

		else -> null
	}
}