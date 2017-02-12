Test MIN3D Framework
=================

This is a test of this framework:

[https://code.google.com/archive/p/min3d/](https://code.google.com/archive/p/min3d/)


# Improvements

This repo is awesome but it's examples has some points "strange" that I fixed :D

## min3d.sampleProject1:raw/camaro_obj

```java
IParser parser = Parser.createParser(Parser.Type.OBJ, getResources(), "min3d.sampleProject1:raw/camaro_obj", true);
```

**min3d.sampleProject1:raw/camaro_obj** is the path of obj file. When I tried to run this examples I had this problem :

```
ERROR No package identifier when getting value for resource number 
```

This is because string **min3d.sampleProject1** must be the initial package that was setting at the creation project moment.
After some hours, and a little experience in android i could see that this string **min3d.sampleProject1** is related to path for raw files insise **res** folder.

So I created a util class to get this path:

```java

public static String getGlobalResourcePackageIdentifier(Context context) {
    String packageName = context.getPackageName();
    PackageManager pm = context.getPackageManager();
    try {
        ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
        String apk = ai.dataDir;
        return apk.substring(apk.lastIndexOf(File.separator)+1);
    } catch (Throwable x) {
        Log.i(ResourceUtils.class.getCanonicalName(),"Failed when try to get global package identifier");
    }
    return null;
}


```

And worked perfectly :D

```java
IParser parser = Parser.createParser(Parser.Type.OBJ,
		getResources(), ResourceUtils.getGlobalResourcePackageIdentifier(this.getBaseContext())+":raw/camaro_obj", true);
```
