package org.manganga.mobile.parsers.model

import org.manganga.mobile.parsers.InternalParsersApi

@InternalParsersApi
class WordSet(private vararg val words: String) {

	fun anyWordIn(dateString: String): Boolean = words.any {
		dateString.contains(it, ignoreCase = true)
	}
}