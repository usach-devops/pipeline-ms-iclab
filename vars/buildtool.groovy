def get(){
   if (fileExists('build.gradle')) {
      return 'gradle'
   }

   if (fileExists('pom.xml')) {
      return  'maven'
   }
   error "Archivo de compilación no existe. No se puede construir pipeline."
}
return this
