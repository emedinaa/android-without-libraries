#HSLIDE
<!-- .slide: data-autoslide="10000" -->

**Eduardo José Medina Alfaro**
   
###### @eduardomedina
   
###### https://github.com/emedinaa

#HSLIDE

**"Como vivir sin librerías y no morir en el intento"**

#HSLIDE

#### Meetup App

<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp0.png" height="500">
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp1.png"
height="500">

#VSLIDE

<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp2.png"
height="480">
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp3.png"
height="480">

#VSLIDE
[https://www.meetup.com/es-ES/meetup_api/](https://www.meetup.com/es-ES/meetup_api/)
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/meetup_api.png">

#HSLIDE
   ¿?
- ¿Qué librerías necesito para crear esta app?
- ¿Cómo comunico los componentes de mi app?
- ¿Cómo manejo las imágenes?
- ¿Con que librería consumo los servicios Resful?
- ¿Cómo evito el cruce de dependencias?
- ¿Usar o no alguna arquitectura?, ¿Qué patrón de architectura? MVP, Clean

#HSLIDE
###### [https://github.com/android10/Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture)
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/cleanandroid10.png" height="520">

#VSLIDE
```Java
   dependencies {
     def presentationDependencies = rootProject.ext.presentationDependencies
     def presentationTestDependencies = rootProject.ext.presentationTestDependencies
     def developmentDependencies = rootProject.ext.developmentDependencies

     compile project(':domain')
     compile project(':data')

     apt presentationDependencies.daggerCompiler
     compile presentationDependencies.dagger
     compile presentationDependencies.butterKnife
     compile presentationDependencies.recyclerView
     compile presentationDependencies.rxJava
     compile presentationDependencies.rxAndroid
     provided presentationDependencies.javaxAnnotation

     androidTestCompile presentationTestDependencies.mockito
     androidTestCompile presentationTestDependencies.dexmaker
     androidTestCompile presentationTestDependencies.dexmakerMockito
     androidTestCompile presentationTestDependencies.espresso
     androidTestCompile presentationTestDependencies.testingSupportLib

     //Development
     compile developmentDependencies.leakCanary
   }
```

#HSLIDE
[https://github.com/googlesamples/android-architecture](https://github.com/googlesamples/android-architecture)
<img src="https://raw.githubusercontent.com/wiki/googlesamples/android-architecture/images/aab-logo.png" height="471">

#VSLIDE
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/google_android_architecture.png">

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

#### Meetup App

<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp0.png" height="500">
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp1.png"
height="500">

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
   
   //RxJava & RxAndroid
   compile 'io.reactivex:rxandroid:1.2.1'
    
   compile 'io.reactivex:rxjava:1.1.6'
    
   compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
   
   //Transforms 
   //https://github.com/wasabeef/picasso-transformations
   
   compile 'jp.wasabeef:picasso-transformations:2.1.0'
   
   // If you want to use the GPU Filters
   compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'
   
   //Fonts
   //https://github.com/chrisjenx/Calligraphy
   compile 'uk.co.chrisjenx:calligraphy:2.2.0'
   
```

#HSLIDE
 Helper
 
 - ##### ImageLoaderHelper
 
#VSLIDE
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp2.png"
height="480">
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp3.png"
height="480">
 
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
 
 - ##### UI & CustomViews
 
#VSLIDE
 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp0.png" height="500">
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp1.png"
height="500">
  
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
 
 - ##### Adapters & RecyclerView
 
#VSLIDE
 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp2.png" height="500">
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/screenshotApp3.png"
height="500">

#HSLIDE
```java
      public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

          private List<Meetup> meetups;

          public EventAdapter(List<Meetup> meetups) {
              this.meetups = meetups;
              //imageLoaderHelper= new ImageLoaderHelper(ImageLoaderHelper.GLIDE);
          }

          public static class ViewHolder extends RecyclerView.ViewHolder
          {
              private TextView tviName,tviPlace,tviAddress,tviDesc;
              private TextView tviDate,tviUrl;

              public ViewHolder(View rootView) {
                  super(rootView);
                  tviName= (TextView) rootView.findViewById(R.id.tviName);
                  tviPlace= (TextView) rootView.findViewById(R.id.tviPlace);
                  tviAddress= (TextView) rootView.findViewById(R.id.tviAddress);
                  tviDesc= (TextView) rootView.findViewById(R.id.tviDesc);
                  tviDate= (TextView) rootView.findViewById(R.id.tviDate);
                  tviUrl= (TextView) rootView.findViewById(R.id.tviUrl);
              }
          }
```

#VSLIDE
```java

     @Override
       public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           // create a new view
           View v = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.row_meetup, parent, false);
           // set the view's size, margins, paddings and layout parameters
           ViewHolder vh = new ViewHolder(v);
           return vh;
       }

       @Override
       public void onBindViewHolder(ViewHolder holder, int position) {
           Meetup meetup = meetups.get(position);
           if (meetup != null) {
               String name= meetup.getName();
               String place= meetup.getVenue().getName();
               String address= meetup.getVenue().getAddress();
               String desc= meetup.getDescription();
               String url= meetup.getUrl();
               holder.tviName.setText(name);
               holder.tviPlace.setText(place);
               holder.tviAddress.setText(address);
               holder.tviDesc.setText(Html.fromHtml(desc));
               holder.tviUrl.setText(url);
           }
       }

       @Override
       public int getItemCount() {
           return meetups.size();
       }
```
#HSLIDE
Es recomendable descargar las librerías y agregarlas a nuestro proyecto  y poder realizar los cambios que sean necesarios para que se ajusten a lo que necesitemos.
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
  
  - ##### Arquitectura de la App
  
#HSLIDE
Android MVP

<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/mvp.png" height="540">

#VSLIDE

```Java

   public abstract class BasePresenter<V> implements Presenter<V> {

       protected V view;

       @Override
       public void attachedView(V view) {
           this.view= view;
       }

       @Override
       public void detachView() {
           this.view= null;
       }
   }
```

```Java

   public interface Presenter<V> {

       void attachedView(V view);
       void detachView();
   }
```
#VSLIDE
```Java

    private final EventsInteractor eventsInteractor;

    public EventPresenter(EventsInteractor eventsInteractor) {
        this.eventsInteractor = eventsInteractor;
    }

    public void getPastEvents(String groupName){
        view.showLoading();
        this.eventsInteractor.pastEvents(groupName,restCallback);
    }
    public void getUpcomingEvent(String groupName){
        view.showLoading();
        this.eventsInteractor.events(groupName,restCallback);
    }

    private StorageCallback restCallback= new StorageCallback() {
        @Override
        public void onSuccess(Object object) {
            view.renderMeetups((List<Meetup>)(object));
            view.hideLoading();
        }

        @Override
        public void onFailure(Exception e) {
            String message= e.getMessage();
            view.showMessage(message);
            view.hideLoading();
        }
    };
```
#VSLIDE
```Java

   public class MemberPresenter extends BasePresenter<MemberView> {

       private final MembersInteractor membersInteractor;

       public MemberPresenter(MembersInteractor membersInteractor) {
           this.membersInteractor = membersInteractor;
       }

       public void getMembers(String groupName){
           view.showLoading();
           this.membersInteractor.membersByGroup(groupName,restCallback);
       }


       private StorageCallback restCallback= new StorageCallback() {
           @Override
           public void onSuccess(Object object) {
               view.renderMembers((List<Member>)(object));
               view.hideLoading();
           }

           @Override
           public void onFailure(Exception e) {
               String message= e.getMessage();
               view.showMessage(message);
               view.hideLoading();
           }
       };
   }
```

#HSLIDE
Clean Architecture

 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/clean_layouts.png" height="620">

#VSLIDE

 <img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/dependency_rule.png" height="620">
 
#HSLIDE
  
  - ##### S. O. L. I. D.
  
#HSLIDE

##### Inversión de Dependencias e Inyección de Dependencias
<img src="https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/images/dependency_inject.png" height="580">

#HSLIDE

```Java

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rviEvents.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rviEvents.setLayoutManager(mLayoutManager);
        int margin= getResources().getDimensionPixelSize(R.dimen.row_margin);
        rviEvents.addItemDecoration(new MarginDecoration(margin));

        EventMapper eventMapper= new EventMapper();
        eventPresenter= new EventPresenter(new EventsRestInteractor(eventMapper));
        eventPresenter.attachedView(this);

    }
    
```

#HSLIDE
  
  - ##### DEMO **https://github.com/emedinaa/android-without-libraries/tree/dev**
  
#HSLIDE
  - #### Conclusiones
  
#HSLIDE

 - No reinventar la rueda.
 
  - Si una librería vale la pena, aprende de ella , estudia la técnica y úsala en tu proyecto.
 
 - Haz que tu código no dependa de una sola librería.
 
 - Estudia  la teoria mas que como usar una librería.

 
#HSLIDE
  
  - ##### Referencias
  
#HSLIDE

- Dagger2 [link](https://docs.google.com/presentation/d/1fby5VeGU9CN8zjw4lAb2QPPsKRxx6mSwCe9q7ECNSJQ/pub?start=false&loop=false&delayms=3000#slide=id.p)
- MVP & Clean Architecture [https://github.com/googlesamples/android-architecture](https://github.com/googlesamples/android-architecture)
- Uncle Bob Clean Architecture [https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)
- Helpers [http://www.yegor256.com/2014/04/27/typical-mistakes-in-java-code.html](http://www.yegor256.com/2014/04/27/typical-mistakes-in-java-code.html)
- Dependency Injection for testing [https://gumroad.com/l/DaggerlessDITesting#](https://gumroad.com/l/DaggerlessDITesting#)

#VSLIDE

- Custom Dialog [https://github.com/emedinaa/android_custom_dialog_fragment](https://github.com/emedinaa/android_custom_dialog_fragment)

- SharedpreferencesHelper [https://github.com/emedinaa/sharedpreferenceshelper](https://github.com/emedinaa/sharedpreferenceshelper)

- Custom View [https://github.com/emedinaa/aiesec_pucp_custom_comp_android](https://github.com/emedinaa/aiesec_pucp_custom_comp_android)

- Clean Architecture [https://github.com/emedinaa/android-clean-architecture](https://github.com/emedinaa/android-clean-architecture)

#HSLIDE

**Write Code, Read Code , learn from experiences ...**

by Fernando Cejas

#HSLIDE

@eduardomedina

https://github.com/emedinaa/android-without-libraries

#### Thanks  ;)
