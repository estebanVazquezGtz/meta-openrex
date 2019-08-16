# Copyright (C) 2015, 2016 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "FSL Community BSP i.MX6 Linux kernel with backported features and fixes"
DESCRIPTION = "Linux kernel based on Freescale 3.14.52-1.1.0 GA release, used by FSL Community BSP in order to \
provide support for i.MX6 based platforms and include official Linux kernel stable updates, backported \
features and fixes coming from the vendors, kernel community or FSL Community itself."

# Copyright (C) 2012-2015 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "jetrho"

SRC_URI = "git://github.com/estebanVazquezGtz/openrex-linux-v3.14.git;branch=${SRCBRANCH} \
           file://defconfig"

LOCALVERSION = "-yoctoEsteban"

SRCREV = "b7477809745c25eca99a125054d110e4e85c9590"

COMPATIBLE_MACHINE = "(mx6|mx7|imx6qopenrex|imx6sopenrex)"
