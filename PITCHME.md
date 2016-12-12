#HSLIDE
<!-- .slide: data-autoslide="10000" -->

   ### Eduardo José Medina Alfaro
   
   #### @eduardomedina
   
   #### https://github.com/emedinaa
   

#HSLIDE

- ¿Qué librerias necesito ahora para crear una app?
- Helpers
- Integrar una librería a nuestro proyecto
- No depender de una sola librería
- Librerías que realmente sirven
- Arquitectura : Clean y MVP

#HSLIDE
 Helper
 
 - ##ImageLoader
 
 - ##ImageLoaderHelper
 
#HSLIDE

 Creando un ImageLoader 

¿Qué librerias usamos normalmente para cargar imagenes ?. Por ejemplo,si queremos mostrar una imagen en un vista o lista 

- Picasso by Square [http://square.github.io/picasso/](http://square.github.io/picasso/)

- Glide [https://github.com/bumptech/glide](https://github.com/bumptech/glide)

- Fresco by Facebook [https://github.com/facebook/fresco](https://github.com/facebook/fresco)

#HSLIDE

Picaso y Glide son muy similares , Glide y Fresco cuentan algunas  funcionalidades adicionales.

```
   Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
```

```
   Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
```

#HSLIDE
Si es con fresco, debemos usar un customView
```
   <com.facebook.drawee.view.SimpleDraweeView
       android:id="@+id/sdvImage"
       android:layout_width="130dp"
       android:layout_height="130dp"
       fresco:placeholderImage="@drawable/myPlaceholderImage" />
```

```
   Fresco.initialize(context);
   Uri imageUri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg");
   SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.sdvImage);
   draweeView.setImageURI(imageUri);
```

#HSLIDE
La idea es no depender de una en particular , no tener código suelto en nuestro código y en su momento poder escoger trabajar con una u otra.

Definimos el comportamiento del ImageLoader:

```
      public interface ImageLoader {

          void load(String url, ImageView imageView);
          void loadCircle(String url, ImageView imageView);
          void loadLocal(String path, ImageView imageView);
      }
```

#HSLIDE
 Luego, lo implementación en la clase "ImageLoaderHelper"
 
```
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
Si es Picasso , creamos la implementación "PicassoLoader"
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

  - ##Adapters
        
  - ##Renderers 

#HSLIDE
Es recomendable cargar en local,las librerías a nuestro proyecto  y poder realizar los cambios que sean necesarios para que se ajusten a lo que necesitemos.
 ![LOGO](https://raw.githubusercontent.com/emedinaa/android-without-libraries/master/renderers.png)
 
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
