APP_STL := gnustl_static
APP_CPPFLAGS := -frtti -fexceptions
APP_ABI := armeabi armeabi-v7a x86 mips       #这句是设置生成的cpu指令类型，提示，目前绝大部分安卓手机支持armeabi，libs下太多类型，编译进去 apk 包会过大
APP_PLATFORM := android-14    #这句是设置最低安卓平台，可以不弄