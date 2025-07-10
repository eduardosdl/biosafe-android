package com.eduardosdl.biosafe.navigation.tabs

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.eduardosdl.biosafe.R
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class TabsItemsList(
    val route: String,
    @StringRes val labelRes: Int,
    @Contextual val icon: ImageVector,
) {
    @Serializable
    data object Home : TabsItemsList(
        route = "home",
        labelRes = R.string.label_home_screen_app_bar,
        icon = Icons.Default.Home
    )

    @Serializable
    data object Users : TabsItemsList(
        route = "users",
        labelRes = R.string.label_users_screen_app_bar,
        icon = Icons.Default.Person
    )

    companion object {
        val ALL: List<TabsItemsList> = listOf(Home, Users)
        fun fromRoute(route: String?): TabsItemsList? = ALL.find { it.route == route }
    }
}