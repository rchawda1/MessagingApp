package com.example.messagingapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test

class Test1 {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun test1() {
        rule.setContent {
            MainAppScreen()
        }

        rule.onNodeWithTag("First").printToLog("Rahul")
        rule.onNodeWithTag("First").performClick()
        rule.onNodeWithTag("hello").printToLog("Rahul")

    }
}