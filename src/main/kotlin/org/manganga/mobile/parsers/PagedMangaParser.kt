package org.manganga.mobile.parsers

import org.manganga.mobile.parsers.model.Manga
import org.manganga.mobile.parsers.model.MangaSource
import org.manganga.mobile.parsers.model.MangaTag
import org.manganga.mobile.parsers.model.SortOrder
import org.manganga.mobile.parsers.util.Paginator

@InternalParsersApi
abstract class PagedMangaParser(
	source: MangaSource,
	pageSize: Int,
	searchPageSize: Int = pageSize,
) : MangaParser(source) {

	protected val paginator = Paginator(pageSize)
	protected val searchPaginator = Paginator(searchPageSize)

	override suspend fun getList(offset: Int, query: String): List<Manga> {
		return getList(searchPaginator, offset, query, null, defaultSortOrder)
	}

	override suspend fun getList(offset: Int, tags: Set<MangaTag>?, sortOrder: SortOrder?): List<Manga> {
		return getList(paginator, offset, null, tags, sortOrder ?: defaultSortOrder)
	}

	@InternalParsersApi
	@Deprecated("You should use getListPage for PagedMangaParser", level = DeprecationLevel.HIDDEN)
	final override suspend fun getList(
		offset: Int,
		query: String?,
		tags: Set<MangaTag>?,
		sortOrder: SortOrder,
	): List<Manga> = throw UnsupportedOperationException("You should use getListPage for PagedMangaParser")

	abstract suspend fun getListPage(page: Int, query: String?, tags: Set<MangaTag>?, sortOrder: SortOrder): List<Manga>

	private suspend fun getList(
		paginator: Paginator,
		offset: Int,
		query: String?,
		tags: Set<MangaTag>?,
		sortOrder: SortOrder,
	): List<Manga> {
		val page = paginator.getPage(offset)
		val list = getListPage(page, query, tags, sortOrder)
		paginator.onListReceived(offset, page, list.size)
		return list
	}
}