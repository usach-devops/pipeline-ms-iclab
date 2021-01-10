package com.usachpipeline

BuildToolType get(){
   if (fileExists('build.gradle')) {
      return BuildToolType.Gradle
   }

   if (fileExists('pom.xml')) {
      return  BuildToolType.Maven
   }
   error "Archivo ${archivo} no existe. No se puede construir pipeline basado en ${params.buildtool}"
}
