SUMMARY = "snort3"
DESCRIPTION = "snort3 - a free lightweight network intrusion detection system for UNIX and Windows."
HOMEPAGE = "http://www.snort.org/"
SECTION = "net"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=78fa8ef966b48fbf9095e13cc92377c5"

PV = "3+git${SRCPV}"

DEPENDS = "flex-native bison-native hwloc libdaq libdnet libpcap libpcre libtirpc libunwind luajit zlib"

SRC_URI = "git://github.com/snort3/snort3.git;protocol=https;branch=master \
           file://0001-cmake-Check-for-HP-libunwind.patch"
SRCREV = "e1760a8dbb829bb3fcf1a340ab6cc4bb80a47ecd"

S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-scripts"

inherit cmake pkgconfig

EXTRA_OECMAKE += "\
    -DFLEX_TARGET_ARG_COMPILE_FLAGS='--noline' \
    -DBISON_TARGET_ARG_COMPILE_FLAGS='--no-lines' \
"

do_install:append() {
    sed -i "s#${RECIPE_SYSROOT}##g" ${D}${libdir}/pkgconfig/snort.pc
}

FILES:${PN} += "${libdir}/snort/daq/*.so"

FILES:${PN}-scripts = "${bindir}/appid_detector_builder.sh"

RDEPENDS:${PN}-scripts += "bash"

# mips64/ppc/ppc64/riscv64/riscv32 is not supported in this release
COMPATIBLE_HOST:mipsarchn32 = "null"
COMPATIBLE_HOST:mipsarchn64 = "null"
COMPATIBLE_HOST:powerpc = "null"
COMPATIBLE_HOST:powerpc64 = "null"
COMPATIBLE_HOST:powerpc64le = "null"
COMPATIBLE_HOST:riscv64 = "null"
COMPATIBLE_HOST:riscv32 = "null"
