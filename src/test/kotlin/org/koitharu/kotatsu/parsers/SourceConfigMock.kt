package org.manganga.mobile.parsers

import org.manganga.mobile.parsers.config.ConfigKey
import org.manganga.mobile.parsers.config.MangaSourceConfig

internal class SourceConfigMock : MangaSourceConfig {

	override fun <T> get(key: ConfigKey<T>): T = key.defaultValue
}