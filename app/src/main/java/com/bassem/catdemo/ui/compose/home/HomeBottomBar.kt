package com.bassem.catdemo.ui.compose.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bassem.catdemo.data.models.tabs

@Composable
fun HomeBottomBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
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