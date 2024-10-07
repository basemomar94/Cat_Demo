package com.bassem.catdemo.ui.compose.shared

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.data.models.tabs

@Preview(showBackground = true)
@Composable
fun BottomBarComposeScreen() {
    BottomBarCompose(selectedTab = 1, onTabSelected = {})
}

@Composable
fun BottomBarCompose(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                label = { Text(tab.title) },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}