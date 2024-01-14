package com.brayandev.listtaskapp.domain.model


sealed interface Error {
    object Connectivity : Error
    class Unknown(val message: String) : Error
}