package org.manganga.mobile.parsers.exception

import org.manganga.mobile.parsers.InternalParsersApi
import org.manganga.mobile.parsers.model.MangaSource

/**
 * Authorization is required for access to the requested content
 */
class AuthRequiredException @InternalParsersApi constructor(
	val source: MangaSource,
) : RuntimeException("Authorization required")