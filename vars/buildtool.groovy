def get(){
   if (fileExists('build.gradle')) {
      return 'gradle'
   }

   if (fileExists('pom.xml')) {
      return  'maven'
   }
   error "Archivo ${archivo} no existe. No se puede construir pipeline basado en ${params.buildtool}"
}
return this
