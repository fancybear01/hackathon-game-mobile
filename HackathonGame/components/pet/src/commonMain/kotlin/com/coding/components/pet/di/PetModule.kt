package com.coding.components.pet.di

import com.coding.components.pet.data.api.PetApi
import com.coding.components.pet.data.api.PetApiImpl
import com.coding.components.pet.data.repository.PetRepositoryImpl
import com.coding.components.pet.domain.repository.PetRepository
import com.coding.components.pet.domain.usecase.GetPetUseCase
import com.coding.components.pet.domain.usecase.GetPetUseCaseImpl
import com.coding.components.pet.domain.usecase.SetNameUseCase
import com.coding.components.pet.domain.usecase.SetNameUseCaseImpl
import org.koin.dsl.module

val petModule
    get() = module {
        single<PetApi> { PetApiImpl(get()) }
        single<PetRepository> { PetRepositoryImpl(get()) }
        single<GetPetUseCase> { GetPetUseCaseImpl(get()) }
        single<SetNameUseCase> { SetNameUseCaseImpl(get()) }
    }