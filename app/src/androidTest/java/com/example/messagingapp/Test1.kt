package com.example.messagingapp

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test


class Test1 {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun test() {
        rule.setContent {
            MainAppScreen()
        }

        rule.onNodeWithTag("First").printToLog("Rahul")
        rule.onNodeWithTag("First").assertHasClickAction()
        rule.onNodeWithTag("First").performClick()
        rule.onNodeWithTag("hello").printToLog("Rahul")
        rule.onNodeWithText("how are you")
        rule.onNodeWithText("sjfwo").assertExists()
        rule.onNodeWithText("Type your message").assertExists()
        rule.onNodeWithText("Type your message").assertHasClickAction()


    }
    @Test
    fun test2() {
        rule.setContent {
            MainAppScreen()
        }

        rule.onNodeWithTag("Third").printToLog("Rahul")
        rule.onNodeWithTag("Third").assertHasClickAction()
        rule.onNodeWithTag("Third").performClick()
        rule.onNodeWithText("Type your message").assertExists()
        rule.onNodeWithText("Type your message").assertHasClickAction()
    }
}

