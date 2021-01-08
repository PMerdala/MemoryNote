package pl.merdala.memorynote.framework.di

import dagger.Component
import pl.merdala.memorynote.framework.AbstractViewModel

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(abstractViewModel: AbstractViewModel)
}
