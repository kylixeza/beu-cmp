# Ignore missing CameraX extension classes
-dontwarn androidx.camera.extensions.impl.**
-dontwarn androidx.camera.extensions.internal.**
-dontwarn androidx.camera.extensions.internal.sessionprocessor.**
-dontwarn androidx.camera.extensions.impl.advanced.**

# Ignore missing SLF4J class
-dontwarn org.slf4j.impl.StaticLoggerBinder

# Keep interfaces that might be used by reflection
-keepnames interface androidx.camera.extensions.impl.** { *; }
-keepnames interface androidx.camera.extensions.impl.advanced.** { *; }

# Keep some classes used by CameraX extensions if needed
-keepnames class androidx.camera.extensions.internal.** { *; }
-keepnames class androidx.camera.extensions.internal.sessionprocessor.** { *; }
