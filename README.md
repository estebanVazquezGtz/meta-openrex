# Imagenes Personalizadas con YOCTO para Open Rex

A continuación se describe como preparar un sistema operativo Linux para ser capaz de compilar imagenes personalizadas con YOCTO, este repositorio contiene los archivos de meta datos para la generacion de un sistema de arranque para la tarjeta de desarrollo "Open Rex: imx6qopenrex". 

En caso de interesarse por saber mas detalles acerca de YOCTO, como usarlo y crear contenido personalizado así como de la tarjeta "Open Rex" por favor visite: http://www.imx6rex.com/open-rex/software/

### 1) Instalar la herramienta 'repo'.
    mkdir ~/bin
    curl http://commondatastorage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
    chmod a+x ~/bin/repo
 
### 2) Obtener Board Support Package (BSP) desde el repositorio de  Freescale y guardarlo en el directorio de trabajo 'fsl-community-bsp'.
    cd
    mkdir fsl-community-bsp
    cd fsl-community-bsp
    PATH=${PATH}:~/bin
    repo init -u https://github.com/Freescale/fsl-community-bsp-platform -b jethro
 
### 3) Crear un directorio para archivos 'mainfest'.
    cd ~/fsl-community-bsp/
    mkdir -pv .repo/local_manifests/
 
### 4) Crear un nuevo archivo: 'imx6openrex.xml' dentro del directorio: 'local_manifests/' con el siguiente contenido.
      
      <?xml version="1.0" encoding="UTF-8"?>
      
      <manifest>
      
      <remote fetch="git://github.com/estebanVazquezGtz" name="estebanVazquezGtz"/>
      <project remote="estebanVazquezGtz" revision="jethro" name="meta-openrex" path="sources/meta-openrex">
        <copyfile src="openrex-setup.sh" dest="openrex-setup.sh"/>
      </project>
    </manifest>
 
### 5) Sincronizar los repositorios.
    repo sync
 
### 6) Añadir la capa de meta datos para "Open Rex".
    source openrex-setup.sh

### 7) Colocarse en el directorio de trabajo.  
    cd ~/fsl-community-bsp/

### 8) Configurar el entorno y generar los archivos de imagen las tarjetas que soporta esta version son imx6qopenrex (quad core) y imx6sopenrex (solo core).

    MACHINE=ID_TARJETA source setup-environment build-openrex
    MACHINE=ID_TARJETA bitbake core-image-base

### 9) Cargar los archivos de salida a una tarjeta SD.

Los nombres de los directorios y los archivos de salida dependen de lo que se haya generado, a continuación se muestran los pasos para cargar los archivos de salida de la ejecución del comando 'bitbake core-image-base' en una tarjeta SD externa.

    umount /dev/sdb?
    gunzip -c ~/fsl-community-bsp/build-openrex/tmp/deploy/images/imx6q-openrex/core-image-base-imx6q-openrex.sdcard.gz > ~/fsl-community-bsp/build-openrex/tmp/deploy/images/imx6q-openrex/core-image-base-imx6q-openrex.sdcard
    sudo dd if=~/fsl-community-bsp/build-openrex/tmp/deploy/images/imx6q-openrex/core-image-base-imx6q-openrex.sdcard of=/dev/sdb
    umount /dev/sdb?
     
### 10) Probar el sistema de arranque u-Boot.
Con la intención de corroborar que los archivos generados funcionen adecuadamente se debera insertar la tarjeta SD con los datos cargados anteriormente en la tarjeta "Open Rex" y conectar este dispositivo a una computadora vía USB la cual debe ejecutar un programa capaz de leer y escribir datos en el puerto serial, una vez hecho esto reiniciar la tarjeta "Open Rex" para interrumpir la secuencia de conteo de u-Boot presionar la barra espaciadora y ejecutar el siguiente comando:

    mw.l 0x020d8040 0x2840; mw.l 0x020d8044 0x10000000; reset
 
 Ahora "Open Rex" iniciara con el gestor de arranque compilado en los pasos anteriores.
