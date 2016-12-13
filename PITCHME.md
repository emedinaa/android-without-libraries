#HSLIDE
<!-- .slide: data-autoslide="10000" -->

**Eduardo José Medina Alfaro**
   
###### @eduardomedina
   
###### https://github.com/emedinaa
 
#HSLIDE
[https://github.com/googlesamples/android-architecture](https://github.com/googlesamples/android-architecture)
<img src="https://raw.githubusercontent.com/wiki/googlesamples/android-architecture/images/aab-logo.png">

#VSLIDE
<img src="https://raw.githubusercontent.com/wiki/googlesamples/android-architecture/images/google_android_architecture">

#VSLIDE

```Java
dependencies {
    // App's dependencies, including test
    compile "com.android.support:appcompat-v7:$rootProject.supportLibraryVersion"
    compile "com.android.support:cardview-v7:$rootProject.supportLibraryVersion"
    compile "com.android.support:design:$rootProject.supportLibraryVersion"
    compile "com.android.support:recyclerview-v7:$rootProject.supportLibraryVersion"
    compile "com.android.support:support-v4:$rootProject.supportLibraryVersion"
    compile "com.android.support.test.espresso:espresso-idling-resource:$rootProject.espressoVersion"
    compile "com.google.guava:guava:$rootProject.guavaVersion"

    // Dependencies for local unit tests
    testCompile "junit:junit:$rootProject.ext.junitVersion"
    testCompile "org.mockito:mockito-all:$rootProject.ext.mockitoVersion"
    testCompile "org.hamcrest:hamcrest-all:$rootProject.ext.hamcrestVersion"

    // Android Testing Support Library's runner and rules
    androidTestCompile "com.android.support.test:runner:$rootProject.ext.runnerVersion"
    androidTestCompile "com.android.support.test:rules:$rootProject.ext.runnerVersion"

    // Dependencies for Android unit tests
    androidTestCompile "junit:junit:$rootProject.ext.junitVersion"
    androidTestCompile "org.mockito:mockito-core:$rootProject.ext.mockitoVersion"
    androidTestCompile 'com.google.dexmaker:dexmaker:1.2'
    androidTestCompile 'com.google.dexmaker:dexmaker-mockito:1.2'

    // Espresso UI Testing
    androidTestCompile "com.android.support.test.espresso:espresso-core:$rootProject.espressoVersion"
    androidTestCompile "com.android.support.test.espresso:espresso-contrib:$rootProject.espressoVersion"
androidTestCompile "com.android.support.test.espresso:espresso-intents:$rootProject.espressoVersion"
```

#HSLIDE

- ¿Qué librerias necesito para crear una app?
- Crear Helpers
- No depender de una sola librería
- ¿Cómo integrar una librería a nuestro proyecto?
- Librerías que realmente sirven
- Arquitectura : Clean y MVP
- Comunicación entre componentes

#HSLIDE
```
   ext {

       // sdk and tools
       minSdkVersion = 14
       targetSdkVersion = 23
       compileSdkVersion = 23
       buildToolsVersion = '23.0.3'

       // dependencies versions
       supportLibraryVersion = '23.4.0'
       playServicesVersion = '9.2.1'

       //junit
       junitVersion = "4.12"
       mockitoVersion = "1.10.19"

       //dagger2
       daggerVersion ='2.4'

   }
```
#VSLIDE
```
   dependencies {
       compile fileTree(include: ['*.jar'], dir: 'libs')
       testCompile 'junit:junit:4.12'
       compile "com.android.support:appcompat-v7:$rootProject.supportLibraryVersion"
       compile "com.android.support:support-v4:$rootProject.supportLibraryVersion"
       compile "com.android.support:design:$rootProject.supportLibraryVersion"
       compile "com.android.support:cardview-v7:$rootProject.supportLibraryVersion"
       compile "com.android.support:recyclerview-v7:$rootProject.supportLibraryVersion"
```
#VSLIDE
```
   //PICASSO
   http://square.github.io/picasso/
    
   //GLIDE
   https://github.com/bumptech/glide
   
   //FRESCO
   compile 'com.facebook.fresco:fresco:0.14.1'
   
   //BUTTERKNIFE
   compile 'com.jakewharton:butterknife:8.4.0'
   
   //DAGGER2
   compile "com.google.dagger:dagger:$rootProject.daggerVersion"
   apt "com.google.dagger:dagger-compiler:$rootProject.daggerVersion"
   
   //RETROFIT 2
   compile 'com.squareup.retrofit2:retrofit:2.1.0'
   
   //GSON
   compile 'com.squareup.retrofit2:converter-gson:2.1.0'
   
   //INTERCEPTOR
   compile 'com.squareup.okhttp3:logging-interceptor:3.3.1'
   
   //EVENTBUS
   compile 'org.greenrobot:eventbus:3.0.0'
   
   //RENDERERS
   compile 'com.github.pedrovgs:renderers:3.2.0'
   
```

#HSLIDE
 Helper
 
 - ##### ImageLoader
 
 - ##### ImageLoaderHelper
 
#HSLIDE

 Creando un ImageLoader 

¿Qué librerias usamos para cargar imágenes ?. Por ejemplo, si queremos mostrar imágenes en una vista o lista.

- Picasso by Square [http://square.github.io/picasso/](http://square.github.io/picasso/)

- Glide [https://github.com/bumptech/glide](https://github.com/bumptech/glide)

- Fresco by Facebook [https://github.com/facebook/fresco](https://github.com/facebook/fresco)

#HSLIDE

Picaso y Glide son muy similares , Glide y Fresco cuentan con algunas funcionalidades adicionales.

```Java

      Picasso.with(imageView.getContext())
                   .load(url)
                   .into(imageView);
```

```Java

      Glide.with(imageView.getContext())
                   .load(url)
                   .into(imageView);
```

#HSLIDE
Si es con fresco, debemos usar un customView
```Xml

      <com.facebook.drawee.view.SimpleDraweeView
          android:id="@+id/sdvImage"
          android:layout_width="130dp"
          android:layout_height="130dp"
          fresco:placeholderImage="@drawable/myPlaceholderImage" />
```

```Java

      Fresco.initialize(context);
      Uri imageUri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg");
      SimpleDraweeView draweeView = 
                  (SimpleDraweeView) findViewById(R.id.sdvImage);
      draweeView.setImageURI(imageUri);
```

#HSLIDE
La idea es no depender de una libreria en particular , no tener código suelto en nuestro proyecto y en su momento poder escoger con cual trabajar o no.

```Java

      public interface ImageLoader {

          void load(String url, ImageView imageView);
          void loadCircle(String url, ImageView imageView);
          void loadLocal(String path, ImageView imageView);
      }
```

#HSLIDE
ImageLoaderHelper

```Java 

       public class ImageLoaderHelper {
           public static final int GLIDE=1;
           public static final int PICASSO=2;

           private int type=GLIDE;
           private ImageLoader imageLoader;

           public ImageLoaderHelper(int type) {
               this.type = type;
               imageLoader= factory();
           }
       
```
#VSLIDE

```Java 

      public ImageLoader getLoader() {
               return imageLoader;
           }

     private ImageLoader factory()
     {
         switch (type)
         {
             case PICASSO:
                return new PicassoLoader();
             case GLIDE:
                 return new GlideLoader();
             default:
                 return new GlideLoader();
         }
     }
    }
```

#HSLIDE
Si es Picasso , creamos la clase  "PicassoLoader"
```Java

public class PicassoLoader implements ImageLoader {

    @Override
    public void load(String url, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadCircle(String url, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(url)
                .transform(new CircleTransform())
                .into(imageView);
    }

    @Override
    public void loadLocal(String path, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(new File(path))
                .into(imageView);
    }
}
```
#VSLIDE
Si es Glide , creamos la clase  "GlideLoader"
```Java

public class GlideLoader implements ImageLoader{

    @Override
    public void load(String url, ImageView imageView)
    {
        Glide.with(imageView.getContext())
        .load(url).into(imageView);
    }
    
    @Override
    public void loadCircle(String url, ImageView imageView){
        Glide.with(imageView.getContext()).load(url)
             .bitmapTransform(
              new CropCircleTransformation(
                                    imageView.getContext()))
             .into(imageView);
    }

    @Override
    public void loadLocal(String path, ImageView imageView){
        Glide.with(imageView.getContext())
                     .load(new File(path))
                     .into(imageView);
    }
```

#HSLIDE
Bueno... y como podemos usar este helper ?
```Java

   private final ImageLoader imageLoader;

      public MemberRenderer(ImageLoader imageLoader){
          this.imageLoader = imageLoader;
  }
```

```
   private void renderThumbnail(Member member) {
          Photo photo= member.getPhoto();
          String url= photo.getThumb_link();
          imageLoader.loadCircle(url,iviMember);
  }
```

#HSLIDE

  - ##### UI
  
#HSLIDE
Custom View

```Java

public class MTextView extends AppCompatTextView {
    public MTextView(Context context) {
        super(context);
        app(context,null);
    }

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        app(context,attrs);
    }

    public MTextView(Context context, AttributeSet attrs, 
        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        app(context,attrs);
    }
 
```
#VSLIDE
```Java

   private void app(Context context, Object object) {
        if(!isInEditMode()) loadFont(context);
    }

    protected void loadFont(Context context){
        String pathFont= "fonts/helveticaneuelight.ttf";
        if(getTag()==null){
            pathFont= "fonts/helveticaneuelight.ttf";
        }else if(getTag().equals("1")){
            pathFont= "fonts/helveticaneuelight.ttf";
        }else if(getTag().equals("2")){
            pathFont= "fonts/gotham-rounded-bold.otf";
        }
        Typeface type = Typeface.createFromAsset(
            context.getAssets(),pathFont);
        setTypeface(type);
    }
}
   
```
#HSLIDE
 ```Xml
 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout 
   xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="14dp">
    <ImageView
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:padding="2dp"
        tools:src="@drawable/circle"
        android:background="@drawable/bgcircle"
        android:layout_centerVertical="true"
        android:id="@+id/iviMember"/>

    <com.emedinaa.meetupapp.common.ui.MTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@+id/iviMember"
        android:textSize="18sp"
        android:textStyle="bold"
        android:tag="1"
        android:layout_marginLeft="10dp"
        android:layout_centerVertical="true"
        tools:text="Eduardo Medina"
        android:id="@+id/tviName"/>

    <com.emedinaa.meetupapp.common.ui.MTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="14sp"
        android:tag="1"
        android:layout_marginLeft="10dp"
        android:layout_alignParentBottom="true"
        tools:text="Miembro"
        android:layout_alignParentRight="true"
        android:id="@+id/tviType"/>

</RelativeLayout>
```
  
#HSLIDE

  - ##### Adapters
        
  - ##### Renderers 

#HSLIDE
Es recomendable cargar en local,las librerías a nuestro proyecto  y poder realizar los cambios que sean necesarios para que se ajusten a lo que necesitemos.
 ![LOGO](https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/renderers.png)
 
 
#HSLIDE
  - ##### Storage Options
  
#HSLIDE
  SharedPreferencesHelper
  ```Java
  
  public interface SharedPreferencesHelper{
         void saveEmail (String email);
         String email();
         void saveUser(User user);
         User user();
         void clear();
  }
  
  ```
#HSLIDE
DefaultSharedPreferencesHelper
```Java

      private  final String MY_SHARED_PREFERENCES 
                              = "com.emedinaa.sharedpreferences";
      private  final String KEY_EMAIL 
                              = MY_SHARED_PREFERENCES+".session.email";
      private  final String KEY_USER 
                              = MY_SHARED_PREFERENCES+".session.user";

      private final SharedPreferences sharedPreferences;
      private final GsonHelper gsonHelper;

      public DefaultSharedPreferencesHelper(GsonHelper gsonHelper,
          SharedPreferences sharedPreferences) {
          this.gsonHelper= gsonHelper;
          this.sharedPreferences = sharedPreferences;
      }

      @Override
      public void saveEmail(String email) {
          SharedPreferences.Editor editor = editor();
          editor.putString(KEY_EMAIL, email);
          editor.apply();
      }

      @Override
      public String email() {
          String  email= sharedPreferences.getString(KEY_EMAIL,"");
          return email;
      }

```
#VSLIDE
```Java

         @Override
         public void saveUser(User user) {
             SharedPreferences.Editor editor = editor();
             editor.putString(KEY_USER, gsonHelper.objectToJSON(user).toString());
             editor.apply();
         }

         @Override
         public User user() {
             String  userStr= sharedPreferences.getString(KEY_USER,"");
             User user= gsonHelper.jsonToObject(userStr,User.class);
             return user;
         }

         @Override
         public void clear() {
             SharedPreferences.Editor editor = editor();
             editor.clear();
             editor.apply();
         }

         private  SharedPreferences.Editor editor() {
             return sharedPreferences.edit();
         }
     }
```
#VSLIDE
```Java

 public class GsonHelper {

      public  JSONObject objectToJSON(Object obj)
      {
          GsonBuilder gsonb = new GsonBuilder();
          Gson gson = gsonb.create();
          JSONObject jsonObject = null;
          try {
              jsonObject = new JSONObject(gson.toJson(obj));
          } catch (Exception e) {
              e.printStackTrace();
          }
          return jsonObject;
      }

      public <T>T jsonToObject(String json,Class<T> cls){

          GsonBuilder gsonb = new GsonBuilder();
          Gson gson = gsonb.create();
          return gson.fromJson(json, cls);
      }
  }
```
#HSLIDE
Guardar y obtener el Email

```Java

  private void preferencesEmail() {
        SharedPreferences sharedPreferences= getSharedPreferences
        (MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        
        GsonHelper gsonHelper= new GsonHelper();

        sharedPreferencesHelper= new DefaultSharedPreferencesHelper
        (gsonHelper,sharedPreferences);
        
        sharedPreferencesHelper.saveEmail("emedinaa@gmail.com");
        String email=sharedPreferencesHelper.email();

        Log.v(TAG, "email "+email);
    }
```
Output
```Java

 V/MainActivity: email emedinaa@gmail.com
```

#HSLIDE
Android MVP
 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/mvp.png" height="640">

#HSLIDE
Clean Architecture
 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/clean_layouts.png" height="640">

#VSLIDE
 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/dependency_rule.png" height="640">

#HSLIDE
##### Inversión de Dependencias e Inyección de Dependencias
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/dependency_inject.png" height="580">

#HSLIDE
<!-- .slide: data-autoslide="10000"-->

#HSLIDE

References 

- Dagger2 [link](https://docs.google.com/presentation/d/1fby5VeGU9CN8zjw4lAb2QPPsKRxx6mSwCe9q7ECNSJQ/pub?start=false&loop=false&delayms=3000#slide=id.p)

- Clean Architecture

- Android MVP

- Helpers

#HSLIDE

**Write Code, Read Code , learn from experiences ...**
by Fernando Cejas

#HSLIDE

@eduardomedina

https://github.com/emedinaa/android-without-libraries

#### Thanks  ;)
