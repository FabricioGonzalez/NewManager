package com.kaskin.manager.presentation.user.login.states

import java.io.Serializable

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String,
    val setor: String
    //... other data fields that may be accessible to the UI
) : Serializable