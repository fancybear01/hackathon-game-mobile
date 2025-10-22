package com.coding.course_screen_impl

import com.coding.course_screen_impl.course_screen.CourseScreen
import com.coding.course_screen_impl.course_screen.CourseScreenModel
import com.coding.featue.course_screen_api.CourseScreenApi
import com.coding.mvi_koin_voyager.provideMviModel
import org.koin.dsl.module

val courseScreenModule
    get() = module {
        provideMviModel<CourseScreen> { tag, _ -> CourseScreenModel(tag, getSections = get()) }
        single<CourseScreenApi> { CourseScreenImpl() }
    }