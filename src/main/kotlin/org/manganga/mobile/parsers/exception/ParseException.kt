package org.manganga.mobile.parsers.exception

import org.manganga.mobile.parsers.InternalParsersApi

class ParseException @InternalParsersApi @JvmOverloads constructor(
	val shortMessage: String?,
	val url: String,
	cause: Throwable? = null,
) : RuntimeException("$shortMessage at $url", cause)