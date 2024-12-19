package org.jj.core.presentation

import cmp_notes_app.composeapp.generated.resources.Res
import cmp_notes_app.composeapp.generated.resources.error_disk_full
import cmp_notes_app.composeapp.generated.resources.error_unknown
import org.jj.core.domain.DataError

fun DataError.toUiText(): UiText{
    val stringRes = when(this){
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
    }
    return UiText.StringResourceId(stringRes)
}