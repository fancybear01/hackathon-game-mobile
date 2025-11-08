package com.coding.components.quiz.di

import com.coding.components.quiz.data.api.StudyApi
import com.coding.components.quiz.data.api.StudyApiImpl
import com.coding.components.quiz.data.repository.StudyRepositoryImpl
import com.coding.components.quiz.domain.repostory.StudyRepository
import com.coding.components.quiz.domain.usecase.GetQuestionsUseCase
import com.coding.components.quiz.domain.usecase.GetQuestionsUseCaseImpl
import com.coding.components.quiz.domain.usecase.GetSectionsUseCase
import com.coding.components.quiz.domain.usecase.GetSectionsUseCaseImpl
import com.coding.components.quiz.domain.usecase.GetTheoryUseCase
import com.coding.components.quiz.domain.usecase.GetTheoryUseCaseImpl
import org.koin.dsl.module

val quizModule
    get() = module {
        single<StudyApi> { StudyApiImpl(get()) }
        single<StudyRepository> { StudyRepositoryImpl(get()) }
        single<GetQuestionsUseCase> { GetQuestionsUseCaseImpl(get()) }
        single<GetSectionsUseCase> { GetSectionsUseCaseImpl(get()) }
        single<GetTheoryUseCase> { GetTheoryUseCaseImpl(get()) }
    }