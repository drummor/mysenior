#include "jni.h"
#include "com_weshape3d_myapplication_MyJNI.h"
#include "stdlib.h"

jint  Java_com_weshape3d_myapplication_MyJNI_add
  (JNIEnv *env, jobject job, jint ji, jint jj){
    jint value = ji +jj;
    return value;
  }
