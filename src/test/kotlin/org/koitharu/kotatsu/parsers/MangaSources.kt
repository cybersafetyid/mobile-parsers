package org.manganga.mobile.parsers

import org.junit.jupiter.params.provider.EnumSource
import org.manganga.mobile.parsers.model.MangaSource

@EnumSource(MangaSource::class, names = ["LOCAL", "DUMMY"], mode = EnumSource.Mode.EXCLUDE)
internal annotation class MangaSources