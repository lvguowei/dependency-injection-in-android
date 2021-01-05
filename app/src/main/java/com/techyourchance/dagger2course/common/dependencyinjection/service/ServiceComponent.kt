package com.techyourchance.dagger2course.common.dependencyinjection.service

import dagger.Subcomponent

@ServiceScope
@Subcomponent(modules = [ServiceModule::class])
interface ServiceComponent {

}
