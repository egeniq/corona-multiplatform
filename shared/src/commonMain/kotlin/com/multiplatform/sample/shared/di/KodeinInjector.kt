package com.multiplatform.sample.shared.di

import com.multiplatform.sample.shared.datasource.HopkinsAPI
import com.multiplatform.sample.shared.repo.CoronaRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = Kodein {

    bind<CoronaRepository>() with provider { CoronaRepository() }
    bind<HopkinsAPI>() with provider { HopkinsAPI() }

}
