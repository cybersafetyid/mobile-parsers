package org.manganga.mobile.parsers.exception

import okio.IOException

class CloudFlareProtectedException(
	val url: String,
) : IOException("Protected by CloudFlare: $url")