# Copyright (C) 2012-2015 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "jetrho"

SRC_URI = "git://github.com/estebanVazquezGtz/openrex-linux-v3.14.git;branch=${SRCBRANCH} \
           file://defconfig"

LOCALVERSION = "-fslc"
