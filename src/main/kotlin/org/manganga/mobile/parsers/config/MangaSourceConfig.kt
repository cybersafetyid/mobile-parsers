package org.manganga.mobile.parsers.config

interface MangaSourceConfig {

	operator fun <T> get(key: ConfigKey<T>): T
}