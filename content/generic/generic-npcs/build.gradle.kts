plugins {
    id("base-conventions")
}

dependencies {
    implementation(projects.api.pluginCommons)
    implementation(projects.api.drops)
    implementation(projects.content.interfaces.bank)
}
