#HSLIDE
<!-- .slide: data-autoslide="10000" -->

**Eduardo José Medina Alfaro**
   
###### @eduardomedina
   
###### https://github.com/emedinaa
   

#HSLIDE

- ¿Qué librerias necesito para crear una app?
- Crear Helpers
- No depender de una sola librería
- ¿Cómo integrar una librería a nuestro proyecto?
- Librerías que realmente sirven
- Arquitectura : Clean y MVP
- Comunicación entre componentes

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
```Java

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
```
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
#HSLIDE
Bueno... y como podemos usar este helper ?
```
   private final ImageLoader imageLoader;

      public MemberRenderer(ImageLoader imageLoader) {
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

```
   public class MTextView extends AppCompatTextView {
       public MTextView(Context context) {
           super(context);
           app(context,null);
       }

       public MTextView(Context context, AttributeSet attrs) {
           super(context, attrs);
           app(context,attrs);
       }

       public MTextView(Context context, AttributeSet attrs, int defStyleAttr) {
           super(context, attrs, defStyleAttr);
           app(context,attrs);
       }
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
           Typeface type = Typeface.createFromAsset(context.getAssets(),pathFont);
           setTypeface(type);
       }
   }
```

#HSLIDE
 ```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
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
 ![LOGO](https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/renderers.png)
 
 
#HSLIDE
  - ##### Storage Options
  
#HSLIDE
  PreferencesHelper
  ```java
    public interface SharedPreferencesHelper {

      void saveEmail (String email);
      String email();

      void saveUser(User user);
      User user();

      void clear();
  }
  ```
#HSLIDE
DefaultSharedPreferencesHelper
```
      private  final String MY_SHARED_PREFERENCES = "com.emedinaa.sharedpreferences";
      private  final String KEY_EMAIL = MY_SHARED_PREFERENCES+".session.email";
      private  final String KEY_USER = MY_SHARED_PREFERENCES+".session.user";

      private final SharedPreferences sharedPreferences;
      private final GsonHelper gsonHelper;

      public DefaultSharedPreferencesHelper(GsonHelper gsonHelper,SharedPreferences sharedPreferences) {
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
#HSLIDE
```
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

```
  private void preferencesEmail() {
        SharedPreferences sharedPreferences= getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        GsonHelper gsonHelper= new GsonHelper();

        sharedPreferencesHelper= new DefaultSharedPreferencesHelper(gsonHelper,sharedPreferences);
        sharedPreferencesHelper.saveEmail("emedinaa@gmail.com");
        String email=sharedPreferencesHelper.email();

        Log.v(TAG, "email "+email);
    }
```
Output
```
 V/MainActivity: email emedinaa@gmail.com
```

#HSLIDE
Clean Architecture & Android MVP

#HSLIDE
Inversión de control e Inyección de Dependencias
![DI](https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/dependency_inject.png)

#HSLIDE
<!-- .slide: data-autoslide="8000" -->

#HSLIDE

References 

- Dagger2 [link](https://docs.google.com/presentation/d/1fby5VeGU9CN8zjw4lAb2QPPsKRxx6mSwCe9q7ECNSJQ/pub?start=false&loop=false&delayms=3000#slide=id.p)

- Clean Architecture

- Android MVP

- Helpers

#HSLIDE

### Go for it.
### Just add <span style="color: #e49436; text-transform: none">PITCHME.md</span> ;)
