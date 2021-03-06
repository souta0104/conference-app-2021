package io.github.droidkaigi.feeder

import androidx.annotation.IdRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.feeder.core.theme.ConferenceAppFeederTheme
import io.github.droidkaigi.feeder.feed.FeedTabs
import io.github.droidkaigi.feeder.main.R
import io.github.droidkaigi.feeder.other.OtherTabs

enum class DrawerContents(
    val group: Group,
    @IdRes
    val imageResId: Int,
    val label: String,
    val route: String,
) {
    HOME(
        group = Group.NEWS,
        imageResId = R.drawable.ic_baseline_home_24,
        label = "HOME",
        route = "feed/${FeedTabs.Home.routePath}"
    ),
    BLOG(
        group = Group.NEWS,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "BLOG",
        route = "feed/${FeedTabs.FilteredFeed.Blog.routePath}"
    ),
    VIDEO(
        group = Group.NEWS,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "VIDEO",
        route = "feed/${FeedTabs.FilteredFeed.Video.routePath}"
    ),
    PODCAST(
        group = Group.NEWS,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "PODCAST",
        route = "feed/${FeedTabs.FilteredFeed.Podcast.routePath}"
    ),
    ABOUT_DROIDKAIGI(
        group = Group.OTHER,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "DroidKaigiとは",
        route = "other/${OtherTabs.AboutThisApp.routePath}"
    ),
    CONTRIBUTOR(
        group = Group.OTHER,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "CONTRIBUTOR",
        route = "other/${OtherTabs.Contributor.routePath}"
    ),
    STAFF(
        group = Group.OTHER,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "STAFF",
        route = "other/${OtherTabs.Staff.routePath}"
    ),
    SETTING(
        group = Group.OTHER,
        imageResId = R.drawable.ic_baseline_list_alt_24,
        label = "SETTING",
        route = "other/${OtherTabs.Settings.routePath}",
    ),
    ;

    enum class Group {
        NEWS, OTHER;
    }
}

@Composable
fun DrawerContent(onNavigate: (route: String) -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(36.dp))
        for (group in DrawerContents.Group.values()) {
            val groupContents = DrawerContents.values()
                .filter { content -> content.group == group }
            DrawerContentGroup(groupContents, onNavigate)
            Spacer(modifier = Modifier.height(2.dp))
            Divider(thickness = 4.dp)
        }
    }
}

@Composable
private fun DrawerContentGroup(
    groupContents: List<DrawerContents>,
    onNavigate: (route: String) -> Unit,
) {
    for (content in groupContents) {
        DrawerButton(
            painter = painterResource(id = content.imageResId),
            label = content.label,
            isSelected = true,
            {
                onNavigate(content.route)
            }
        )
    }
}

@Preview
@Composable
fun PreviewDrawerContent() {
    ConferenceAppFeederTheme {
        DrawerContent {
        }
    }
}
